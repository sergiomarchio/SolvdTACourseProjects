package com.solvd.carfactory.models.car;

public class Brand {
    private long id;
    private String name;

    public Brand() {
    }
    public Brand(long id) {
        this.id = id;
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
}
