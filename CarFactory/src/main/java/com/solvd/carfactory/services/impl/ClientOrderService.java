package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IClientDAO;
import com.solvd.carfactory.dao.IClientOrderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ClientDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ClientOrderDAO;
import com.solvd.carfactory.models.client.Client;
import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.services.IClientOrderService;
                    
public class ClientOrderService implements IClientOrderService {
    private IClientOrderDAO clientOrderDAO = new ClientOrderDAO();
    private IClientDAO clientDAO = new ClientDAO();

    @Override
    public ClientOrder getClientOrderById(long id) {
        ClientOrder clientOrder = clientOrderDAO.getItemById(id);
        clientOrder.setClient(clientDAO.getItemById(clientOrder.getClient().getId()));
        return clientOrder;
    }
}