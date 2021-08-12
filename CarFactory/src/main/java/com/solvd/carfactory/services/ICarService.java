package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.Car;

public interface ICarService {
    Car getCarById(long id);
}