package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICarDAO;
import com.solvd.carfactory.dao.IPartDAO;
import com.solvd.carfactory.dao.IPartTypeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PartDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PartTypeDAO;
import com.solvd.carfactory.models.car.Car;
import com.solvd.carfactory.models.car.Part;
import com.solvd.carfactory.models.supply.PartType;
import com.solvd.carfactory.services.ICarService;
import com.solvd.carfactory.services.IPartService;
import com.solvd.carfactory.services.IPartTypeService;

public class PartService implements IPartService {
    private IPartDAO partDAO = new PartDAO();
    private ICarService carService = new CarService();
    private IPartTypeService partTypeService = new PartTypeService();

    @Override
    public Part getPartById(long id) {
        Part part = partDAO.getItemById(id);
        part.setCar(carService.getCarById(part.getCar().getId()));
        part.setPartType(partTypeService.getPartTypeById(part.getPartType().getId()));
        return part;
    }
}