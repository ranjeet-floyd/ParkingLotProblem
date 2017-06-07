package com.gojek.parkinglot.command;

import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import com.gojek.parkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class SlotNumForRegisNumberCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public SlotNumForRegisNumberCommand() {
        parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(Object... values) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 1) {
            builder.append("array len 1  and must contains regisNumber");
            return builder.toString();
        }
        try {
            String regisNumber = values[0].toString();
            int slotId = parkingLotFactory.getParkingLot().slotsOfCarRegistrationNumber(regisNumber);
            builder.append(slotId);

        } catch (NoSuchCarFoundException ex) {
            builder.append("Not found");
        }
        return builder.toString();
    }

}
