package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PartType;

public class Part {
    private long id;
    private String serialNumber;
    private Car car;
    private PartType partType;

    public Part() {
    }
    public Part(long id) {
        this.id = id;
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
    public PartType getPartType() {
        return partType;
    }
    public void setPartType(PartType partType) {
        this.partType = partType;
    }
}
