package com.solvd.booking.company;

import com.solvd.booking.property.Property;
import com.solvd.booking.user.Partner;
import com.solvd.booking.user.Passenger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingCompany{
    private final static Logger LOGGER = Logger.getLogger(BookingCompany.class);

    private String name;
    private String website;

    private List<Partner> partners;
    private List<Passenger> passengers;
    private List<Property> properties;


    public BookingCompany(){}
    public BookingCompany(String name, String website) {
        this.partners = new ArrayList<Partner>();
        this.passengers = new ArrayList<Passenger>();
        this.properties = new ArrayList<Property>();

        this.name = name;
        this.website = website;
    }


    public List<Property> searchProperties (IFindableCriteria<Property> criteria){
        List<Property> availableProperties = search(criteria, properties);

        if (availableProperties.size() == 0){
            LOGGER.debug("There are no available properties meeting the desired criteria");
        } else {
            LOGGER.debug("These properties meet the criteria:");

            availableProperties.stream()
                               .forEach(property -> LOGGER.debug(property.getName()));
        }

        return availableProperties;
    }


    public <T extends Findable> List <T> search(IFindableCriteria<T> criteria,
                                                List<T> targets) {

        return targets.stream()
                      .filter(criteria::meets)
                      .collect(Collectors.toList());

    }

    public Passenger findPassenger(IStringCriteria<Passenger> criteria, String email){
        return criteria.find(passengers, email);
    }


    public void addProperty(Property property){
        properties.add(property);
    }

    public void removeProperty(Property property){
        properties.remove(property);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Partner> getPartners() {
        return partners;
    }
    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Property> getProperties() {
        return properties;
    }
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
