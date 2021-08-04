package com.solvd.booking.main;

import com.solvd.booking.company.Review;
import com.solvd.booking.user.Passenger;

import java.util.Objects;

public class Attraction {
    private long id;
    private String name;
    private Review review;

    private float costPerChild;
    private float costPerAdult;
    private boolean aquatic;
    private byte rollercoasterCount;
    private byte theatreCount;


    public Attraction(){}
    public Attraction(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public boolean reserveTicket(Passenger passenger, int id) {
        // ...
        return false;
    }

    public boolean cancelTicket(Passenger passenger, int id) {
        // ...
        return false;
    }


    @Override
    public String toString() {
        return "id: " + id + "\n"
             + "name: " + name + "\n"
             + "costPerChild: " + costPerChild + "\n"
             + "costPerAdult: " + costPerAdult + "\n"
             + "aquatic: " + aquatic + "\n"
             + "rollercoasterCount: " + rollercoasterCount + "\n"
             + "theatreCount: " + theatreCount
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null
                || o.getClass() != this.getClass()
                || o.hashCode() != this.hashCode())
            return false;

        // Cast type to access variables
        Attraction obj = (Attraction) o;

        return id == obj.id
            && name.equals(obj.name)
            && Float.compare(costPerChild, obj.costPerChild) == 0
            && Float.compare(costPerAdult, obj.costPerAdult) == 0
            && aquatic == obj.aquatic
            && rollercoasterCount == obj.rollercoasterCount
            && theatreCount == obj.theatreCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, costPerChild, costPerAdult,
                aquatic, rollercoasterCount, theatreCount);
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }

    public float getCostPerChild() {
        return costPerChild;
    }
    public void setCostPerChild(float costPerChild) {
        this.costPerChild = costPerChild;
    }

    public float getCostPerAdult() {
        return costPerAdult;
    }
    public void setCostPerAdult(float costPerAdult) {
        this.costPerAdult = costPerAdult;
    }

    public boolean isAquatic() {
        return aquatic;
    }
    public void setAquatic(boolean aquatic) {
        this.aquatic = aquatic;
    }

    public byte getRollercoasterCount() {
        return rollercoasterCount;
    }
    public void setRollercoasterCount(byte rollercoasterCount) {
        this.rollercoasterCount = rollercoasterCount;
    }

    public byte getTheatreCount() {
        return theatreCount;
    }
    public void setTheatreCount(byte theatreCount) {
        this.theatreCount = theatreCount;
    }
}
