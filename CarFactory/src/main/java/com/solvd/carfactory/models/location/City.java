package com.solvd.carfactory.models.location;


import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("city")
public class City {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "country")
    private Country country;

    public City() {
    }
    public City(long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", country:" + country +
                '}';
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
