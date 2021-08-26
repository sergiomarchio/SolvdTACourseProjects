package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IBrandDAO;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.services.ICarModelService;
import com.solvd.carfactory.services.IPaintColorService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;

import java.util.stream.Collectors;


public class CarModelService implements ICarModelService {
    private ICarModelDAO carModelDAO = MybatisUtil.getIDao(ICarModelDAO.class); //new CarModelDAO();
    private IBrandDAO brandDAO = MybatisUtil.getIDao(IBrandDAO.class); // new BrandDAO();

    private IPaintColorService paintColorService = new PaintColorService();

    @Override
    public CarModel getCarModelById(long id) {
        CarModel carModel = carModelDAO.getItemById(id);
        carModel.setBrand(brandDAO.getItemById(carModel.getBrand().getId()));

        carModel.setPaintColors(
                carModel.getPaintColors().stream()
                        .map(pc -> paintColorService.getPaintColorById(pc.getId()))
                        .collect(Collectors.toList())
        );
        return carModel;
    }
}