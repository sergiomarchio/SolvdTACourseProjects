package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.CarModel;

public interface ICarModelService {
    CarModel getCarModelById(long id);
}