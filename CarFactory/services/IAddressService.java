package com.solvd.carfactory.services;

import com.solvd.carfactory.models.location.Address;

public interface IAddressService {
    Address getAddressById(long id);
}