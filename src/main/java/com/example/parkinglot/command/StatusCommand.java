package com.example.parkinglot.command;

import com.example.parkinglot.bean.ParkingLot;
import com.exampleparkinglot.objects.ParkingLotSingleton;
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
    public Object apply(String... values) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, ParkingLot> parkingLotRepo = parkingLotFactory.getParkingLot().status();
        builder.append("Slot No. Registration No Colour");
        builder.append("\n");
        
        parkingLotRepo.entrySet().stream()
                .map(s -> s.getValue())
                .forEach(p -> {
                    builder.append(String.format("%-9d", p.getId()));
                    builder.append(String.format("%8s", p.getVehicle().getRegistrationNumber()));
                    builder.append(String.format("%8s", p.getVehicle().getColor()));
                    builder.append("\n");
                });
        return builder.toString();
    }

}
