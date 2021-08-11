# 05/08/2021
# by Sergio Marchio
#
# Quick and dirty script to help with writing Java DAOs and Services
#
# ! Input files are read from the same dir in which the script is located !
# Inputs:
#   .sql file (file name must end with "-generate_db.sql")
#       containing CREATE TABLE statements
#       with fields in separate lines and table and field names enclosed between backticks (`)
#       just like the .sql generated in MySQL Workbench
#       first field is assumed to be the pk named id
#       fk are assumed to be named as table_name_id
#   pom.xml to read groupId and artifactId (Maven dir structure is expected)
#   model Classes are expected to be in groupId.artifactId.models.ModelClass
#       Class file names should not be repeated
#
# Output:
#   folder structure for dao and services with corresponding classes and abstract classes
#       Including code for SQL CRUD queries, and code for buildItem and setPsParameters methods
#       which are defined in my implementation of AbstractMysqlJdbcDAO.java
#       and imports!
#
# ! Important Note !
# Check for correctness of generated filenames and code
#   * Pay special attention to class names and data types!
#   Depending on fk names in database, set id in setPsParameters and Class name in service impl may be wrong
#   Unsupported data types are defined as Object with class UNKNOWN_
#   * Pay special attention to classes that extend other model class, if any
#   * Object data types assignment may be wrong!
#


import inspect
import os
import pathlib
import sys


def snake_to_camel(string):
    return string.replace('_', ' ').title().replace(' ', '')


# Works in most cases, check output anyway
def singular(string):
    if string.endswith("ies"):
        return string[:-3] + "y"
    elif string.endswith("ses"):
        return string[:-2]
    elif string.endswith("s"):
        return string[:-1]
    else:
        return string


# gets table name from MySQL Workbench's output CREATE TABLE line
def get_table_name(string):
    return string.split(".")[1].split('`')[1]


# Argument is the line defining a field in sql create
#    returns a list consisting of the name of the field, the data type, and foreign key name if present
def get_fields(string):
    parts = string.strip().split(' ')
    name = parts[0].split('`')[1]
    sql_type = parts[1].split('(')[0]

    fk = snake_to_camel(name[:-3]) if name.endswith("_id") else ""

    return [name, sql_type, fk]


# returns a dict where each key is the name of the table
#   value is a list of lists,
#   where each inner list corresponds to a field and its characteristics
def get_db():
    with open(get_sql_file(), "r") as file:

        db = {}

        line = file.readline()
        while line:
            if line.startswith("CREATE TABLE"):
                table_name = get_table_name(line)
                fields = []

                line = file.readline()
                while line.strip().startswith('`'):
                    fields.append(get_fields(line))

                    line = file.readline()

                db[table_name] = fields
                continue

            line = file.readline()

    return db


# returns a list for the java type corresponding to the sql_type
#   this list contains the Class name of the java type, and if needed the function to map the value to the java type
#   and the library which contains that function
def java_type(sql_type):
    types = {"VARCHAR": ["String", "String", ""],
             "INT": ["Long", "Long", ""],
             "DOUBLE": ["Double", "Double", ""],
             "DATETIME": ["Object", "LocalDateTime", "java.time.LocalDateTime"],
             "YEAR": ["Object", "Year", "java.time.Year"]}

    java_field_type = types[sql_type] if sql_type in types.keys() else ["Object", "UNKNOWN_", ".UNKNOWN"]

    return java_field_type


def get_import(class_name):
    # Template
    # import {group_id}.{artifact_id}.models
    # import com.solvd .carfactory   .models.location.City;

    class_file = "UNKNOWN_" + class_name
    for f in pathlib.Path(models_path).rglob('*'):
        if f.is_file() and f.name == class_name + ".java":
            class_file = f

    full_class = pathlib.PurePath(str(class_file)).as_posix() \
        .replace("/", ".").replace("src.main.java.", "").replace(".java", "")
    return f"import {full_class};"


def make_rs_get(class_name, name, sql_type, fk):
    # template:
    # {class_name}.set{field}(          {rs_get}                                                );
    # e.setFirstName(                   rs.getString("first_name")                              );
    # e.setCreationDate(                rs.getObject("creation_date", LocalDateTime.class)      );
    # e.setDepartment(new Department(   rs.getLong("department_id")                         )   );

    field = snake_to_camel(name) if fk == "" else fk + "(new " + fk

    field_type, obj_class, *_ = java_type(sql_type)

    rs_get = f'rs.get{field_type}("{name}"'
    if field_type == "Object":
        rs_get = f"{rs_get}, {obj_class}.class"

    if fk != "":
        rs_get += ")"

    return f"{class_name}.set{field}({rs_get}));"


def make_ps_set(i, name, sql_type, fk):
    # Template:
    # ps.set{type}  ({index},   {argument}                                                  );
    # ps.setString  (6,         item.getFirstName()                                         );
    # ps.setLong    (8,         item.getDepartment().getId()                                );
    # ps.setObject  (1,         LocalDateTime(item.getCreationDate()), MysqlType.DATETIME   );
    index = str(i)

    field = snake_to_camel(name) + "()" if fk == "" else fk + "().getId()"

    field_type, obj_class, *_ = java_type(sql_type)
    argument = f"item.get{field}"

    if field_type == "Object":
        argument = f"{obj_class}({argument}), MysqlType.{sql_type}"

    return f"ps.set{field_type}({index}, {argument});"


def create_dao(db):
    dao_path = "dao/"
    impl_path = dao_path + "mysql/jdbc/"
    impl_class = impl_path.replace("/", ".")[:-1]

    if not os.path.exists(impl_path):
        os.makedirs(impl_path)

    for table_name, fields in db.items():
        table_class = snake_to_camel(singular(table_name))
        table_sql = table_class.upper()
        table_var = table_class[0].lower() + table_class[1:]

        fk_fields = [fk for *_, fk in fields if fk != ""]

        dao = table_class + "DAO"
        interface = "I" + dao

        with open(dao_path + interface + ".java", "w") as file:
            file.write(inspect.cleandoc(f'''
                package {group_id}.{artifact_id}.dao;
                
                {get_import(table_class)}
                
                public interface {interface} extends IBaseDAO<{table_class}> {{
                }}
                '''))

        with open(impl_path + dao + ".java", "w") as file:
            fields_insert = ", ".join([n for n, *_ in fields[1:]])
            parameters_insert = ", ".join(["(?)" for _ in fields[1:]])
            fields_update = ", ".join([n + " = ?" for n, *_ in fields[1:]])

            rs_calls = [make_rs_get(table_var, name, field_type, fk) for name, field_type, fk in fields]
            rs_lines = ("\n" + " " * 4 * 6).join(rs_calls)

            # Depending on fk names, set id may be wrong, check it manually!
            ps_calls = [make_ps_set(i + 1, name, field_type, fk)
                        for i, (name, field_type, fk) in enumerate(fields[1:])]
            ps_lines = ("\n" + " " * 4 * 6).join(ps_calls)

            type_imports = [f"import {lib};" for name, field_type, fk in fields
                            for *_, lib in [java_type(field_type)] if lib != ""]
            if type_imports:
                type_imports += ["import com.mysql.cj.MysqlType;"]
            imports = [get_import(class_name) for class_name in fk_fields + [table_class]]
            imports += [f"import {group_id}.{artifact_id}.dao.{interface};"]
            imports += ["import org.apache.log4j.Logger;"]
            import_lines = ("\n" + " " * 4 * 4).join(sorted(imports + type_imports))

            file.write(inspect.cleandoc(f'''
                package {group_id}.{artifact_id}.{impl_class};
                
                {import_lines}
                
                import java.sql.*;
                
                public class {dao} extends AbstractMysqlJdbcDAO<{table_class}> implements {interface} {{
                    private final static Logger LOGGER = Logger.getLogger({dao}.class);
                    private final static String CREATE_{table_sql}_FROM_OBJECT = "INSERT INTO "
                        + "{table_name} ({fields_insert}) "
                        + "VALUES {parameters_insert}";
                    private final static String GET_{table_sql}_BY_ID = "SELECT * FROM {table_name} WHERE id = ?";
                    private final static String UPDATE_{table_sql} = "UPDATE {table_name} SET "
                        + "{fields_update} WHERE id = ?";
                    private final static String DELETE_{table_sql} = "DELETE FROM {table_name} WHERE id = ?";

                    @Override
                    public void createItem({table_class} item) {{
                        item.setId(createItem(item, CREATE_{table_sql}_FROM_OBJECT));
                    }}
                                
                    @Override
                    public {table_class} getItemById(long id) {{
                        return getItemById(id, GET_{table_sql}_BY_ID);
                    }}

                    @Override
                    public void updateItem({table_class} item) {{
                        updateItem(item, UPDATE_{table_sql}, item.getId());
                    }}

                    @Override
                    public void deleteItem(long id) {{
                        deleteItem(id, DELETE_{table_sql});
                    }}

                    @Override
                    protected {table_class} buildItem(ResultSet rs) throws SQLException{{
                        {table_class} {table_var} = new {table_class}();

                        {rs_lines}

                        return {table_var};
                    }}

                    @Override
                    protected void setPsParameters({table_class} item, PreparedStatement ps) throws SQLException {{
                        {ps_lines}
                    }}
                }}
                '''))


def make_fk_dao(fk):
    fk_var = fk[0].lower() + fk[1:]

    # Template:
    # private ICountryDAO countryDAO = new CountryDAO();

    return f"private I{fk}DAO {fk_var}DAO = new {fk}DAO();"


def make_fk_set(table_var, fk):
    # Template:
    #                (item                  ({fk_id}                  ));
    # city.setCountry(countryDAO.getItemById(city.getCountry().getId()));
    fk_var = fk[0].lower() + fk[1:]
    fk_id = f"{table_var}.get{fk}().getId()"
    item = f"{fk_var}DAO.getItemById({fk_id})"

    return f"{table_var}.set{fk}({item});"


def create_services(db):
    services_path = "services/"
    impl_path = services_path + "impl/"

    if not os.path.exists(impl_path):
        os.makedirs(impl_path)

    for table_name, fields in db.items():
        table_class = snake_to_camel(singular(table_name))
        table_var = table_class[0].lower() + table_class[1:]

        impl = table_class + "Service"
        interface = "I" + impl

        fk_fields = [fk for *_, fk in fields if fk != ""]

        # Creates services only for tables with at least one fk
        if fk_fields:
            with open(services_path + interface + ".java", "w") as file:
                file.write(inspect.cleandoc(f'''
                    package {group_id}.{artifact_id}.services;
                    
                    {get_import(table_class)}

                    public interface {interface} {{
                        {table_class} get{table_class}ById(long id);
                    }}
                    '''))

            with open(impl_path + impl + ".java", "w") as file:
                fk_dao = [make_fk_dao(fk) for fk in fk_fields]
                fk_dao_lines = ("\n" + " " * 4 * 6).join(fk_dao)
                fk_sets = [make_fk_set(table_var, fk) for fk in fk_fields]
                fk_set_lines = ("\n" + " " * 4 * 7).join(fk_sets)

                imports = [get_import(class_name) for class_name in fk_fields + [table_class]]
                imports += [f"import {group_id}.{artifact_id}.services.{interface};"]

                import_idaos = [f"import {group_id}.{artifact_id}.dao.I{class_name}DAO;"
                                for class_name in fk_fields + [table_class]]
                import_daos = [f"import {group_id}.{artifact_id}.dao.mysql.jdbc.{class_name}DAO;"
                               for class_name in fk_fields + [table_class]]

                import_lines = ("\n" + " " * 4 * 5).join(sorted(imports + import_idaos + import_daos))

                file.write(inspect.cleandoc(f'''
                    package {group_id}.{artifact_id}.services.impl;
                    
                    {import_lines}
                                        
                    public class {impl} implements {interface} {{
                        private I{table_class}DAO {table_var}DAO = new {table_class}DAO();
                        {fk_dao_lines}
                    
                        @Override
                        public {table_class} get{table_class}ById(long id) {{
                            {table_class} {table_var} = {table_var}DAO.getItemById(id);
                            {fk_set_lines}
                            return {table_var};
                        }}
                    }}
                    '''))


def get_sql_file():
    return [f for f in os.listdir(".") if os.path.isfile(f) and f.endswith("-generate_db.sql")][0]


def get_group_artifact():
    group_id = ""
    artifactId = ""
    with open("pom.xml", "r") as file:
        for line in file.readlines():
            if line.startswith("    <groupId>"):
                group_id = line.strip().replace("<groupId>", "").replace("</groupId>", "")
            elif line.startswith("    <artifactId>"):
                artifact_id = line.strip().replace("<artifactId>", "").replace("</artifactId>", "")

    return group_id, artifact_id


group_id, artifact_id = get_group_artifact()

base_path = "src/main/java/" + group_id.replace(".", "/") + "/" + artifact_id.replace(".", "/") + "/"
models_path = base_path + "models"

print(base_path)
print(models_path)

database = get_db()

print("Identified tables and fields:")
for t, f in database.items():
    print(t + "\n" + str([x for x in f]) + "\n")

create_dao(database)
create_services(database)
