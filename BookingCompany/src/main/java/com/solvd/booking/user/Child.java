package com.solvd.booking.user;

import com.solvd.booking.location.Country;
import com.solvd.booking.company.BookingCompany;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class Child extends Passenger{
    private final static Logger LOGGER = Logger.getLogger(Child.class);

    private Adult responsibleAdult;

    public Child(){}
    public Child(String firstName, String lastName, LocalDate dateOfBirth, Country country,
                 Adult responsibleAdult){
        super(firstName, lastName, responsibleAdult.getEmail(), dateOfBirth, country);
        this.responsibleAdult = responsibleAdult;
    }

    @Override
    public void register(BookingCompany company)
            throws FutureDateException, BirthDateException {

        registerPassenger(age -> {
            if (age >= 18){
                throw new BirthDateException("Children must be under 18!\n" +
                        "You can't register your inner child in this site!");
            } else if (age < 12){
                LOGGER.info("Please remember that children younger than 12 can't travel alone");
            }
        }, company);
    }


    public Adult getResponsibleAdult() {
        return responsibleAdult;
    }
    public void setResponsibleAdult(Adult responsibleAdult) {
        this.responsibleAdult = responsibleAdult;
    }
}
