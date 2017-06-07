package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;

/**
 *
 * @author ranjeet
 */
public class SlotNumForRegisNumberCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public SlotNumForRegisNumberCommand() {
        parkingLotFactory = ParkingLotSingleton.getInstance();
    }

    @Override
    public <T> T apply(Object... values) {

        if (values.length != 1 ) {
            throw new IllegalArgumentException("array len 1  and must contains regisNumber");
        }
        try {
            String regisNumber = values[0].toString();

            int slotId = parkingLotFactory.slotsOfCarRegistrationNumber(regisNumber);
            System.out.println(slotId);

        } catch (NoSuchCarFoundException ex) {
            System.out.println("Not found");
        }
        return null;
    }

}
