package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ProviderDAO;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.IProviderService;

public class ProviderServiceJDBC implements IProviderService {
    private IProviderDAO providerDAO = new ProviderDAO();
    private IAddressService addressService = new AddressServiceJDBC();

    @Override
    public Provider getProviderById(long id) {
        Provider provider = providerDAO.getItemById(id);
        provider.setAddress(addressService.getAddressById(provider.getAddress().getId()));
        return provider;
    }
}