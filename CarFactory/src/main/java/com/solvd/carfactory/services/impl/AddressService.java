package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CityDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.services.IAddressService;

public class AddressService implements IAddressService {
    private IAddressDAO addressDAO = new AddressDAO();
    private ICityDAO cityDAO = new CityDAO();

    @Override
    public Address getAddressById(long id) {
        Address a = addressDAO.getItemById(id);
        a.setCity(cityDAO.getItemById(a.getCity().getId()));
        return a;
    }
}
