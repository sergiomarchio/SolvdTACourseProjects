package com.solvd.booking.location;


public enum Country {
    ARG("Argentina", "AR", "ARG", "032", Continent.SOUTH_AMERICA),
    BLR("Belarus", "BY", "BLR", "112", Continent.EUROPE),
    BRA("Brazil", "BR", "BRA", "076", Continent.SOUTH_AMERICA),
    CAN("Canada", "CA", "CAN", "124", Continent.NORTH_AMERICA),
    EGY("Egypt", "EG", "EGY", "818", Continent.AFRICA),
    FRA("France", "FR", "FRA", "250", Continent.EUROPE),
    JPN("Japan", "JP", "JPN", "392", Continent.ASIA),
    UKR("Ukraine", "UA", "UKR", "804", Continent.EUROPE),
    USA("United States of America", "US", "USA", "840", Continent.NORTH_AMERICA);


    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String numCode;
    private Continent continent;


    Country(String name, String alpha2Code, String alpha3Code, String numCode, Continent continent){
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.numCode = numCode;
        this.continent = continent;
    }


    public String getName(){
        return name;
    }
    public String getNumCode(){
        return numCode;
    }
    public String getAlpha2Code() {
        return alpha2Code;
    }
    public String getAlpha3Code() {
        return alpha3Code;
    }
    public Continent getContinent() {
        return continent;
    }
}
