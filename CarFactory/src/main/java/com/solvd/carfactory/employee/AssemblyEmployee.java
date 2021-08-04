package com.solvd.carfactory.employee;

import com.solvd.carfactory.assemblyline.AssemblyLine;

public class AssemblyEmployee {
    private long id;
    private AssemblyLine assemblyLine;

    public AssemblyEmployee() {
    }
    public AssemblyEmployee(long id, AssemblyLine assemblyLine) {
        this.id = id;
        this.assemblyLine = assemblyLine;
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
