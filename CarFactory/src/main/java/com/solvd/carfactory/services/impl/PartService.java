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
import com.solvd.carfactory.services.IPartService;
                    
public class PartService implements IPartService {
    private IPartDAO partDAO = new PartDAO();
    private ICarDAO carDAO = new CarDAO();
    private IPartTypeDAO partTypeDAO = new PartTypeDAO();

    @Override
    public Part getPartById(long id) {
        Part part = partDAO.getItemById(id);
        part.setCar(carDAO.getItemById(part.getCar().getId()));
        part.setPartType(partTypeDAO.getItemById(part.getPartType().getId()));
        return part;
    }
}