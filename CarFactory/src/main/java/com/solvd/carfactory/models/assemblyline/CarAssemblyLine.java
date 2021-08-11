package com.solvd.carfactory.models.assemblyline;

import com.solvd.carfactory.models.car.Car;

public class CarAssemblyLine {
    private long id;
    private AssemblyLine assemblyLine;
    private Car car;

    public CarAssemblyLine() {
    }
    public CarAssemblyLine(long id) {
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
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
}
