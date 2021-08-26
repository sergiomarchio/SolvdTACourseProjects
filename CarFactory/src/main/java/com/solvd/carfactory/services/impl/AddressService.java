package com.solvd.carfactory.services.impl;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.services.IAddressService;
import com.solvd.carfactory.services.ICityService;
import com.solvd.carfactory.util.mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AddressService implements IAddressService {
    private IAddressDAO addressDAO = MybatisUtil.getIDao(IAddressDAO.class); //new AddressDAO();
    private ICityService cityService = new CityService();

    @Override
    public Address getAddressById(long id) {
        Address a = addressDAO.getItemById(id);
        a.setCity(cityService.getCityById(a.getCity().getId()));
        return a;
    }

    // TODO: maybe faster if opening 1 connection, creating complete obj and then closing...
//    public Address getMBAddressById(long id) {
//        try(SqlSession ss = MybatisUtil.getSSF().openSession()){
//            Address a = ss.getMapper(IAddressDAO.class).getItemById(1);
//            a.setCity(ss.getMapper(ICityDAO.class).getItemById(a.getCity().getId()));
//            a.
//        }
//    }
}
