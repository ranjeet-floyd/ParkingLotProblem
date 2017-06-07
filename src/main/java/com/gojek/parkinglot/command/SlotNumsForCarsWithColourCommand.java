package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ranjeet
 */
public class SlotNumsForCarsWithColourCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public SlotNumsForCarsWithColourCommand() {
        this.parkingLotFactory = ParkingLotSingleton.getInstance();
    }

    @Override
    public <T> T apply(Object... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("array len 1  and must contains color");
        }
        String color = values[0].toString();

        List<Integer> slotIds = parkingLotFactory.slotsOfCarColor(color);
        List<String> strSlotIds = slotIds.stream()
                .map(i -> i.toString())
                .collect(Collectors.toList());
        System.out.println(String.join(", ", strSlotIds));
        return null;
    }

}
