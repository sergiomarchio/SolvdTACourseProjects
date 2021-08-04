package com.solvd.booking.property;

import com.solvd.booking.company.*;
import com.solvd.booking.location.Address;
import com.solvd.booking.location.GpsCoordinates;
import com.solvd.booking.rentalunit.RentalUnit;
import com.solvd.booking.reservation.Reservable;
import com.solvd.booking.reservation.Reservation;
import com.solvd.booking.main.*;
import com.solvd.booking.user.Partner;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;


public abstract class Property implements Findable, Listable, Reservable, Reviewable {

    private final static Logger LOGGER = Logger.getLogger(Property.class);

    private long id;
    private String name;
    private byte stars;
    private float rating;
    private Address address;
    private GpsCoordinates gpsCoordinates;
    private List<Review> reviews;
    private Partner partner;

    private boolean wifiFree;
    private boolean parkingAvailable;
    private boolean restaurantAvailable;
    private boolean roomServiceAvailable;
    private boolean gymAvailable;
    private boolean spaAvailable;
    private boolean swimmingPoolAvailable;


    public Property(){}
    public Property(long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;

        this.reviews = new ArrayList<Review>();
    }

    @Override
    public <T> boolean isInLocation(T location) {
        return address.getCity() == location
                || address.getCity().getCountry() == location
                || address.getCity().getCountry().getContinent() == location;
    }


    @Override
    public void list(BookingCompany company) {
        company.addProperty(this);

        if (partner != null) {
            partner.addProperty(this);
        }

        LOGGER.debug(getId() + " - " + getName() + " added to " + company.getName());
    }

    @Override
    public void unlist(BookingCompany company) {
        company.removeProperty(this);

        if (partner != null) {
            partner.removeProperty(this);
        }

        LOGGER.debug(getId() + " - " + getName() + " removed from " + company.getName());
    }

    @Override
    public boolean makeReservation (Reservation<?> reservation) {
        LOGGER.debug("Reservation added!");

        return true;
    }

    @Override
    public boolean cancelReservation(Reservation<?> reservation){
        LOGGER.debug("Reservation cacelled!");

        return true;
    }


    @Override
    public void review(Review review) {
        this.reviews.add(review);

        updateRating();

        LOGGER.debug("Passenger " + review.getGuestName() + " reviewed " + this.name);
    }

    private void updateRating(){
        this.rating = (float) reviews.stream()
                                     .mapToDouble(Review::getRating)
                                     .sum() / this.reviews.size();

        LOGGER.debug("Rating for " + this.name + " was updated to " + this.rating);
    }


    // Checks if the property has any available rental units in the desired date range
    protected boolean areRentalUnitsAvailable(Map<Long, ? extends RentalUnit> rentalUnits,
                                              LocalDate startDate,
                                              LocalDate endDate)
            throws DateOrderException, PastDateException {

        if (startDate.compareTo(endDate) > 0){
            throw new DateOrderException();
        } else if (startDate.compareTo(LocalDate.now()) < 0){
            throw new PastDateException();
        }

        // at least one rental unit available is enough to return true
        return rentalUnits.values().stream()
                                   .anyMatch(unit -> unit.isAvailable(startDate, endDate));
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public byte getStars(){
        return stars;
    }
    public void setStars(byte stars){
        this.stars = stars;
    }

    public float getRating(){
        return rating;
    }
    public void setRating(float rating){
        this.rating = rating;
    }

    public Address getAddress(){
        return address;
    }
    public void setAddress(Address address){
        this.address = address;
    }

    public GpsCoordinates getGpsCoordinates(){
        return gpsCoordinates;
    }
    public void setGpsCoordinates(GpsCoordinates gpsCoordinates){
        this.gpsCoordinates = gpsCoordinates;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Partner getPartner() {
        return partner;
    }
    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    
    public boolean isWifiFree() {
        return wifiFree;
    }
    public void setWifiFree(boolean wifiFree) {
        this.wifiFree = wifiFree;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }
    public void setParkingAvailable(boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public boolean isRestaurantAvailable() {
        return restaurantAvailable;
    }
    public void setRestaurantAvailable(boolean restaurantAvailable) {
        this.restaurantAvailable = restaurantAvailable;
    }

    public boolean isRoomServiceAvailable() {
        return roomServiceAvailable;
    }
    public void setRoomServiceAvailable(boolean roomServiceAvailable) {
        this.roomServiceAvailable = roomServiceAvailable;
    }

    public boolean isGymAvailable() {
        return gymAvailable;
    }
    public void setGymAvailable(boolean gymAvailable) {
        this.gymAvailable = gymAvailable;
    }

    public boolean isSpaAvailable() {
        return spaAvailable;
    }
    public void setSpaAvailable(boolean spaAvailable) {
        this.spaAvailable = spaAvailable;
    }

    public boolean isSwimmingPoolAvailable() {
        return swimmingPoolAvailable;
    }
    public void setSwimmingPoolAvailable(boolean swimmingPoolAvailable) {
        this.swimmingPoolAvailable = swimmingPoolAvailable;
    }
}

