package com.solvd.carfactory.models.location;

public class City {
    private long id;
    private String name;
    private Country country;

    public City() {
    }
    public City(long id){
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
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
}
