package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAssemblyEmployeeDAO;
import com.solvd.carfactory.dao.IAssemblyLineDAO;
import com.solvd.carfactory.dao.IEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AssemblyEmployeeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AssemblyLineDAO;
import com.solvd.carfactory.dao.mysql.jdbc.EmployeeDAO;
import com.solvd.carfactory.models.assemblyline.AssemblyLine;
import com.solvd.carfactory.models.employee.AssemblyEmployee;
import com.solvd.carfactory.models.employee.Employee;
import com.solvd.carfactory.services.IAssemblyEmployeeService;
import com.solvd.carfactory.services.IEmployeeService;

public class AssemblyEmployeeService implements IAssemblyEmployeeService {
    private IAssemblyEmployeeDAO assemblyEmployeeDAO = new AssemblyEmployeeDAO();
    private IEmployeeService employeeService = new EmployeeService();
    private IAssemblyLineDAO assemblyLineDAO = new AssemblyLineDAO();

    @Override
    public AssemblyEmployee getAssemblyEmployeeById(long id) {
        AssemblyEmployee assemblyEmployee = assemblyEmployeeDAO.getItemById(id);

        Employee employee = employeeService.getEmployeeById(id);
        assemblyEmployee.setFirstName(employee.getFirstName());
        assemblyEmployee.setLastName(employee.getLastName());
        assemblyEmployee.setEmail(employee.getEmail());
        assemblyEmployee.setPhone(employee.getPhone());
        assemblyEmployee.setShift(employee.getShift());
        assemblyEmployee.setSalary(employee.getSalary());
        assemblyEmployee.setAddress(employee.getAddress());
        assemblyEmployee.setDepartment(employee.getDepartment());

        assemblyEmployee.setAssemblyLine(assemblyLineDAO.getItemById(assemblyEmployee.getAssemblyLine().getId()));

        return assemblyEmployee;
    }
}