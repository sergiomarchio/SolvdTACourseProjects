package com.solvd.booking.rentalunit;


public class Cabin extends RentalUnit {
    private boolean fireplaceAvailable;
    private boolean bbqAvailable;


    public Cabin() {}
    public Cabin(long id, byte capacity) {
        super(id, capacity);
    }


    public boolean isFireplaceAvailable() {
        return fireplaceAvailable;
    }
    public void setFireplaceAvailable(boolean fireplaceAvailable) {
        this.fireplaceAvailable = fireplaceAvailable;
    }

    public boolean isBbqAvailable() {
        return bbqAvailable;
    }
    public void setBbqAvailable(boolean bbqAvailable) {
        this.bbqAvailable = bbqAvailable;
    }
}
