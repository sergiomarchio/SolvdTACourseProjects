package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.ModelPart;

public interface IModelPartService {
    ModelPart getModelPartById(long id);
}