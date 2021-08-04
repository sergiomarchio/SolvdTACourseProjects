package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PaintColor;

public class ModelColor {
    private long id;
    private PaintColor color;
    private CarModel model;

    public ModelColor() {
    }
    public ModelColor(long id, PaintColor color, CarModel model) {
        this.id = id;
        this.color = color;
        this.model = model;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public PaintColor getColor() {
        return color;
    }
    public void setColor(PaintColor color) {
        this.color = color;
    }
    public CarModel getModel() {
        return model;
    }
    public void setModel(CarModel model) {
        this.model = model;
    }
}
