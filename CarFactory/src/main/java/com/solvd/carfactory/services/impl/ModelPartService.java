package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.IModelPartDAO;
import com.solvd.carfactory.dao.IPartTypeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CarModelDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ModelPartDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PartTypeDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.car.ModelPart;
import com.solvd.carfactory.models.supply.PartType;
import com.solvd.carfactory.services.ICarModelService;
import com.solvd.carfactory.services.IModelPartService;
import com.solvd.carfactory.services.IPartTypeService;

public class ModelPartService implements IModelPartService {
    private IModelPartDAO modelPartDAO = new ModelPartDAO();
    private ICarModelService carModelService = new CarModelService();
    private IPartTypeService partTypeService = new PartTypeService();

    @Override
    public ModelPart getModelPartById(long id) {
        ModelPart modelPart = modelPartDAO.getItemById(id);
        modelPart.setCarModel(carModelService.getCarModelById(modelPart.getCarModel().getId()));
        modelPart.setPartType(partTypeService.getPartTypeById(modelPart.getPartType().getId()));
        return modelPart;
    }
}