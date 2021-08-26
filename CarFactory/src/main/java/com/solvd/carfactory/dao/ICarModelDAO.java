package com.solvd.carfactory.dao;

import com.solvd.carfactory.models.car.CarModel;

public interface ICarModelDAO extends IBaseDAO<CarModel> {
    CarModel getFullCarModelById(long id);
    CarModel getNestedCarModelById(long id);
}