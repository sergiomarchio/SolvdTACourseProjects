package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.models.location.Country;
import com.solvd.carfactory.services.ICountryService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;

public class CountryService implements ICountryService {
    private ICountryDAO countryDAO = MybatisUtil.getIDao(ICountryDAO.class);

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
