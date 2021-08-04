package com.solvd.booking.location;

public class GpsCoordinates {
    private float latitude;
    private float longitude;
    private float elevation;


    public GpsCoordinates(){}
    public GpsCoordinates(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public float getLatitude(){
        return latitude;
    }
    public void setLatitude(float latitude){
        this.latitude = latitude;
    }

    public float getLongitude(){
        return longitude;
    }
    public void setLongitude(float longitude){
        this.longitude = longitude;
    }

    public float getElevation(){
        return elevation;
    }
    public void setElevation(float elevation){
        this.elevation = elevation;
    }
}
