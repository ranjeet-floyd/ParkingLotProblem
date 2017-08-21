package com.example.parkinglot.command;

import com.example.parkinglot.bean.Car;
import com.example.parkinglot.exception.NoSpaceException;
import com.exampleparkinglot.objects.ParkingLotSingleton;

/**
 *
 * @author ranjeet
 */
public class ParkCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public ParkCommand() {
        parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(String... values) {

        StringBuilder builder = new StringBuilder();
        if (values.length != 2) {
            builder.append("str array should contains  car reg and color");
            return builder.toString();
        }

        try {
            String regNo =  values[0];
            String color =  values[1];
            int slotId = parkingLotFactory.getParkingLot().park(new Car(color, regNo));
            builder.append("Allocated slot number: ");
            builder.append(slotId);

        } catch (NoSpaceException ex) {
            builder.append("Sorry, parking lot is full");
        }
        return builder.toString();
    }

}
