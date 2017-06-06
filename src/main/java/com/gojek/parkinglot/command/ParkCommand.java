package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;

/**
 *
 * @author ranjeet
 */
public class ParkCommand implements Command {

    private ParkingLotFactory parkingLotFactory;

    public ParkCommand(ParkingLotFactory parkingLotFactory) {
        this.parkingLotFactory = parkingLotFactory;
    }

    @Override
    public Integer apply(String... values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("str array should contain car reg and color");
        }
        try {
            String regNo = values[0];
            String color = values[1];
            return this.parkingLotFactory.park(new Car(color, regNo));
        } catch (NoSpaceException ex) {
            System.err.println(ex);
            throw new RuntimeException("No Space left", ex);
        }
    }

}
