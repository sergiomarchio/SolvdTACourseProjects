package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PaintColor;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

public class CarModel {
    private long id;
    private String name;
    private String type;
    private Year year;
    private String fuelType;
    private double unitaryPrice;
    private Brand brand;
    private List<PaintColor> paintColors;

    public CarModel() {
        this.paintColors = new LinkedList<>();
    }
    public CarModel(long id) {
        this.id = id;
        this.paintColors = new LinkedList<>();
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Year getYear() {
        return year;
    }
    public void setYear(Year year) {
        this.year = year;
    }
    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    public double getUnitaryPrice() {
        return unitaryPrice;
    }
    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public List<PaintColor> getPaintColors() {
        return paintColors;
    }
    public void setPaintColors(List<PaintColor> paintColors) {
        this.paintColors = paintColors;
    }
}
