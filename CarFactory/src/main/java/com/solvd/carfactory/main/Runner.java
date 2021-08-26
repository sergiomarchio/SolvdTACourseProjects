package com.solvd.carfactory.main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.dao.mysql.jdbc.CountryDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import com.solvd.carfactory.models.supply.Provider;
import com.solvd.carfactory.sax.*;
import com.solvd.carfactory.services.*;
import com.solvd.carfactory.services.impl.*;
import com.solvd.carfactory.services.impl.jdbc.AddressServiceJDBC;
import com.solvd.carfactory.services.impl.jdbc.CarModelServiceJDBC;
import com.solvd.carfactory.services.impl.jdbc.CityServiceJDBC;
import com.solvd.carfactory.util.mybatis.MybatisUtil;
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
        LOGGER.debug("Got city from CityService with id: " + city);

        ConnectionPool.getInstance().closeAll();
    }

    private static void magicSax() {
        Country country = XMLRead.xmlRead("src/main/resources/xml/country.xml", new UniversalSAX<Country>());
        LOGGER.debug("Read Country: " + country);

        City city = XMLRead.xmlRead("src/main/resources/xml/city.xml", new UniversalSAX<City>());
        LOGGER.debug("Read City: " + city);
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

            LOGGER.debug("Unmarshalled address: " + address);

            Marshaller m = c.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(address, new File("src/main/resources/output/address_marshall.xml"));

        } catch (JAXBException e) {
            LOGGER.error("JAXB error:\n" + e);
        }
    }

    public static void jaxbCarModel() {
        String carModelXml = "src/main/resources/output/car_model_marshal_mybatis.xml";

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

        LOGGER.debug("Read address from JSON file: " + address);

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

    public static void mybatisSimpleCrud() {
        Country c = new CountryService().getCountryById(1);
        LOGGER.info("Country " + c.getId() + ": " + c.getName());

        Country nepal = new Country();
        nepal.setName("Nepall");
        ICountryService countryService = new CountryService();
        countryService.createCountry(nepal);
        LOGGER.info("Created country " + nepal.getId() + ": " + nepal.getName());

        nepal.setName("Nepal");
        countryService.updateCountry(nepal);
        LOGGER.info("Update statement executed");
        LOGGER.info("Updated country " + nepal.getId() + ": " + countryService.getCountryById(nepal.getId()));

        countryService.deleteCountry(nepal.getId());
        LOGGER.info("Deleted country " + nepal.getId() + ": " + nepal.getName());

        LOGGER.info("Trying to read non existent element to check deletion:");
        countryService.getCountryById(nepal.getId());
    }

    public static void mybatisNested() {
        IAddressService addressService = new AddressService();
        Address a = addressService.getAddressById(1);
        LOGGER.info(a);

        IProviderService providerService = new ProviderService();
        Provider p = providerService.getProviderById(1);
        LOGGER.info("\n" + p);
    }

    public static void performance() {
        long time0 = System.currentTimeMillis();
        new CountryDAO().getItemById(1);
        long time1 = System.currentTimeMillis();
        MybatisUtil.getIDao(ICountryDAO.class).getItemById(1);
        long time2 = System.currentTimeMillis();

        LOGGER.info("\nSimple query."
                + "\nElapsed time for jdbc: " + (time1 - time0) / 1000f + "s"
                + "\nElapsed time for mybatis: " + (time2 - time1) / 1000f + "s");

        time0 = System.currentTimeMillis();
        new CityServiceJDBC().getCityById(1);
        time1 = System.currentTimeMillis();
        new CityService().getCityById(1);
        time2 = System.currentTimeMillis();
        MybatisUtil.getIDao(ICityDAO.class).getFullCityById(1);
        long time3 = System.currentTimeMillis();
        City c = MybatisUtil.getIDao(ICityDAO.class).getNestedCityById(1);
        long time4 = System.currentTimeMillis();

        LOGGER.info("\nNested select city: " + c);

        LOGGER.info("\n1 nested query."
                + "\nElapsed time for jdbc: " + (time1 - time0) / 1000f + "s"
                + "\nElapsed time for mybatis: " + (time2 - time1) / 1000f + "s"
                + "\nElapsed time for mybatis join: " + (time3 - time2) / 1000f + "s"
                + "\nElapsed time for mybatis nested select: " + (time4 - time3) / 1000f + "s");

        time0 = System.currentTimeMillis();
        new AddressServiceJDBC().getAddressById(1);
        time1 = System.currentTimeMillis();
        new AddressService().getAddressById(1);
        time2 = System.currentTimeMillis();
        Address a = MybatisUtil.getIDao(IAddressDAO.class).getFullAddressById(1);
        time3 = System.currentTimeMillis();
        Address na = MybatisUtil.getIDao(IAddressDAO.class).getNestedAddressById(1);
        time4 = System.currentTimeMillis();

        LOGGER.info("\nFull Address read: " + a);
        LOGGER.info("\nNested address read: " + na);

        LOGGER.info("\n2 nested query."
                + "\nElapsed time for jdbc: " + (time1 - time0) / 1000f + "s"
                + "\nElapsed time for mybatis: " + (time2 - time1) / 1000f + "s"
                + "\nElapsed time for mybatis join: " + (time3 - time2) / 1000f + "s"
                + "\nElapsed time for mybatis nested select: " + (time4 - time3) / 1000f + "s");


        time0 = System.currentTimeMillis();
        new CarModelServiceJDBC().getCarModelById(1);
        time1 = System.currentTimeMillis();
        new CarModelService().getCarModelById(1);
        time2 = System.currentTimeMillis();
        CarModel cm = MybatisUtil.getIDao(ICarModelDAO.class).getFullCarModelById(1);
        time3 = System.currentTimeMillis();
        CarModel cmn = MybatisUtil.getIDao(ICarModelDAO.class).getNestedCarModelById(1);
        time4 = System.currentTimeMillis();

        LOGGER.info("Full car model read: " + cm);
        LOGGER.info("Nested car model read: " + cmn);

        LOGGER.info("\nMulti nested query with list."
                + "\nElapsed time for jdbc: " + (time1 - time0) / 1000f + "s"
                + "\nElapsed time for mybatis: " + (time2 - time1) / 1000f + "s"
                + "\nElapsed time for mybatis join: " + (time3 - time2) / 1000f + "s"
                + "\nElapsed time for mybatis nested select: " + (time4 - time3) / 1000f + "s");

    }


    public final static void main(String[] args) {
//        crudOperations();
//
//        magicSax();
//        xmlWrite();
//        saxWithList();
//
//        jaxbAddress();
//        jaxbCarModel();
//
//        jacksonAddress();
//        jacksonCarModel();

//        mybatisSimpleCrud();
//        mybatisNested();

//        performance();

        CarModel cmn = MybatisUtil.getIDao(ICarModelDAO.class).getNestedCarModelById(1);
        LOGGER.info("Nested car model: " + cmn);
    }
}
