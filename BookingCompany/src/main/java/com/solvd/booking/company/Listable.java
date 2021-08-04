package com.solvd.booking.company;

import com.solvd.booking.company.BookingCompany;

public interface Listable {
    void list(BookingCompany company);
    void unlist(BookingCompany company);
}
