package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.models.supply.PaintColor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Car {
    private long id;
    private LocalDateTime manufacturedDate;
    private CarModel carModel;
    private ClientOrder clientOrder;
    private PaintColor paintColor;

    public Car() {
    }
    public Car(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDateTime getManufacturedDate() {
        return manufacturedDate;
    }
    public void setManufacturedDate(LocalDateTime manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }
    public CarModel getCarModel() {
        return carModel;
    }
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
    public ClientOrder getClientOrder() {
        return clientOrder;
    }
    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }
    public PaintColor getPaintColor() {
        return paintColor;
    }
    public void setPaintColor(PaintColor paintColor) {
        this.paintColor = paintColor;
    }
}
