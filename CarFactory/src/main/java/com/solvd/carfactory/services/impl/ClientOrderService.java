package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IClientOrderDAO;
import com.solvd.carfactory.dao.mysql.jdbc.ClientOrderDAO;
import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.services.IClientOrderService;
import com.solvd.carfactory.services.IClientService;

public class ClientOrderService implements IClientOrderService {
    private IClientOrderDAO clientOrderDAO = new ClientOrderDAO();
    private IClientService clientService = new ClientService();

    @Override
    public ClientOrder getClientOrderById(long id) {
        ClientOrder clientOrder = clientOrderDAO.getItemById(id);
        clientOrder.setClient(clientService.getClientById(clientOrder.getClient().getId()));
        return clientOrder;
    }
}