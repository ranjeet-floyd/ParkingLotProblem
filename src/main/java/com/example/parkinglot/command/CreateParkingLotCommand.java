package com.example.parkinglot.command;

import com.exampleparkinglot.objects.ParkingLotSingleton;

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
    public Object apply(String... values) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 1) {
            builder.append("str array should contain int numberOfSlot");
            return builder.toString();
        }

        int numberOfSlot = Integer.parseInt(values[0]);
        parkingLotSingleton.getParkingLot().initParkingLots(numberOfSlot);
        builder.append("Created a parking lot with ");
        builder.append(numberOfSlot);
        builder.append(" slots");
        return builder.toString();

    }

}
