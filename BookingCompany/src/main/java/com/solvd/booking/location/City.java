package com.solvd.booking.location;

public enum City {
    TORONTO_CAN("Toronto", Country.CAN);


    private String name;
    private Country country;


    City(String name, Country country){
        this.name = name;
        this.country = country;
    }


    public String getName(){
        return name;
    }
    public Country getCountry(){
        return country;
    }
}