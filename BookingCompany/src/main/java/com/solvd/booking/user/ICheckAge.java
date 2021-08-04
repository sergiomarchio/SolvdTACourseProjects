package com.solvd.booking.user;

@FunctionalInterface
public interface ICheckAge {
    void ageCheck(int value) throws BirthDateException;
}
