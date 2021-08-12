package com.solvd.carfactory.models.location;

public class Country {
    private long id;
    private String name;

    public Country() {
    }
    public Country(long id) {
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
