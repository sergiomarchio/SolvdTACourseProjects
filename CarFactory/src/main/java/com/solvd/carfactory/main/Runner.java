package com.solvd.carfactory.main;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import com.solvd.carfactory.sax.CountrySAX;
import com.solvd.carfactory.sax.XMLRead;
import com.solvd.carfactory.services.ICityService;
import com.solvd.carfactory.services.impl.CityService;
import org.apache.log4j.Logger;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);

    private static void crudOperations(){
        ICountryDAO countryDAO = new CountryDAO();

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
        LOGGER.debug("Deleted item with id: " + japan.getId() + " Name: " + japan.getName());
        LOGGER.debug("Trying to read non existent element to check deletion:");
        countryDAO.getItemById(japan.getId());


        ICityService cityService = new CityService();
        City city = cityService.getCityById(1);
        LOGGER.debug("Got city from CityService with id: " + city.getId() + " Name: " + city.getName()
                + " Country: " + city.getCountry().getName());


        ConnectionPool.getInstance().closeAll();
    }

    private static void sax(){

        Country country = XMLRead.xmlRead("src/main/resources/xml/country.xml", new CountrySAX());

        LOGGER.debug(country.getId() + " - " + country.getName());
    }

    public final static void main(String[] args){
        // crudOperations();

        sax();

    }
}
