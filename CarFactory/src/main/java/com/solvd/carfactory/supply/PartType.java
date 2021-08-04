package com.solvd.carfactory.supply;

public class PartType {
    private long id;
    private String name;
    private String type;
    private Provider provider;

    public PartType() {
    }
    public PartType(long id, String name, String type, Provider provider) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.provider = provider;
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
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
