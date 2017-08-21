package com.example.parkinglot.command;

import com.exampleparkinglot.objects.ParkingLotSingleton;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ranjeet
 */
public class SlotNumsForCarsWithColourCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public SlotNumsForCarsWithColourCommand() {
        this.parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(String... values) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 1) {
            builder.append("array len 1  and must contains color");
            return builder.toString();
        }
        String color = values[0];
        List<Integer> slotIds = parkingLotFactory.getParkingLot().slotsOfCarColor(color);
        List<String> strSlotIds = slotIds.stream()
                .map(i -> i.toString())
                .collect(Collectors.toList());
        builder.append(String.join(", ", strSlotIds));
        return builder.toString();
    }

}
