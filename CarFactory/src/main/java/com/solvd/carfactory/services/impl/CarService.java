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
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.services.ICarService;
                    
public class CarService implements ICarService {
    private ICarDAO carDAO = new CarDAO();
    private ICarModelDAO carModelDAO = new CarModelDAO();
    private IClientOrderDAO clientOrderDAO = new ClientOrderDAO();
    private IPaintColorDAO paintColorDAO = new PaintColorDAO();

    @Override
    public Car getCarById(long id) {
        Car car = carDAO.getItemById(id);
        car.setCarModel(carModelDAO.getItemById(car.getCarModel().getId()));
        car.setClientOrder(clientOrderDAO.getItemById(car.getClientOrder().getId()));
        car.setPaintColor(paintColorDAO.getItemById(car.getPaintColor().getId()));
        return car;
    }
}