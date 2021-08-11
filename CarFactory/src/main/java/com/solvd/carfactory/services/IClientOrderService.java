package com.solvd.carfactory.services;

import com.solvd.carfactory.models.client.ClientOrder;

public interface IClientOrderService {
    ClientOrder getClientOrderById(long id);
}