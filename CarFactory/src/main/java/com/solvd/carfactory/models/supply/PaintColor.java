package com.solvd.carfactory.models.supply;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "paint_color")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("paintColor")
public class PaintColor {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "provider")
    private Provider provider;

    public PaintColor() {
    }
    public PaintColor(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PaintColor{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", provider:" + provider +
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
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
