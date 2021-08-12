package com.solvd.carfactory.services;

import com.solvd.carfactory.models.supply.PaintColor;

public interface IPaintColorService {
    PaintColor getPaintColorById(long id);
}