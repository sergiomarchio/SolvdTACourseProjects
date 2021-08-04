package com.solvd.carfactory.main;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICountryDAO;
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


        ICountryDAO countryDAO = new CountryDAO();

        LOGGER.debug("Read country from db: " + countryDAO.getItemById(1).getName());

        Country japan = new Country();
        japan.setName("iapan");
        // Create
        countryDAO.createItem(japan);
        LOGGER.debug("Created country with id: " + japan.getId() + " Name: " + japan.getName());
        // Read
        LOGGER.debug("Read country with id: " + countryDAO.getItemById(japan.getId()).getId()
                + " Name: " + countryDAO.getItemById(japan.getId()).getName());
        // Update
        japan.setName("Japan");
        countryDAO.updateItem(japan);
        LOGGER.debug("Updated country with id: " + japan.getId()
                + " Name: " + countryDAO.getItemById(japan.getId()).getName());
        // Delete
        countryDAO.deleteItem(japan.getId());
        LOGGER.debug("Deleted item.");
        countryDAO.getItemById(japan.getId());

        pool.closeAll();
    }
}
