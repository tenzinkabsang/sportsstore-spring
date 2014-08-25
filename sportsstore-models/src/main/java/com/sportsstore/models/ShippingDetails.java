package com.sportsstore.models;

import org.hibernate.validator.constraints.NotEmpty;

public class ShippingDetails {
    @NotEmpty(message = "Please enter a name")
    private String name;

    @NotEmpty
    private String line1;

    private String city;
    private String state;
    private String zip;
    private boolean giftWrap;

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public String getName() {
        return name;
    }

    public String getLine1() {
        return line1;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}

