package com.ironhack.MidtermSpring.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private int postalCode;

    //CONSTRUCTORS
    public Address(String callePelai, int i, String barcelona, int i1, String spain) {
    }

    public Address(String street, String city, int postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    //GETTERS & SETTERS
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}

