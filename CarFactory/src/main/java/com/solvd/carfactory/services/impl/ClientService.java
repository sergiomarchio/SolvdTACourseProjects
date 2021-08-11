package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.IClientDAO;
import com.solvd.carfactory.dao.mysql.jdbc.AddressDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ClientDAO;
import com.solvd.carfactory.models.client.Client;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.services.IClientService;
                    
public class ClientService implements IClientService {
    private IClientDAO clientDAO = new ClientDAO();
    private IAddressDAO addressDAO = new AddressDAO();

    @Override
    public Client getClientById(long id) {
        Client client = clientDAO.getItemById(id);
        client.setAddress(addressDAO.getItemById(client.getAddress().getId()));
        return client;
    }
}