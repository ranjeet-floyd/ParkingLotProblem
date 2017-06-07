package com.gojek.parkinglot.command;

import com.gojek.parkinglot.objects.ParkingLotSingleton;
import java.util.List;

/**
 *
 * @author ranjeet
 */
public class RegNumsForCarsWithColourCommand implements Command {

    ParkingLotSingleton parkingLotFactory ;

    public RegNumsForCarsWithColourCommand() {
        this.parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }
    
    
    @Override
    public <T> T apply(Object... values)  {
        if (values.length != 1 ) {
            throw new IllegalArgumentException("array len 2  and must contains parkingLotFactory obj");
        }
        String color = values[0].toString();

        List<String> regisNums = parkingLotFactory.getParkingLot().registrationNumbersOfColor(color);
        System.out.println(String.join(", ", regisNums));
        return null;
    }

}
