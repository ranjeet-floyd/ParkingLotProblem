package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.bean.Car;

/**
 *
 * @author ranjeet
 */
public class LeaveCommand implements Command {

    private ParkingLotFactory parkingLotFactory;

    public LeaveCommand(ParkingLotFactory parkingLotFactory) {
        this.parkingLotFactory = parkingLotFactory;
    }

    @Override
    public Car apply(String... values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("str array should contain int Slot id");
        }
        int slotId = Integer.parseInt(values[0]);
        return null;
//        parkingLotFactory.unPark(null);
    }

}
