package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class CreateParkingLotCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public CreateParkingLotCommand() {
        parkingLotFactory = ParkingLotSingleton.getInstance();
    }

    @Override
    public ParkingLotSingleton apply(Object... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain int numberOfSlot");
        }
        int numberOfSlot = Integer.parseInt(values[0].toString());
        parkingLotFactory.initParkingLots(numberOfSlot);
        System.out.println("Created a parking lot with " + numberOfSlot + " slots");
        return parkingLotFactory;

    }

}
