package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ProviderDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.IProviderService;
                    
public class ProviderService implements IProviderService {
    private IProviderDAO providerDAO = new ProviderDAO();
    private IAddressService addressService = new AddressService();

    @Override
    public Provider getProviderById(long id) {
        Provider provider = providerDAO.getItemById(id);
        provider.setAddress(addressService.getAddressById(provider.getAddress().getId()));
        return provider;
    }
}