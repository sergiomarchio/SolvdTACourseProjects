package com.solvd.carfactory.models.assemblyline;

public class AssemblyLine {
    private long id;
    private String name;

    public AssemblyLine() {
    }
    public AssemblyLine(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
