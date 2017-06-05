package com.gojek.parkinglot.bean;

/**
 * Immutable car info class
 *
 * @author ranjeet
 */
public class CarInfo {

    private final String color;
    private final String registrationNumber;

    public CarInfo(String color, String registrationNumber) {
        this.color = color;
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}
