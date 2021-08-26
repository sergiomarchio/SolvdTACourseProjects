package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.IModelColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ModelColorDAO;
import com.solvd.carfactory.models.car.ModelColor;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.services.ICarModelService;
import com.solvd.carfactory.services.IModelColorService;
import com.solvd.carfactory.services.IPaintColorService;
import com.solvd.carfactory.services.impl.CarModelService;

import java.util.List;
import java.util.stream.Collectors;

public class ModelColorServiceJDBC implements IModelColorService {
    private IModelColorDAO modelColorDAO = new ModelColorDAO();
    private IPaintColorService paintColorService = new PaintColorServiceJDBC();

    @Override
    public ModelColor getModelColorById(long id) {
        ICarModelService carModelService = new CarModelService();

        ModelColor modelColor = modelColorDAO.getItemById(id);
        modelColor.setPaintColor(paintColorService.getPaintColorById(modelColor.getPaintColor().getId()));
        modelColor.setCarModel(carModelService.getCarModelById(modelColor.getCarModel().getId()));
        return modelColor;
    }

    @Override
    public List<PaintColor> getPaintColorsByModelId(long id) {
        return modelColorDAO.getPaintColorsByModelId(id).stream()
                            .map(pc -> paintColorService.getPaintColorById(pc.getId()))
                            .collect(Collectors.toList());
    }
}