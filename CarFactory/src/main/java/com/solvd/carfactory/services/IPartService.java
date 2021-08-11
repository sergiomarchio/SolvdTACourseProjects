package com.solvd.carfactory.services;

import com.solvd.carfactory.models.car.Part;

public interface IPartService {
    Part getPartById(long id);
}