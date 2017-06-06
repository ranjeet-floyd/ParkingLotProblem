package com.gojek.parkinglot.bean;

/**
 *
 * @author ranjeet
 */
public class ParkingLot {
    private int id;
    private Car parkedCar;

    public ParkingLot(int id) {
        this.id = id;
        this.parkedCar = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public void setParkedCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }

}
