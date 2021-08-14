package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.DepartmentDAO;
import com.solvd.carfactory.dao.mysql.jdbc.EmployeeDAO;
import com.solvd.carfactory.models.employee.Employee;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.IDepartmentService;
import com.solvd.carfactory.services.IEmployeeService;
                    
public class EmployeeService implements IEmployeeService {
    private IEmployeeDAO employeeDAO = new EmployeeDAO();
    private IAddressService addressService = new AddressService();
    private IDepartmentService departmentService = new DepartmentService();

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee = employeeDAO.getItemById(id);
        employee.setAddress(addressService.getAddressById(employee.getAddress().getId()));
        employee.setDepartment(departmentService.getDepartmentById(employee.getDepartment().getId()));
        return employee;
    }

    @Override
    public Employee getEmployeeByIdNoDept(long id) {
        Employee employee = employeeDAO.getItemById(id);
        employee.setAddress(addressService.getAddressById(employee.getAddress().getId()));
        return null;
    }
}