package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;

/**
 *
 * @author ranjeet
 */
public class LeaveCommand implements Command {

    public LeaveCommand() {
    }

    @Override
    public Object apply(Object... values) {

        if (values.length != 2) {
            throw new IllegalArgumentException("str array should contain parkingLotFactory, int SlotId");
        }
        try {
            ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
            int slotId = Integer.parseInt(values[1].toString());
            parkingLotFactory.unPark(slotId);
            System.out.println("Slot number " + slotId + " is free");
            
        } catch (NoSuchCarFoundException ex) {
            System.out.println("Not found");
        }
        return null;
    }
}
