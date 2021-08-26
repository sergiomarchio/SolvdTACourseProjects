package com.solvd.carfactory.models.location;


import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("country")
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

    @Override
    public String toString() {
        return "Country{" +
                "id:" + id +
                ", name:'" + name + '\'' +
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
}
