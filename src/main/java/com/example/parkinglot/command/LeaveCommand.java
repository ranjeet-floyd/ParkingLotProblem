package com.example.parkinglot.command;

import com.example.parkinglot.exception.NoSuchCarFoundException;
import com.exampleparkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class LeaveCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public LeaveCommand() {
        parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(String... values) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 1) {
            builder.append("str array should contain parkingLotFactory, int SlotId");
            return builder.toString();
        }

        try {
            int slotId = Integer.parseInt(values[0]);
            parkingLotFactory.getParkingLot().unPark(slotId);
            builder.append("Slot number ");
            builder.append(slotId);
            builder.append(" is free");

        } catch (NoSuchCarFoundException ex) {
            builder.append("Not found");
        }
        return builder.toString();
    }
}
