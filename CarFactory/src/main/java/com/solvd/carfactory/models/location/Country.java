package com.solvd.carfactory.models.location;


import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
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
