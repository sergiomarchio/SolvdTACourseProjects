package com.solvd.booking.company;

import com.solvd.booking.location.Country;

public class Review {
    private String guestName;
    private Country country;
    private float rating;
    private String comment;


    public Review() {}
    public Review(String guestName, Country country, float rating, String comment) {
        this.guestName = guestName;
        this.country = country;
        this.rating = rating;
        this.comment = comment;
    }


    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
