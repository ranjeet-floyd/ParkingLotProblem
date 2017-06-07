package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;
import java.util.List;

/**
 *
 * @author ranjeet
 */
public class RegNumsForCarsWithColourCommand implements Command {

    ParkingLotSingleton parkingLotFactory;

    public RegNumsForCarsWithColourCommand() {
        this.parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(Object... values) {
        StringBuilder builder = new StringBuilder();
        if (values.length != 1) {
            builder.append("array len 1  and must contains vehicle color");
            return builder.toString();
        }

        String color = values[0].toString();
        List<String> regisNums = parkingLotFactory.getParkingLot().registrationNumbersOfColor(color);
        builder.append(String.join(", ", regisNums));
        return builder.toString();
    }

}
