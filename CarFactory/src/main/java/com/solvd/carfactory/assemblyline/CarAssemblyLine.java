package com.solvd.carfactory.assemblyline;

import com.solvd.carfactory.car.Car;

public class CarAssemblyLine {
    private long id;
    private AssemblyLine assemblyLine;
    private Car car;

    public CarAssemblyLine() {
    }
    public CarAssemblyLine(long id, AssemblyLine assemblyLine, Car car) {
        this.id = id;
        this.assemblyLine = assemblyLine;
        this.car = car;
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
