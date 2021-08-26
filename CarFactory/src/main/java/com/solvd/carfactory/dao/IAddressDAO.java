package com.solvd.carfactory.dao;

import com.solvd.carfactory.models.location.Address;

public interface IAddressDAO extends IBaseDAO<Address> {
    Address getFullAddressById(long id);
    Address getNestedAddressById(long id);
}
