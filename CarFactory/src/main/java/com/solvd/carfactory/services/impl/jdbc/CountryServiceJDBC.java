package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.location.Country;
import com.solvd.carfactory.services.ICountryService;

public class CountryServiceJDBC implements ICountryService {
    private ICountryDAO countryDAO = new CountryDAO();

    @Override
    public void createCountry(Country country) {
        countryDAO.createItem(country);
    }

    @Override
    public Country getCountryById(long id) {
        return countryDAO.getItemById(id);
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.updateItem(country);
    }

    @Override
    public void deleteCountry(long id) {
        countryDAO.deleteItem(id);
    }
}
