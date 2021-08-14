package com.solvd.carfactory.services;

import com.solvd.carfactory.models.employee.Employee;

public interface IEmployeeService {
    Employee getEmployeeById(long id);

    // Gets employee without filling department to avoid infinite loop for head employee
    Employee getEmployeeByIdNoDept(long id);
}