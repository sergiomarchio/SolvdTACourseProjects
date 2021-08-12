package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.models.employee.Department;
import com.solvd.carfactory.models.employee.Employee;
import com.solvd.carfactory.models.location.Address;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO extends AbstractMysqlJdbcDAO<Employee> implements IEmployeeDAO {
    private final static Logger LOGGER = Logger.getLogger(EmployeeDAO.class);
    private final static String CREATE_EMPLOYEE_FROM_OBJECT = "INSERT INTO "
            + "employees(first_name, last_name, email, phone, shift, salary, address_id, department_id)"
            + "VALUES (?), (?), (?), (?), (?), (?), (?), (?)";
    private final static String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private final static String UPDATE_EMPLOYEE = "UPDATE employees SET "
            + "first_name = ?, last_name = ?, email = ?, phone = ?, shift = ?, salary = ?, address_id = ?, department_id = ?"
            + "WHERE id = ?";
    private final static String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";

    @Override
    public void createItem(Employee item) {
        createItem(item, CREATE_EMPLOYEE_FROM_OBJECT);
    }

    @Override
    public Employee getItemById(long id) {
        return getItemById(id, GET_EMPLOYEE_BY_ID);
    }

    @Override
    public void updateItem(Employee item) {
        updateItem(item, UPDATE_EMPLOYEE, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_EMPLOYEE);
    }

    @Override
    protected Employee buildItem(ResultSet rs) throws SQLException {
        Employee e = new Employee();

        e.setId(rs.getLong("id"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setEmail(rs.getString("email"));
        e.setPhone(rs.getString("phone"));
        e.setShift(rs.getString("shift"));
        e.setSalary(rs.getDouble("salary"));
        Address a = new Address(rs.getLong("address_id"));
        e.setAddress(a);

        Department d = new Department(rs.getLong("department_id"));
        e.setDepartment(d);

        return e;
    }

    @Override
    protected void setPsParameters(Employee item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getFirstName());
        ps.setString(2, item.getLastName());
        ps.setString(3, item.getEmail());
        ps.setString(4, item.getPhone());
        ps.setString(5, item.getShift());
        ps.setDouble(6, item.getSalary());
        ps.setLong(7, item.getAddress().getId());
        ps.setLong(8, item.getDepartment().getId());
    }
}
