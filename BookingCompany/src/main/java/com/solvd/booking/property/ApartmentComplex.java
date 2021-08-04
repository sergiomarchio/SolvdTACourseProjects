package com.solvd.booking.property;

import com.solvd.booking.location.Address;
import com.solvd.booking.rentalunit.Apartment;
import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.util.Map;

public class ApartmentComplex extends Property {

    private Map<Long, Apartment> apartments;


    public ApartmentComplex() {}
    public ApartmentComplex(long id, String name, Address address) {
        super(id, name, address);
    }


    @Override
    public boolean isAvailable(LocalDate startDate, LocalDate endDate)
            throws DateOrderException, PastDateException {
        return areRentalUnitsAvailable(apartments, startDate, endDate);
    }


    public Map<Long, Apartment> getApartments() {
        return apartments;
    }
    public void setApartments(Map<Long, Apartment> apartments) {
        this.apartments = apartments;
    }
}
