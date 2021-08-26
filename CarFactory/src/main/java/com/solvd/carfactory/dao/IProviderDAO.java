package com.solvd.carfactory.dao;

import com.solvd.carfactory.models.supply.Provider;

public interface IProviderDAO extends IBaseDAO<Provider> {
    Provider getNestedProviderById(long id);
}