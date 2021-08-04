package com.solvd.booking.user;

import com.solvd.booking.property.Property;
import com.solvd.booking.company.BookingCompany;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Partner extends User{
    private final static Logger LOGGER = Logger.getLogger(Partner.class);

    private List<Property> properties;


    public Partner() {}
    public Partner(String firstName, String lastName, String email) {
        super(firstName, lastName, email);

        this.properties = new ArrayList<Property>();
    }


    public void addProperty(Property property){
        properties.add(property);
    }

    public void removeProperty(Property property){
        properties.remove(property);
    }


    @Override
    public void register(BookingCompany company) {
        List<Partner> partners = company.getPartners();
        partners.add(this);
        company.setPartners(partners);

        LOGGER.debug("Partner " + getFirstName() + " " + getLastName()
                + " - " + getEmail() + " was registered\n"
                + " Welcome!");
    }

    @Override
    public void unregister(BookingCompany company) {
        List<Partner> partners = company.getPartners();
        partners.remove(this);
        company.setPartners(partners);

        LOGGER.debug("Partner " + getFirstName() + " " + getLastName()
                + " - " + getEmail() + " was unregistered\n"
                + " we will miss you! :(");
    }

    public List<Property> getProperties() {
        return properties;
    }
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
