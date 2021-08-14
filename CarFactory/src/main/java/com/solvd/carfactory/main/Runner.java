package com.solvd.carfactory.main;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import com.solvd.carfactory.sax.*;
import com.solvd.carfactory.services.ICityService;
import com.solvd.carfactory.services.impl.CarModelService;
import com.solvd.carfactory.services.impl.CityService;
import com.solvd.carfactory.services.impl.DepartmentService;
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

    private static void magicSax(){
        Country country = XMLRead.xmlRead("src/main/resources/xml/country.xml", new UniversalSAX<Country>());
        LOGGER.debug("Read Country: " + country.getId() + " - " + country.getName());

        City city = XMLRead.xmlRead("src/main/resources/xml/city.xml", new UniversalSAX<City>());
        LOGGER.debug("Read City: " + city.getId() + " - " + city.getName() + " - " + city.getCountry().getName());
    }

    private static void xmlWrite(){
        Address address = XMLRead.xmlRead("src/main/resources/xml/address.xml", new UniversalSAX<Address>());
        new XMLWrite().xmlWrite("src/main/resources/output/test.xml", address);
    }

    private static void saxWithList(){
        CarModel carModel = XMLRead.xmlRead("src/main/resources/xml/car_model.xml", new UniversalSAX<>());
        LOGGER.debug("Car model: " + carModel.getName() + ", color " + carModel.getPaintColors().get(0).getName());

        new XMLWrite().xmlWrite("src/main/resources/output/list_test.xml", carModel);

        new XMLWrite().xmlWrite("src/main/resources/output/model_from_db.xml",
                new CarModelService().getCarModelById(1));
    }

    public final static void main(String[] args){
        //crudOperations();

        //magicSax();

        //xmlWrite();

        saxWithList();
    }
}
