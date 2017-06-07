package com.gojek.parkinglot.command;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.bean.ParkingLot;
import java.util.Map;

/**
 *
 * @author ranjeet
 */
public class StatusCommand implements Command {

    public StatusCommand() {
    }

    @Override
    public Object apply(Object... values) throws Exception {
        if (values.length != 1 && values[0] instanceof ParkingLotFactory) {
            throw new IllegalArgumentException("array must contains parkingLotFactory obj");
        }
        ParkingLotFactory parkingLotFactory = (ParkingLotFactory) values[0];
        Map<Integer, ParkingLot> parkingLotRepo = parkingLotFactory.status();
//        System.out.println(String.format("%4d", 5));
        System.out.println("Slot No. Registration No Colour");
        parkingLotRepo.entrySet().stream()
                .map(s -> s.getValue())
                .forEach(p -> {
                    System.out.print(String.format("%-9d", p.getId()));
                    System.out.print(String.format("%8s", p.getParkedCar().getRegistrationNumber()));
                    System.out.println(String.format("%8s", p.getParkedCar().getColor()));
                });
        return null;
    }

}
