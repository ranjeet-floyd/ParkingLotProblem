package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * utils for parkingLot
 *
 * @author ranjeet
 */
public class ParkingLotUtil {

    public static final ParkingLotUtil PARKING_LOT_UTIL = new ParkingLotUtil();

    private ParkingLotUtil() {
    }

    /**
     * Registration numbers of all cars of a particular colour that are parked.
     *
     * @param color
     * @return list of registration number of car color
     */
    public List<String> registrationNumbersOfColor(String color) {
        return ParkingLot.getPARKING_LOOKUP().entrySet()
                .stream()
                .filter(car -> car.getKey().getColor()
                .equalsIgnoreCase(color))
                .map(p -> p.getKey().getRegistrationNumber())
                .collect(Collectors.toList());

    }

    /**
     * Slot number in which a car with a given registration number is parked.
     *
     * @param registrationNo
     * @return slot ids for car registration number
     */
    public int slotsOfCarRegistrationNumber(String registrationNo) throws NoSuchCarFoundException {
        List<Integer> slots = ParkingLot.getPARKING_LOOKUP().entrySet()
                .stream()
                .filter(car -> car.getKey().getRegistrationNumber()
                .equalsIgnoreCase(registrationNo))
                .map(p -> p.getValue().getId())
                .collect(Collectors.toList());

        if (slots.size() == 1) {

            return slots.get(0);
        }

        if (slots.isEmpty()) {
            throw new NoSuchCarFoundException("No car found with regis No:" + registrationNo);
        }

        throw new RuntimeException("Something wrong |Multiple car found for regis No: " + registrationNo);

    }

    /**
     * Slot numbers of all slots where a car of a particular colour is parked.
     *
     * @param color
     * @return slot ids for car color
     */
    public List<Integer> slotsOfCarColor(String color) {
        return ParkingLot.getPARKING_LOOKUP().entrySet()
                .stream()
                .filter(car -> car.getKey().getColor()
                .equalsIgnoreCase(color))
                .map(p -> p.getValue().getId())
                .collect(Collectors.toList());
    }
}
