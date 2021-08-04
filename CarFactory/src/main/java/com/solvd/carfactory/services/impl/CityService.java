package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CityDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.services.ICityService;

public class CityService implements ICityService {
    private ICityDAO cityDAO = new CityDAO();
    private ICountryDAO countryDAO = new CountryDAO();

    @Override
    public City getCityById(long id) {
        City c = cityDAO.getItemById(id);
        c.setCountry(countryDAO.getItemById(c.getCountry().getId()));
        return c;
    }
}
