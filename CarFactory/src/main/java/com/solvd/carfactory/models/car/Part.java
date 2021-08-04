package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PartType;

public class Part {
    private long id;
    private String serialNumber;
    private Car car;
    private PartType type;

    public Part() {
    }
    public Part(long id, String serialNumber, Car car, PartType type) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.car = car;
        this.type = type;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public PartType getType() {
        return type;
    }
    public void setType(PartType type) {
        this.type = type;
    }
}
