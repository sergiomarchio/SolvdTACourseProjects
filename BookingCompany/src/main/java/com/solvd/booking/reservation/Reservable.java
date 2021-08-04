package com.solvd.booking.reservation;


public interface Reservable {
    boolean makeReservation(Reservation<?>  reservation);
    boolean cancelReservation(Reservation<?>  reservation);
}
