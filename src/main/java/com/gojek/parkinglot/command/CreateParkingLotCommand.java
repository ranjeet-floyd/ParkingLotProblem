package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;

/**
 *
 * @author ranjeet
 */
public class CreateParkingLotCommand implements Command {

    public CreateParkingLotCommand() {
    }

    @Override
    public ParkingLotFactory apply(Object... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain int numberOfSlot");
        }
        int numberOfSlot = Integer.parseInt(values[0].toString());
        ParkingLotFactory parkingLotFactory = ParkingLotFactory.initParkingLots(numberOfSlot);
        System.out.println("Created a parking lot with " + numberOfSlot + " slots");
        return parkingLotFactory;

    }

}
