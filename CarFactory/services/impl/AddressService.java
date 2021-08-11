package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CityDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.services.IAddressService;
                    
public class AddressService implements IAddressService {
    private IAddressDAO addressDAO = new AddressDAO();
    private ICityDAO cityDAO = new CityDAO();

    @Override
    public Address getAddressById(long id) {
        Address address = addressDAO.getItemById(id);
        address.setCity(cityDAO.getItemById(address.getCity().getId()));
        return address;
    }
}