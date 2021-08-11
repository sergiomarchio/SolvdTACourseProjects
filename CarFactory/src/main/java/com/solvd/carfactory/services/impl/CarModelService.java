package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IBrandDAO;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.mysql.jdbc.BrandDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarModelDAO;
import com.solvd.carfactory.models.car.Brand;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.services.ICarModelService;
                    
public class CarModelService implements ICarModelService {
    private ICarModelDAO carModelDAO = new CarModelDAO();
    private IBrandDAO brandDAO = new BrandDAO();

    @Override
    public CarModel getCarModelById(long id) {
        CarModel carModel = carModelDAO.getItemById(id);
        carModel.setBrand(brandDAO.getItemById(carModel.getBrand().getId()));
        return carModel;
    }
}