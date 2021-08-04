package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.models.supply.PaintColor;

import java.time.LocalDate;

public class Car {
    private long id;
    private LocalDate manufacturedDate;
    private CarModel model;
    private ClientOrder order;
    private PaintColor color;

    public Car() {
    }
    public Car(long id, CarModel model, PaintColor color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getManufacturedDate() {
        return manufacturedDate;
    }
    public void setManufacturedDate(LocalDate manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }
    public CarModel getModel() {
        return model;
    }
    public void setModel(CarModel model) {
        this.model = model;
    }
    public ClientOrder getOrder() {
        return order;
    }
    public void setOrder(ClientOrder order) {
        this.order = order;
    }
    public PaintColor getColor() {
        return color;
    }
    public void setColor(PaintColor color) {
        this.color = color;
    }
}
