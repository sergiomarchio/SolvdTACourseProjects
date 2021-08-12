package com.solvd.carfactory.models.car;

import com.solvd.carfactory.models.supply.PartType;

public class ModelPart {
    private long id;
    private CarModel carModel;
    private PartType partType;
    private int count;

    public ModelPart() {
    }
    public ModelPart(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public CarModel getCarModel() {
        return carModel;
    }
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
    public PartType getPartType() {
        return partType;
    }
    public void setPartType(PartType partType) {
        this.partType = partType;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
