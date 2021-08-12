package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.DepartmentDAO;
import com.solvd.carfactory.dao.mysql.jdbc.EmployeeDAO;
import com.solvd.carfactory.models.employee.Employee;
import com.solvd.carfactory.services.IEmployeeService;
                    
public class EmployeeService implements IEmployeeService {
    private IEmployeeDAO employeeDAO = new EmployeeDAO();
    private IAddressDAO addressDAO = new AddressDAO();
    private IDepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee = employeeDAO.getItemById(id);
        employee.setAddress(addressDAO.getItemById(employee.getAddress().getId()));
        employee.setDepartment(departmentDAO.getItemById(employee.getDepartment().getId()));
        return employee;
    }
}