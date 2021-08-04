package com.solvd.booking.main;

import com.solvd.booking.company.BookingCompany;
import com.solvd.booking.company.Review;
import com.solvd.booking.location.Address;
import com.solvd.booking.property.Hotel;
import com.solvd.booking.location.City;
import com.solvd.booking.location.Country;
import com.solvd.booking.property.Property;
import com.solvd.booking.reservation.CardType;
import com.solvd.booking.reservation.CreditCard;
import com.solvd.booking.reservation.InvalidCardNumberException;
import com.solvd.booking.reservation.Reservation;
import com.solvd.booking.user.*;
import com.solvd.booking.rentalunit.Room;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);


    public final static void main(String [] args){

        BookingCompany ivCompany = new BookingCompany("Isolation Vacation Inc.",
                                                      "www.ivacation.com");

        Partner partner = new Partner("Mike", "Wazowski", "mike@minc.com");
        partner.register(ivCompany);

        Hotel hotel = new Hotel(12345, "Transylvania Hotel",
                new Address("Missing Av.", "404", City.TORONTO_CAN));
        Map<Long, Room> rooms = new HashMap<Long, Room>();
        Room room1 = new Room(101, (byte)4, (byte)1);

        Set<LocalDate> room1ReservedDates = new HashSet<LocalDate>();
        room1ReservedDates.add(LocalDate.of(2021,11,3));
        room1.setReservedDates(room1ReservedDates);
        rooms.put(room1.getId(), room1);
        rooms.put(235L, new Room(235, (byte)2, (byte)2));

        hotel.setRooms(rooms);
        hotel.setPartner(partner);

        hotel.list(ivCompany);

        Adult adult = new Adult("Ana","Kawasaki", "aka@wasaki.com",
                LocalDate.of(1980, 1, 1),
                Country.EGY);

        Child child = new Child("Jimmy", "Neutron",
                LocalDate.of(2010, 5, 20),
                Country.EGY, adult);


        try {
            adult.register(ivCompany);
        } catch (FutureDateException e) {
            LOGGER.error("Birth date cannot be in the future!");
        } catch (BirthDateException e) {
            LOGGER.error(e);
        }

        Passenger passenger = ivCompany.findPassenger(
                (passengers, email) -> passengers.stream()
                        .filter(p -> p.getEmail().equals(email))
                        .findFirst()
                        .orElse(null),
                "aka@wasaki.com");

        List<Property> availableProperties = new ArrayList<Property>();

        LocalDate startDate = LocalDate.of(2021,11,2);
        LocalDate endDate = LocalDate.of(2021,11,10);


        availableProperties = ivCompany.searchProperties(
                element -> {
                    boolean isInLocation = element.isInLocation(City.TORONTO_CAN);
                    boolean isAvailable = false;

                    try {
                        isAvailable = element.isAvailable(startDate, endDate);
                    } catch (DateOrderException e) {
                        LOGGER.error("Start date cannot be after end date!");
                    } catch (PastDateException e) {
                        LOGGER.error("Start date cannot be in the past!");
                    }

                    return isInLocation && isAvailable;
                }
        );


        Property property = availableProperties.get(0);

        Reservation<Property> reservation = new Reservation<Property>(12345, startDate, endDate);
        reservation.setHolder(passenger);
        reservation.setReservationTarget(property);

        CreditCard card = new CreditCard("George J. Jetson",  CardType.VISA,
                "4000020000000000", YearMonth.of(2023, 5), "023");

        try {
            reservation.setCreditCard(card);
        } catch (InvalidCardNumberException e) {
            LOGGER.error("Provided card number is invalid!");
        } catch (PastDateException e) {
            LOGGER.error("This card is expired!");
        }

        reservation.reserve();

        LOGGER.debug("Reservation status is " + reservation.getStatus());

        if (reservation.isPending()){
            reservation.retry();
        }

        Review review = new Review(passenger.getFirstName(), passenger.getCountry(),
                7, "Beautiful place! but there were some scary critters at night...");
        property.review(review);

    }

}
