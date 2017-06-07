package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ranjeet
 */
public class SlotNumsForCarsWithColourCommand implements Command {

    @Override
    public <T> T apply(Object... values) {
        if (values.length != 2 && values[0] instanceof ParkingLotFactory) {
            throw new IllegalArgumentException("array len 2  and must contains parkingLotFactory obj");
        }
        ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
        String color = values[1].toString();

        List<Integer> slotIds = parkingLotFactory.slotsOfCarColor(color);
        List<String> strSlotIds = slotIds.stream()
                .map(i -> i.toString())
                .collect(Collectors.toList());
        System.out.println(String.join(", ", strSlotIds));
        return null;
    }

}
