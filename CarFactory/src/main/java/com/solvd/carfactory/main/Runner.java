package com.solvd.carfactory.main;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);

    public final static void main(String[] args){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error loading mysql driver class: \n" + e);
        }

        ConnectionPool pool = ConnectionPool.init(5,
                "52.59.193.212:3306", "car_factory_sergio_marchio",
                "root", "devintern");

        Country country = new CountryDAO().getItemById(1);
        LOGGER.debug("Read country from db: " + country.getName());

        pool.closeAll();
    }
}
