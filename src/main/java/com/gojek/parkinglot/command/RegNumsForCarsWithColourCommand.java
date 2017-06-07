package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import java.util.List;

/**
 *
 * @author ranjeet
 */
public class RegNumsForCarsWithColourCommand implements Command {

    @Override
    public <T> T apply(Object... values) throws Exception {
        if (values.length != 2 && values[0] instanceof ParkingLotFactory) {
            throw new IllegalArgumentException("array len 2  and must contains parkingLotFactory obj");
        }
        ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
        String color = values[1].toString();

        List<String> regisNums = parkingLotFactory.registrationNumbersOfColor(color);
        System.out.println(String.join(", ", regisNums));
        return null;
    }

}
