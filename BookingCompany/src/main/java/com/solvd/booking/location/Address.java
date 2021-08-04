package com.solvd.booking.location;

import java.util.Objects;

public class Address {
    private String street;
    private String number;
    private City city;

    public Address() {}
    public Address(String street, String number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null
                || getClass() != o.getClass()
                || o.hashCode() != this.hashCode())
            return false;

        Address address = (Address) o;
        return number.equals(address.number)
                && street.equals(address.street)
                && city == address.city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city);
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

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
}
