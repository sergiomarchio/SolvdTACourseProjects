package com.solvd.booking.reservation;

import com.solvd.booking.main.PastDateException;
import com.solvd.booking.user.Passenger;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class Reservation <T extends Reservable>{
    private long id;
    private Passenger holder;
    private LocalDate startDate;
    private LocalDate endDate;
    private CreditCard creditCard;
    private T reservationTarget;
    private List<Long> reservedUnitsIds;
    private byte adultCount;
    private byte childCount;
    private ReservationStatus status;


    public Reservation(){}
    public Reservation(long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;

    }


    public void reserve() {
        this.status = ReservationStatus.PENDING_APPROVAL;

        if (reservationTarget.makeReservation(this)) {
            this.status = ReservationStatus.APPROVED;
        }

        holder.addReservation(this);
    }

    public void cancel() {
        this.status = ReservationStatus.PENDING_CANCELLATION;

        if (reservationTarget.cancelReservation(this)){
            this.status = ReservationStatus.CANCELED;
        }
        // Cancelled reservations will be kept in the holder profile
        // holder.removeReservation(this);
    }

    public void retry(){
        this.status.retry(this);
    }

    public boolean isPending(){
        return this.status.isPending();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null
                || hashCode() != o.hashCode()
                || getClass() != o.getClass())
            return false;

        Reservation<?> that = (Reservation<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Passenger getHolder() {
        return holder;
    }
    public void setHolder(Passenger holder) {
        this.holder = holder;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(CreditCard creditCard)
            throws InvalidCardNumberException, PastDateException{

        creditCard.validate();
        this.creditCard = creditCard;
    }

    public T getReservationTarget() {
        return reservationTarget;
    }
    public void setReservationTarget(T reservationTarget) {
        this.reservationTarget = reservationTarget;
    }

    public List<Long> getReservedUnitsIds() {
        return reservedUnitsIds;
    }
    public void setReservedUnitsIds(List<Long> reservedUnitsIds) {
        this.reservedUnitsIds = reservedUnitsIds;
    }

    public byte getAdultCount() {
        return adultCount;
    }
    public void setAdultCount(byte adultCount) {
        this.adultCount = adultCount;
    }

    public byte getChildCount() {
        return childCount;
    }
    public void setChildCount(byte childCount) {
        this.childCount = childCount;
    }

    public ReservationStatus getStatus() {
        return status;
    }
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
