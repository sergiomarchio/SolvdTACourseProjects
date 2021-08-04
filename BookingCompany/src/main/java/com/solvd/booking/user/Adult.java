package com.solvd.booking.user;

import com.solvd.booking.location.Country;
import com.solvd.booking.company.BookingCompany;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class Adult extends Passenger{
    private final static Logger LOGGER = Logger.getLogger(Adult.class);

    private boolean legalDriver;

    public Adult(){}
    public Adult(String firstName, String lastName, String email, LocalDate dateOfBirth, Country country){
        super(firstName, lastName, email, dateOfBirth, country);
    }

    @Override
    public void register(BookingCompany company)
            throws FutureDateException, BirthDateException {

        registerPassenger(age -> {
            if (age < 18){
                throw new BirthDateException("You're not an adult yet! Enjoy your youth :)");
            } else if (age > 60) {
                LOGGER.info("You may be eligible for special discounts! Stay tuned!");
            }
        }, company);
    }

    public boolean isLegalDriver() {
        return legalDriver;
    }
    public void setLegalDriver(boolean legalDriver) {
        this.legalDriver = legalDriver;
    }
}
