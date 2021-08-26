package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.dao.mysql.jdbc.PaintColorDAO;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.services.IPaintColorService;
import com.solvd.carfactory.services.IProviderService;

public class PaintColorServiceJDBC implements IPaintColorService {
    private IPaintColorDAO paintColorDAO = new PaintColorDAO();
    private IProviderService providerService = new ProviderServiceJDBC();

    @Override
    public PaintColor getPaintColorById(long id) {
        PaintColor paintColor = paintColorDAO.getItemById(id);
        paintColor.setProvider(providerService.getProviderById(paintColor.getProvider().getId()));
        return paintColor;
    }
}