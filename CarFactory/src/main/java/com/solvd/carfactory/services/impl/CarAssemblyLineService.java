package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAssemblyLineDAO;
import com.solvd.carfactory.dao.ICarAssemblyLineDAO;
import com.solvd.carfactory.dao.ICarDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AssemblyLineDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarAssemblyLineDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarDAO;
import com.solvd.carfactory.models.assemblyline.AssemblyLine;
import com.solvd.carfactory.models.assemblyline.CarAssemblyLine;
import com.solvd.carfactory.models.car.Car;
import com.solvd.carfactory.services.ICarAssemblyLineService;
                    
public class CarAssemblyLineService implements ICarAssemblyLineService {
    private ICarAssemblyLineDAO carAssemblyLineDAO = new CarAssemblyLineDAO();
    private IAssemblyLineDAO assemblyLineDAO = new AssemblyLineDAO();
    private ICarDAO carDAO = new CarDAO();

    @Override
    public CarAssemblyLine getCarAssemblyLineById(long id) {
        CarAssemblyLine carAssemblyLine = carAssemblyLineDAO.getItemById(id);
        carAssemblyLine.setAssemblyLine(assemblyLineDAO.getItemById(carAssemblyLine.getAssemblyLine().getId()));
        carAssemblyLine.setCar(carDAO.getItemById(carAssemblyLine.getCar().getId()));
        return carAssemblyLine;
    }
}