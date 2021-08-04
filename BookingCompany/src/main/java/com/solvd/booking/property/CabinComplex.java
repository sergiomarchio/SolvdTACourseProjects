package com.solvd.booking.property;

import com.solvd.booking.location.Address;
import com.solvd.booking.rentalunit.Cabin;
import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.util.Map;

public class CabinComplex extends Property {
    private Map<Long, Cabin> cabins;


    public CabinComplex() {}
    public CabinComplex(long id, String name, Address address) {
        super(id, name, address);
    }


    @Override
    public boolean isAvailable(LocalDate startDate, LocalDate endDate)
            throws DateOrderException, PastDateException {
        return areRentalUnitsAvailable(cabins, startDate, endDate);
    }


    public Map<Long, Cabin> getCabins() {
        return cabins;
    }
    public void setCabins(Map<Long, Cabin> cabins) {
        this.cabins = cabins;
    }


}
