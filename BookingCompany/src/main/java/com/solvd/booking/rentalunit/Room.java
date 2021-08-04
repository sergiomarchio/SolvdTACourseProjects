package com.solvd.booking.rentalunit;

public class Room extends RentalUnit {
    private RoomType roomType;
    private byte floorNumber;
    private boolean kitchenAvailable;


    public Room(){}
    public Room(long id, byte capacity, byte floorNumber) {
        super(id, capacity);
        this.floorNumber = floorNumber;
    }


    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public byte getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(byte floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isKitchenAvailable() {
        return kitchenAvailable;
    }
    public void setKitchenAvailable(boolean kitchenAvailable) {
        this.kitchenAvailable = kitchenAvailable;
    }
}
