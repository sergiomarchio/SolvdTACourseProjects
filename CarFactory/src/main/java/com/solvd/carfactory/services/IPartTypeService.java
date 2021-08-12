package com.solvd.carfactory.services;

import com.solvd.carfactory.models.supply.PartType;

public interface IPartTypeService {
    PartType getPartTypeById(long id);
}