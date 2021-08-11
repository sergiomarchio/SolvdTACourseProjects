package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PaintColor;

public class ModelColor {
    private long id;
    private PaintColor paintColor;
    private CarModel carModel;

    public ModelColor() {
    }
    public ModelColor(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public PaintColor getPaintColor() {
        return paintColor;
    }
    public void setPaintColor(PaintColor paintColor) {
        this.paintColor = paintColor;
    }
    public CarModel getCarModel() {
        return carModel;
    }
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
