package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.models.employee.Department;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO extends AbstractMysqlJdbcDAO<Department> implements IDepartmentDAO {
    private final static Logger LOGGER = Logger.getLogger(DepartmentDAO.class);
    private final static String CREATE_DEPARTMENT_FROM_OBJECT = "INSERT INTO "
            + "departments(name, phone, email, head_employee_id) VALUES (?), (?), (?), (?)";
    private final static String GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id = ?";
    private final static String UPDATE_DEPARTMENT = "UPDATE departments SET "
            + "name = ?, phone = ?, email = ?, head_employee_id = ? WHERE id = ?";
    private final static String DELETE_DEPARTMENT = "DELETE FROM departments WHERE id = ?";

    @Override
    public void createItem(Department item) {
        createItem(item, CREATE_DEPARTMENT_FROM_OBJECT);
    }

    @Override
    public Department getItemById(long id) {
        return getItemById(id, GET_DEPARTMENT_BY_ID);
    }
    @Override
    public void updateItem(Department item) {
        updateItem(item, UPDATE_DEPARTMENT, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_DEPARTMENT);
    }

    @Override
    protected Department buildItem(ResultSet rs) throws SQLException {
        Department d = new Department();

        d.setId(rs.getLong("id"));
        d.setName(rs.getString("name"));
        // ...

        return d;
    }

    @Override
    protected void setPsParameters(Department item, PreparedStatement ps) throws SQLException {

    }
}
