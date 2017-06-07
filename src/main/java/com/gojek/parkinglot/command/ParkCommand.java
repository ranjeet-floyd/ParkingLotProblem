package com.gojek.parkinglot.command;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class ParkCommand implements Command {

        private final ParkingLotSingleton parkingLotFactory;
    public ParkCommand() {
        parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(Object... values) {

        if (values.length != 2) {
            throw new IllegalArgumentException("str array should contains parkingLotFactory obj, car reg and color");
        }
        try {
            String regNo = (String) values[0];
            String color = (String) values[1];
            int slotId = parkingLotFactory.getParkingLot().park(new Car(color, regNo));
            System.out.println("Allocated slot number: " + slotId);

        } catch (NoSpaceException ex) {
            System.out.println("Sorry, parking lot is full");
        }
        return null;
    }

}
