package com.solvd.booking.rentalunit;


import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class RentalUnit {
    private long id;
    private String name;
    private byte capacity;
    private float costPerAdult;
    private float costPerChild;

    private Set<LocalDate> reservedDates;

    private boolean bathroomPrivate;
    private boolean airConditioningAvailable;
    private boolean tvAvailable;
    private boolean coffeeMachineAvailable;


    public RentalUnit(){}
    public RentalUnit(long id, byte capacity){
        this.id = id;
        this.capacity = capacity;

        this.reservedDates = new HashSet<LocalDate>();
    }


    // Agnostic to date order to avoid exception in caller stream
    // Returns false if date is in the past because it is not available in the past
    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {

        if (startDate.compareTo(LocalDate.now()) < 0){
            return false;
        }

        if (startDate.compareTo(endDate) > 0){
            LocalDate temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        // Unit must be available for all dates in range
        return Stream.iterate(startDate, date -> date.plusDays(1))
                     .limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
                     .noneMatch(date -> reservedDates.contains(date));
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

    public byte getCapacity() {
        return capacity;
    }
    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public float getCostPerAdult() {
        return costPerAdult;
    }
    public void setCostPerAdult(float costPerAdult) {
        this.costPerAdult = costPerAdult;
    }

    public float getCostPerChild() {
        return costPerChild;
    }
    public void setCostPerChild(float costPerChild) {
        this.costPerChild = costPerChild;
    }

    public Set<LocalDate> getReservedDates() {
        return reservedDates;
    }
    public void setReservedDates(Set<LocalDate> reservedDates) {
        this.reservedDates = reservedDates;
    }

    public boolean isBathroomPrivate() {
        return bathroomPrivate;
    }
    public void setBathroomPrivate(boolean bathroomPrivate) {
        this.bathroomPrivate = bathroomPrivate;
    }

    public boolean isAirConditioningAvailable() {
        return airConditioningAvailable;
    }
    public void setAirConditioningAvailable(boolean airConditioningAvailable) {
        this.airConditioningAvailable = airConditioningAvailable;
    }

    public boolean isTvAvailable() {
        return tvAvailable;
    }
    public void setTvAvailable(boolean tvAvailable) {
        this.tvAvailable = tvAvailable;
    }

    public boolean isCoffeeMachineAvailable() {
        return coffeeMachineAvailable;
    }
    public void setCoffeeMachineAvailable(boolean coffeeMachineAvailable) {
        this.coffeeMachineAvailable = coffeeMachineAvailable;
    }
}

