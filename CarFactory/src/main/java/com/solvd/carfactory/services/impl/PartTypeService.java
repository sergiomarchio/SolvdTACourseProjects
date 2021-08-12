package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IPartTypeDAO;
import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PartTypeDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ProviderDAO;
import com.solvd.carfactory.models.supply.PartType;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.services.IPartTypeService;
import com.solvd.carfactory.services.IProviderService;

public class PartTypeService implements IPartTypeService {
    private IPartTypeDAO partTypeDAO = new PartTypeDAO();
    private IProviderService providerService = new ProviderService();

    @Override
    public PartType getPartTypeById(long id) {
        PartType partType = partTypeDAO.getItemById(id);
        partType.setProvider(providerService.getProviderById(partType.getProvider().getId()));
        return partType;
    }
}