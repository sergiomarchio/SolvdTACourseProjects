package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.services.ICityService;
import com.solvd.carfactory.services.ICountryService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;

public class CityService implements ICityService {
    private ICityDAO cityDAO = MybatisUtil.getIDao(ICityDAO.class); //new CityDAO();
    private ICountryService countryService = new CountryService();

    @Override
    public City getCityById(long id) {
        City c = cityDAO.getItemById(id);
        c.setCountry(countryService.getCountryById(c.getCountry().getId()));
        return c;
    }

}
