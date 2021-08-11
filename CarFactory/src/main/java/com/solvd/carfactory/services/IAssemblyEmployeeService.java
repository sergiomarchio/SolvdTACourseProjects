package com.solvd.carfactory.services;

import com.solvd.carfactory.models.employee.AssemblyEmployee;

public interface IAssemblyEmployeeService {
    AssemblyEmployee getAssemblyEmployeeById(long id);
}