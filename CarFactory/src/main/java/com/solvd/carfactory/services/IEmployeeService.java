package com.solvd.carfactory.services;

import com.solvd.carfactory.models.employee.Employee;

public interface IEmployeeService {
    Employee getEmployeeById(long id);
}