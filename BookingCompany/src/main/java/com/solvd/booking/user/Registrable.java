package com.solvd.booking.user;

import com.solvd.booking.company.BookingCompany;

public interface Registrable {
    void register(BookingCompany company) throws FutureDateException, BirthDateException;
    void unregister(BookingCompany company);
}
