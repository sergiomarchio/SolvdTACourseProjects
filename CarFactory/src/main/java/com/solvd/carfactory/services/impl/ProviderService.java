package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.IProviderService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;

public class ProviderService implements IProviderService {
    private IProviderDAO providerDAO = MybatisUtil.getIDao(IProviderDAO.class); //new ProviderDAO();
    private IAddressService addressService = new AddressService();

    @Override
    public Provider getProviderById(long id) {
        Provider provider = providerDAO.getItemById(id);
        provider.setAddress(addressService.getAddressById(provider.getAddress().getId()));
        return provider;
    }
}