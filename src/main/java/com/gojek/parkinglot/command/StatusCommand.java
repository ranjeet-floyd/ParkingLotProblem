package com.gojek.parkinglot.command;

import com.gojek.parkinglot.bean.ParkingLot;
import com.gojek.parkinglot.objects.ParkingLotSingleton;
import java.util.Map;

/**
 *
 * @author ranjeet
 */
public class StatusCommand implements Command {

    private final ParkingLotSingleton parkingLotFactory;

    public StatusCommand() {
        parkingLotFactory = ParkingLotSingleton.INSTANCE;
    }

    @Override
    public Object apply(Object... values) throws Exception {
        Map<Integer, ParkingLot> parkingLotRepo = parkingLotFactory.getParkingLot().status();
        System.out.println("Slot No. Registration No Colour");
        parkingLotRepo.entrySet().stream()
                .map(s -> s.getValue())
                .forEach(p -> {
                    System.out.print(String.format("%-9d", p.getId()));
                    System.out.print(String.format("%8s", p.getVehicle().getRegistrationNumber()));
                    System.out.println(String.format("%8s", p.getVehicle().getColor()));
                });
        return null;
    }

}
