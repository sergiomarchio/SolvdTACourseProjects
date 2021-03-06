package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.services.IPaintColorService;
import com.solvd.carfactory.services.IProviderService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;

public class PaintColorService implements IPaintColorService {
    private IPaintColorDAO paintColorDAO = MybatisUtil.getIDao(IPaintColorDAO.class); //new PaintColorDAO();
    private IProviderService providerService = new ProviderService();

    @Override
    public PaintColor getPaintColorById(long id) {
        PaintColor paintColor = paintColorDAO.getItemById(id);
        paintColor.setProvider(providerService.getProviderById(paintColor.getProvider().getId()));
        return paintColor;
    }
}