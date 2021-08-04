package com.solvd.booking.property;

import com.solvd.booking.location.Address;
import com.solvd.booking.rentalunit.Room;
import com.solvd.booking.main.Attraction;
import com.solvd.booking.main.DateOrderException;
import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class Resort extends Property {

    private Map<Long, Room> rooms;
    private List<Attraction> attractions;


    public Resort() {}
    public Resort(long id, String name, Address address) {
        super(id, name, address);
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

    public List<Attraction> getAttractions() {
        return attractions;
    }
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
