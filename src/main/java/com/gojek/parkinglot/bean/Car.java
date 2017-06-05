package com.gojek.parkinglot.bean;

import java.util.Objects;

/**
 * Immutable car info class
 *
 * @author ranjeet
 */
public class Car {

    private final String color;
    private final String registrationNumber;

    public Car(String color, String registrationNumber) {
        this.color = color;
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return "{\"carInfo\":{\"color\":" + color + ",\"registrationNumber\":" + registrationNumber + "}}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.registrationNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        return this.registrationNumber.equalsIgnoreCase(other.registrationNumber);
    }
    
    

}
