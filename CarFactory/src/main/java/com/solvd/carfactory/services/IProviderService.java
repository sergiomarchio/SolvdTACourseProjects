package com.solvd.carfactory.services;

import com.solvd.carfactory.models.supply.Provider;

public interface IProviderService {
    Provider getProviderById(long id);
}