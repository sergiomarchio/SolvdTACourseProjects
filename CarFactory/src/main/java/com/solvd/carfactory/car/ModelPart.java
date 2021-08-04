package com.solvd.carfactory.car;

import com.solvd.carfactory.supply.PartType;

public class ModelPart {
    private long id;
    private CarModel model;
    private PartType type;
    private int count;

    public ModelPart() {
    }
    public ModelPart(long id, CarModel model, PartType type, int count) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.count = count;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public CarModel getModel() {
        return model;
    }
    public void setModel(CarModel model) {
        this.model = model;
    }
    public PartType getType() {
        return type;
    }
    public void setType(PartType type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
