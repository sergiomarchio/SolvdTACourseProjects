package com.solvd.carfactory.models.location;


import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("address")
public class Address {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "street")
    private String street;
    @XmlElement(name = "number")
    private String number;
    @XmlElement(name = "dept_number")
    private String deptNumber;
    @XmlElement(name = "zip_code")
    private String zipCode;
    @XmlElement(name = "city")
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
