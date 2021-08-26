package com.solvd.carfactory.services;

import com.solvd.carfactory.models.location.Country;

public interface ICountryService {
    void createCountry(Country country);
    Country getCountryById(long id);
    void updateCountry(Country country);
    void deleteCountry(long id);
}
