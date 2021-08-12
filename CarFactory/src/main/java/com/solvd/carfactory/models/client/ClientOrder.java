package com.solvd.carfactory.models.client;

import java.time.LocalDateTime;

public class ClientOrder {
    private long id;
    private LocalDateTime creationDate;
    private double discountPercent;
    private LocalDateTime deliveryDate;
    private Client client;

    public ClientOrder() {
    }
    public ClientOrder(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
