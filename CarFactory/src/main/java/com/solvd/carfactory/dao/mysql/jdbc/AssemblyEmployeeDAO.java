package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IAssemblyEmployeeDAO;
import com.solvd.carfactory.models.assemblyline.AssemblyLine;
import com.solvd.carfactory.models.employee.AssemblyEmployee;
import com.solvd.carfactory.models.employee.Employee;
import org.apache.log4j.Logger;

import java.sql.*;

public class AssemblyEmployeeDAO extends AbstractMysqlJdbcDAO<AssemblyEmployee> implements IAssemblyEmployeeDAO {
    private final static Logger LOGGER = Logger.getLogger(AssemblyEmployeeDAO.class);
    private final static String CREATE_ASSEMBLYEMPLOYEE_FROM_OBJECT = "INSERT INTO "
        + "assembly_employees (assembly_line_id) "
        + "VALUES (?)";
    private final static String GET_ASSEMBLYEMPLOYEE_BY_ID = "SELECT * FROM assembly_employees WHERE id = ?";
    private final static String UPDATE_ASSEMBLYEMPLOYEE = "UPDATE assembly_employees SET "
        + "assembly_line_id = ? WHERE id = ?";
    private final static String DELETE_ASSEMBLYEMPLOYEE = "DELETE FROM assembly_employees WHERE id = ?";

    @Override
    public void createItem(AssemblyEmployee item) {
        item.setId(createItem(item, CREATE_ASSEMBLYEMPLOYEE_FROM_OBJECT));
    }
                
    @Override
    public AssemblyEmployee getItemById(long id) {
        return getItemById(id, GET_ASSEMBLYEMPLOYEE_BY_ID);
    }

    @Override
    public void updateItem(AssemblyEmployee item) {
        updateItem(item, UPDATE_ASSEMBLYEMPLOYEE, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_ASSEMBLYEMPLOYEE);
    }

    @Override
    protected AssemblyEmployee buildItem(ResultSet rs) throws SQLException{
        AssemblyEmployee assemblyEmployee = new AssemblyEmployee();

        assemblyEmployee.setId(rs.getLong("employee_id"));
        assemblyEmployee.setAssemblyLine(new AssemblyLine(rs.getLong("assembly_line_id")));

        return assemblyEmployee;
    }

    @Override
    protected void setPsParameters(AssemblyEmployee item, PreparedStatement ps) throws SQLException {
        ps.setLong(1, item.getAssemblyLine().getId());
    }
}