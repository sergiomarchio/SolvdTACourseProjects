package com.solvd.carfactory.services;

import com.solvd.carfactory.models.client.Client;

public interface IClientService {
    Client getClientById(long id);
}