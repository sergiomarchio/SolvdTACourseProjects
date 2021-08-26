package com.solvd.carfactory.services.impl.jdbc;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.ICityService;

public class AddressServiceJDBC implements IAddressService {
    private IAddressDAO addressDAO = new AddressDAO();
    private ICityService cityService = new CityServiceJDBC();

    @Override
    public Address getAddressById(long id) {
        Address a = addressDAO.getItemById(id);
        a.setCity(cityService.getCityById(a.getCity().getId()));
        return a;
    }
}
