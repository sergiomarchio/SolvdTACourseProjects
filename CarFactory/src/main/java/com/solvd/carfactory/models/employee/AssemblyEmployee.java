package com.solvd.carfactory.models.employee;

import com.solvd.carfactory.models.assemblyline.AssemblyLine;

public class AssemblyEmployee extends Employee{
    private long id;
    private AssemblyLine assemblyLine;

    public AssemblyEmployee() {
    }
    public AssemblyEmployee(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }
    public void setAssemblyLine(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }
}
