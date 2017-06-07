package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class CreateParkingLotCommand implements Command {

    private final ParkingLotSingleton parkingLotSingleton;

    public CreateParkingLotCommand() {
        parkingLotSingleton = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public ParkingLotSingleton apply(Object... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain int numberOfSlot");
        }
        int numberOfSlot = Integer.parseInt(values[0].toString());
        parkingLotSingleton.getParkingLot().initParkingLots(numberOfSlot);
        System.out.println("Created a parking lot with " + numberOfSlot + " slots");
        return parkingLotSingleton;

    }

}
