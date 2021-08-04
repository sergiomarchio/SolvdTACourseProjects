package com.solvd.booking.user;

import com.solvd.booking.location.Country;
import com.solvd.booking.company.BookingCompany;
import com.solvd.booking.reservation.Reservation;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class Passenger extends User{
    private final static Logger LOGGER = Logger.getLogger(Passenger.class);

    private LocalDate dateOfBirth;
    private Country country;
    private String nationalID;
    private String passportNumber;

    private Set<Reservation<?>> reservations;


    public Passenger(){}
    public Passenger(String firstName, String lastName, String email, LocalDate dateOfBirth, Country country) {
        super(firstName, lastName, email);
        this.dateOfBirth = dateOfBirth;
        this.country = country;

        this.reservations = new HashSet<>();
    }


    public void addReservation(Reservation<?> reservation){
        reservations.add(reservation);
    }

    public void removeReservation(Reservation<?> reservation){
        reservations.remove(reservation);
    }

    public int getAge(){
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }


    public void registerPassenger(ICheckAge ageChecker, BookingCompany company)
            throws FutureDateException, BirthDateException {
        if (dateOfBirth == null){
            throw new BirthDateException("A birth date should be provided!");
        } else if (dateOfBirth.compareTo(LocalDate.now()) > 0){
            throw new FutureDateException();
        }

        ageChecker.ageCheck(getAge());

        List<Passenger> passengers = company.getPassengers();
        passengers.add(this);
        company.setPassengers(passengers);

        LOGGER.debug("Passenger " + getFirstName() + " " + getLastName()
                + " - " + getEmail() + " was registered\n"
                + " Welcome!");
    }

    @Override
    public void unregister(BookingCompany company) {
        List<Passenger> passengers = company.getPassengers();
        passengers.remove(this);
        company.setPassengers(passengers);

        LOGGER.debug("Passenger " + getFirstName() + " " + getLastName()
                + " - " + getEmail() + " was unregistered\n"
                + " we will miss you! :(");
    }


    @Override
    public String toString() {
        return "firstName: " + getFirstName() + "\n"
             + "lastName: " + getLastName() + "\n"
             + "email: " + getEmail() + "\n"
             + "dateOfBirth: " + dateOfBirth + "\n"
             + "country: " + country.getName() + "\n"
             + "nationalID: " + nationalID + "\n"
             + "passportNumber: " + passportNumber
             ;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public String getNationalID() {
        return nationalID;
    }
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Set<Reservation<?>> getReservations() {
        return reservations;
    }
    public void setReservations(Set<Reservation<?>> reservations) {
        this.reservations = reservations;
    }
}
