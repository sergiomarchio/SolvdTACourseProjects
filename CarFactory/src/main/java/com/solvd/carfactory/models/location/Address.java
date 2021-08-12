package com.solvd.carfactory.models.location;

public class Address {
    private long id;
    private String street;
    private String number;
    private String deptNumber;
    private String zipCode;
    private City city;

    public Address() {
    }
    public Address(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getDeptNumber() {
        return deptNumber;
    }
    public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
}
