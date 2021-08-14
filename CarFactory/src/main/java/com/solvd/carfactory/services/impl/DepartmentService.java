package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IDepartmentDAO;
import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.DepartmentDAO;
import com.solvd.carfactory.dao.mysql.jdbc.EmployeeDAO;
import com.solvd.carfactory.models.employee.Department;
import com.solvd.carfactory.models.employee.Employee;
import com.solvd.carfactory.services.IDepartmentService;
import com.solvd.carfactory.services.IEmployeeService;

public class DepartmentService implements IDepartmentService {
    private IDepartmentDAO departmentDAO = new DepartmentDAO();
    private IEmployeeService employeeService = new EmployeeService();

    @Override
    public Department getDepartmentById(long id) {
        Department department = departmentDAO.getItemById(id);

        Employee headEmployee = employeeService.getEmployeeByIdNoDept(department.getHeadEmployee().getId());
        if (id != headEmployee.getDepartment().getId()) {
            throw new IllegalArgumentException("Head Employee (id: " + headEmployee.getId()
                    + ") has Department id: " + headEmployee.getDepartment().getId()
                    + " which is not consistent with department id: " + id);
        }
        headEmployee.setDepartment(department);
        department.setHeadEmployee(headEmployee);

        return department;
    }
}