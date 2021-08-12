package com.solvd.carfactory.dao;

import com.solvd.carfactory.models.car.ModelColor;
import com.solvd.carfactory.models.supply.PaintColor;

import java.util.List;


public interface IModelColorDAO extends IBaseDAO<ModelColor> {
    List<PaintColor> getPaintColorsByModelId(long id);
}