package com.gojek.parkinglot.bean;

/**
 *
 * @author ranjeet
 */
public class ParkingLot {

    private int id;
    private Vehicle parkedCar;

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

    public Vehicle getVehicle() {
        return parkedCar;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedCar = parkedVehicle;
    }

}
