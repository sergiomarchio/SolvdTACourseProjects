package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICarDAO;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.IClientOrderDAO;
import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarModelDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ClientOrderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PaintColorDAO;
import com.solvd.carfactory.models.car.Car;
import com.solvd.carfactory.services.ICarModelService;
import com.solvd.carfactory.services.ICarService;
import com.solvd.carfactory.services.IClientOrderService;
import com.solvd.carfactory.services.IPaintColorService;

public class CarService implements ICarService {
    private ICarDAO carDAO = new CarDAO();
    private ICarModelService carModelService = new CarModelService();
    private IClientOrderService clientOrderService = new ClientOrderService();
    private IPaintColorService paintColorService = new PaintColorService();

    @Override
    public Car getCarById(long id) {
        Car car = carDAO.getItemById(id);
        car.setCarModel(carModelService.getCarModelById(car.getCarModel().getId()));
        car.setClientOrder(clientOrderService.getClientOrderById(car.getClientOrder().getId()));
        car.setPaintColor(paintColorService.getPaintColorById(car.getPaintColor().getId()));
        return car;
    }
}