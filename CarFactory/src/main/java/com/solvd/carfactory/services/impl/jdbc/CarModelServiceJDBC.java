package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.IBrandDAO;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.mysql.jdbc.BrandDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarModelDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.services.ICarModelService;
import com.solvd.carfactory.services.IModelColorService;


public class CarModelServiceJDBC implements ICarModelService {
    private ICarModelDAO carModelDAO = new CarModelDAO();
    private IBrandDAO brandDAO = new BrandDAO();
    private IModelColorService modelColorService = new ModelColorServiceJDBC();

    @Override
    public CarModel getCarModelById(long id) {
        CarModel carModel = carModelDAO.getItemById(id);
        carModel.setBrand(brandDAO.getItemById(carModel.getBrand().getId()));
        carModel.setPaintColors(modelColorService.getPaintColorsByModelId(id));

        return carModel;
    }
}