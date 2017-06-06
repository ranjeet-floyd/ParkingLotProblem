package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;

/**
 *
 * @author ranjeet
 */
public class CreateParkingLotCommand implements Command {

    private ParkingLotFactory parkingLotFactory;

    public CreateParkingLotCommand() {
    }

    @Override
    public ParkingLotFactory apply(String... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain int numberOfSlot");
        }
        try {
            int numberOfSlot = Integer.parseInt(values[0]);
            parkingLotFactory = ParkingLotFactory.initParkingLots(numberOfSlot);
            return parkingLotFactory;
        } catch (NumberFormatException nfe) {
            System.err.println(nfe);
            throw new NumberFormatException("Please provide valid int  numberOfSlot :" + values[0]);
        }
    }

}
