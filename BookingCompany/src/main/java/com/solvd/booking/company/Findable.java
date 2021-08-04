package com.solvd.booking.company;

import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;

public interface Findable {
    boolean isAvailable(LocalDate startDate, LocalDate endDate)
            throws DateOrderException, PastDateException;
    <T> boolean isInLocation(T location);
}
