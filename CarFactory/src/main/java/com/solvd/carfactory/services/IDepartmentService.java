package com.solvd.carfactory.services;

import com.solvd.carfactory.models.employee.Department;

public interface IDepartmentService {
    Department getDepartmentById(long id);
}