package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.models.employee.Department;
import com.solvd.carfactory.models.employee.Employee;
import org.apache.log4j.Logger;

import java.sql.*;

public class DepartmentDAO extends AbstractMysqlJdbcDAO<Department> implements IDepartmentDAO {
    private final static Logger LOGGER = Logger.getLogger(DepartmentDAO.class);
    private final static String CREATE_DEPARTMENT_FROM_OBJECT = "INSERT INTO "
        + "departments (name, phone, email, head_employee_id) "
        + "VALUES (?), (?), (?), (?)";
    private final static String GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id = ?";
    private final static String UPDATE_DEPARTMENT = "UPDATE departments SET "
        + "name = ?, phone = ?, email = ?, head_employee_id = ? WHERE id = ?";
    private final static String DELETE_DEPARTMENT = "DELETE FROM departments WHERE id = ?";

    @Override
    public void createItem(Department item) {
        item.setId(createItem(item, CREATE_DEPARTMENT_FROM_OBJECT));
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
    protected Department buildItem(ResultSet rs) throws SQLException{
        Department department = new Department();

        department.setId(rs.getLong("id"));
        department.setName(rs.getString("name"));
        department.setPhone(rs.getString("phone"));
        department.setEmail(rs.getString("email"));
        department.setHeadEmployee(new Employee(rs.getLong("head_employee_id")));

        return department;
    }

    @Override
    protected void setPsParameters(Department item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getPhone());
        ps.setString(3, item.getEmail());
        ps.setLong(4, item.getHeadEmployee().getId());
    }
}