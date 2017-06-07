package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;

/**
 *
 * @author ranjeet
 */
public class SlotNumForRegisNumberCommand implements Command {

    public SlotNumForRegisNumberCommand() {
    }

    @Override
    public <T> T apply(Object... values) {

        if (values.length != 2 && values[0] instanceof ParkingLotFactory) {
            throw new IllegalArgumentException("array len 2  and must contains parkingLotFactory obj");
        }
        try {
            ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
            String regisNumber = values[1].toString();

            int slotId = parkingLotFactory.slotsOfCarRegistrationNumber(regisNumber);
            System.out.println(slotId);

        } catch (NoSuchCarFoundException ex) {
            System.out.println("Not found");
        }
        return null;
    }

}
