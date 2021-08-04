package com.solvd.carfactory.models.supply;

public class PaintColor {
    private long id;
    private String name;
    private Provider provider;

    public PaintColor() {
    }
    public PaintColor(long id, String name) {
        this.id = id;
        this.name = name;
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
