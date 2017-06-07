package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;

/**
 *
 * @author ranjeet
 */
public class ParkCommand implements Command {

    public ParkCommand() {
    }

    @Override
    public Object apply(Object... values) {

        if (values.length != 3) {
            throw new IllegalArgumentException("str array should contains parkingLotFactory obj, car reg and color");
        }
        try {
            ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
            String regNo = (String) values[1];
            String color = (String) values[2];
            int slotId = parkingLotFactory.park(new Car(color, regNo));
            System.out.println("Allocated slot number: " + slotId);

        } catch (NoSpaceException ex) {
            System.out.println("Sorry, parking lot is full");
        }
        return null;
    }

}
