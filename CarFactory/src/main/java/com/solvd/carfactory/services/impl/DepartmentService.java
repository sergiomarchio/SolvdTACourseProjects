package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.DepartmentDAO;
import com.solvd.carfactory.dao.mysql.jdbc.EmployeeDAO;
import com.solvd.carfactory.models.employee.Department;
import com.solvd.carfactory.services.IDepartmentService;
import com.solvd.carfactory.services.IEmployeeService;

public class DepartmentService implements IDepartmentService {
    private IDepartmentDAO departmentDAO = new DepartmentDAO();
    private IEmployeeService employeeService = new EmployeeService();

    @Override
    public Department getDepartmentById(long id) {
        Department department = departmentDAO.getItemById(id);
        department.setHeadEmployee(employeeService.getEmployeeById(department.getHeadEmployee().getId()));
        return department;
    }
}