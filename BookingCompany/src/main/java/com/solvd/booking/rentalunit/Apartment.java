package com.solvd.booking.rentalunit;


public class Apartment extends RentalUnit {
    private boolean balconyAvailable;


    public Apartment(){}
    public Apartment(long id, byte capacity) {
        super(id, capacity);
    }


    public boolean isBalconyAvailable(){
        return balconyAvailable;
    }
    public void setBalconyAvailable(boolean balconyAvailable){
        this.balconyAvailable = balconyAvailable;
    }
}
