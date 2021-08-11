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
import com.solvd.carfactory.services.IModelPartService;
                    
public class ModelPartService implements IModelPartService {
    private IModelPartDAO modelPartDAO = new ModelPartDAO();
    private ICarModelDAO carModelDAO = new CarModelDAO();
    private IPartTypeDAO partTypeDAO = new PartTypeDAO();

    @Override
    public ModelPart getModelPartById(long id) {
        ModelPart modelPart = modelPartDAO.getItemById(id);
        modelPart.setCarModel(carModelDAO.getItemById(modelPart.getCarModel().getId()));
        modelPart.setPartType(partTypeDAO.getItemById(modelPart.getPartType().getId()));
        return modelPart;
    }
}