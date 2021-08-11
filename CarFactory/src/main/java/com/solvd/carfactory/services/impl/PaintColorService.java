package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PaintColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ProviderDAO;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.services.IPaintColorService;
                    
public class PaintColorService implements IPaintColorService {
    private IPaintColorDAO paintColorDAO = new PaintColorDAO();
    private IProviderDAO providerDAO = new ProviderDAO();

    @Override
    public PaintColor getPaintColorById(long id) {
        PaintColor paintColor = paintColorDAO.getItemById(id);
        paintColor.setProvider(providerDAO.getItemById(paintColor.getProvider().getId()));
        return paintColor;
    }
}