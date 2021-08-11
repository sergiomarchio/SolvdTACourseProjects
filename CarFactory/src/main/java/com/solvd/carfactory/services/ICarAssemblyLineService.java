package com.solvd.carfactory.services;

import com.solvd.carfactory.models.assemblyline.CarAssemblyLine;

public interface ICarAssemblyLineService {
    CarAssemblyLine getCarAssemblyLineById(long id);
}