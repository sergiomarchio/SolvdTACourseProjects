package com.solvd.carfactory.models.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.carfactory.jackson.YearDeserializer;
import com.solvd.carfactory.jackson.YearSerializer;
import com.solvd.carfactory.jaxb.YearAdapter;
import com.solvd.carfactory.models.supply.PaintColor;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "car_model")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("carModel")
public class CarModel {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "year")
    @XmlJavaTypeAdapter(YearAdapter.class)
    @JsonSerialize(using = YearSerializer.class)
    @JsonDeserialize(using = YearDeserializer.class)
    private Year year;
    @XmlElement(name = "fuel_type")
    private String fuelType;
    @XmlElement(name = "unitary_price")
    private double unitaryPrice;
    @XmlElement(name = "brand")
    private Brand brand;
    @XmlElementWrapper(name = "paint_colors")
    @XmlElement(name = "paint_color")
    private List<PaintColor> paintColors;

    public CarModel() {
        this.paintColors = new LinkedList<>();
    }
    public CarModel(long id) {
        this.id = id;
        this.paintColors = new LinkedList<>();
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Year getYear() {
        return year;
    }
    public void setYear(Year year) {
        this.year = year;
    }
    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    public double getUnitaryPrice() {
        return unitaryPrice;
    }
    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public List<PaintColor> getPaintColors() {
        return paintColors;
    }
    public void setPaintColors(List<PaintColor> paintColors) {
        this.paintColors = paintColors;
    }
}
