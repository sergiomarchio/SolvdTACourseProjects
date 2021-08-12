package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.ModelColor;
import com.solvd.carfactory.models.supply.PaintColor;

import java.util.List;

public interface IModelColorService {
    ModelColor getModelColorById(long id);
    List<PaintColor> getPaintColorsByModelId(long id);
}