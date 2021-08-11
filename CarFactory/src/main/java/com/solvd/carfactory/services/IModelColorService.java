package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.ModelColor;

public interface IModelColorService {
    ModelColor getModelColorById(long id);
}