package com.solvd.carfactory.dao;

import com.solvd.carfactory.models.location.City;

public interface ICityDAO extends IBaseDAO<City> {
    City getFullCityById(long id);
    City getNestedCityById(long id);
}
