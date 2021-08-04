package com.solvd.carfactory.client;

import java.time.LocalDate;

public class ClientOrder {
    private long id;
    private LocalDate creationDate;
    private double discountPercent;
    private LocalDate deliveryDate;
    private Client client;

    public ClientOrder() {
    }
    public ClientOrder(long id, LocalDate creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
