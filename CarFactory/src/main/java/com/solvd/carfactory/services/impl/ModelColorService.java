package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.IModelColorDAO;
import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarModelDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ModelColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PaintColorDAO;
import com.solvd.carfactory.models.car.ModelColor;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.services.IModelColorService;

import java.util.List;
import java.util.stream.Collectors;

public class ModelColorService implements IModelColorService {
    private IModelColorDAO modelColorDAO = new ModelColorDAO();
    private IPaintColorDAO paintColorDAO = new PaintColorDAO();
    private ICarModelDAO carModelDAO = new CarModelDAO();

    @Override
    public ModelColor getModelColorById(long id) {
        ModelColor modelColor = modelColorDAO.getItemById(id);
        modelColor.setPaintColor(paintColorDAO.getItemById(modelColor.getPaintColor().getId()));
        modelColor.setCarModel(carModelDAO.getItemById(modelColor.getCarModel().getId()));
        return modelColor;
    }

    @Override
    public List<PaintColor> getPaintColorsByModelId(long id) {
        return modelColorDAO.getPaintColorsByModelId(id).stream()
                            .map(pc -> paintColorDAO.getItemById(pc.getId()))
                            .collect(Collectors.toList());
    }
}