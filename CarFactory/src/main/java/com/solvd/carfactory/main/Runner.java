package com.solvd.carfactory.main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);

    private static void crudOperations() {
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

    private static void magicSax() {
        Country country = XMLRead.xmlRead("src/main/resources/xml/country.xml", new UniversalSAX<Country>());
        LOGGER.debug("Read Country: " + country.getId() + " - " + country.getName());

        City city = XMLRead.xmlRead("src/main/resources/xml/city.xml", new UniversalSAX<City>());
        LOGGER.debug("Read City: " + city.getId() + " - " + city.getName() + " - " + city.getCountry().getName());
    }

    private static void xmlWrite() {
        Address address = XMLRead.xmlRead("src/main/resources/xml/address.xml", new UniversalSAX<Address>());
        new XMLWrite().xmlWrite("src/main/resources/output/test.xml", address);
    }

    private static void saxWithList() {
        CarModel carModel = XMLRead.xmlRead("src/main/resources/xml/car_model.xml", new UniversalSAX<>());
        LOGGER.debug("Car model: " + carModel.getName() + ", color " + carModel.getPaintColors().get(0).getName());

        new XMLWrite().xmlWrite("src/main/resources/output/list_test.xml", carModel);

        new XMLWrite().xmlWrite("src/main/resources/output/model_from_db.xml",
                new CarModelService().getCarModelById(1));
    }

    public static void jaxbAddress() {
        try {
            JAXBContext c = JAXBContext.newInstance(Address.class);
            Unmarshaller u = c.createUnmarshaller();
            Address address = (Address) u.unmarshal(new File("src/main/resources/xml/address.xml"));

            LOGGER.debug("Unmarshalled address: " + address.getStreet() + " " + address.getNumber()
                    + ", " + address.getCity().getName() + ", " + address.getCity().getCountry().getName());

            Marshaller m = c.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(address, new File("src/main/resources/output/address_marshall.xml"));

        } catch (JAXBException e) {
            LOGGER.error("JAXB error:\n" + e);
        }
    }

    public static void jaxbCarModel() {
        String carModelXml = "src/main/resources/output/car_model_marshal.xml";

        try {
            JAXBContext c = JAXBContext.newInstance(CarModel.class);

            Marshaller m = c.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(new CarModelService().getCarModelById(1),
                    new File(carModelXml));

            Unmarshaller u = c.createUnmarshaller();
            CarModel carModel = (CarModel) u.unmarshal(new File(carModelXml));

            LOGGER.debug("Unmarshalled car model: " + carModel.getName() + " " + carModel.getYear()
                    + " " + carModel.getPaintColors().get(0).getName());

        } catch (JAXBException e) {
            LOGGER.error("JAXB error:\n" + e);
        }

    }

    public static void jacksonAddress() {
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.WRAP_ROOT_VALUE);
        om.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        om.enable(SerializationFeature.INDENT_OUTPUT);

        Address address = null;
        try {
            address = om.readValue(new File("src/main/resources/json/address.json"), Address.class);
        } catch (IOException e) {
            LOGGER.error("Error reading JSON file\n" + e);
        }

        LOGGER.debug("Read address from JSON file: " + address.getStreet() + " " + address.getNumber()
                + ", " + address.getCity().getName() + ", " + address.getCity().getCountry().getName());

        try {
            om.writeValue(new File("src/main/resources/output/address.json"), address);
        } catch (IOException e) {
            LOGGER.error("Error writing JSON file\n" + e);
        }
    }

    public static void jacksonCarModel() {
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.WRAP_ROOT_VALUE);
        om.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        om.enable(SerializationFeature.INDENT_OUTPUT);

        String fileName = "src/main/resources/output/car_model.json";

        try {
            om.writeValue(new File(fileName), new CarModelService().getCarModelById(1));
        } catch (IOException e) {
            LOGGER.error("Error writing json file " + fileName + "\n" + e);
        }

        CarModel carModel = null;
        try {
            carModel = om.readValue(new File(fileName), CarModel.class);
        } catch (IOException e) {
            LOGGER.error("Error reading json file " + fileName + "\n" + e);
        }

        LOGGER.debug("Car model read form json: " + carModel.getName() + " " + carModel.getYear()
                + " " + carModel.getPaintColors().get(0).getName());
    }

    public final static void main(String[] args) {
        crudOperations();

        magicSax();
        xmlWrite();
        saxWithList();

        jaxbAddress();
        jaxbCarModel();

        jacksonAddress();
        jacksonCarModel();
    }
}
