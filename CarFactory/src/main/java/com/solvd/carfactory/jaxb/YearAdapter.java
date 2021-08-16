package com.solvd.carfactory.jaxb;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Year;

public class YearAdapter extends XmlAdapter<String, Year> {

    private void validate(Year year){
        if (year.isAfter(Year.now())){
            throw new IllegalArgumentException("Year cannot be in the future!");
        }
    }

    @Override
    public Year unmarshal(String s) throws Exception {
        return Year.parse(s);
    }

    @Override
    public String marshal(Year year) throws Exception {
        return String.valueOf(year);
    }
}
