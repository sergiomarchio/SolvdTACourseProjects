package com.solvd.carfactory.models.supply;

import com.solvd.carfactory.models.location.Address;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "provider")
@XmlAccessorType(XmlAccessType.FIELD)
public class Provider {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "phone")
    private String phone;
    @XmlElement(name = "address")
    private Address address;

    public Provider() {
    }
    public Provider(long id) {
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
