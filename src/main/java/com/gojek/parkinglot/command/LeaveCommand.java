package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;

/**
 *
 * @author ranjeet
 */
public class LeaveCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;
    public LeaveCommand() {
        parkingLotFactory = ParkingLotSingleton.getInstance();
    }

    @Override
    public Object apply(Object... values) {

        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain parkingLotFactory, int SlotId");
        }
        try {
            int slotId = Integer.parseInt(values[0].toString());
            parkingLotFactory.unPark(slotId);
            System.out.println("Slot number " + slotId + " is free");
            
        } catch (NoSuchCarFoundException ex) {
            System.out.println("Not found");
        }
        return null;
    }
}
