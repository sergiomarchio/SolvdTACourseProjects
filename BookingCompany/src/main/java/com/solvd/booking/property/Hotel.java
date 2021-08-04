package com.solvd.booking.property;

import com.solvd.booking.location.Address;
import com.solvd.booking.rentalunit.Room;
import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.util.Map;

public class Hotel extends Property {
    private Map<Long, Room> rooms;


    public Hotel(){
    }
    public Hotel(long id, String name, Address address) {
        super(id, name, address);
    }
    public Hotel(long id, String name, Address address, Map<Long, Room> rooms) {
        super(id, name, address);
        this.rooms = rooms;
    }


    @Override
    public boolean isAvailable(LocalDate startDate, LocalDate endDate)
            throws DateOrderException, PastDateException {
        return areRentalUnitsAvailable(rooms, startDate, endDate);
    }


    public Map<Long, Room> getRooms() {
        return rooms;
    }
    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }
}
