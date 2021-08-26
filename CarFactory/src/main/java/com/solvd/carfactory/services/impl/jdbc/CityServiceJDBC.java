package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CityDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.services.ICityService;
import com.solvd.carfactory.services.ICountryService;

public class CityServiceJDBC implements ICityService {
    private ICityDAO cityDAO = new CityDAO();
    private ICountryService countryService = new CountryServiceJDBC();

    @Override
    public City getCityById(long id) {
        City c = cityDAO.getItemById(id);
        c.setCountry(countryService.getCountryById(c.getCountry().getId()));
        return c;
    }

}
