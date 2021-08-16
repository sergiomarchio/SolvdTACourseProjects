package com.solvd.carfactory.models.car;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "brand")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("brand")
public class Brand {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;

    public Brand() {
    }
    public Brand(long id) {
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
