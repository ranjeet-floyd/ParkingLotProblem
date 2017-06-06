package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.bean.ParkingLot;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author ranjeet
 */
public class ParkingLotFactory {

    //store parked car info
    private final Map<Integer, ParkingLot> parkingLotRepo;
    private final List<ParkingLot> parkingLots;

    private ParkingLotFactory(int numberOfParkingSlots) {
        this.parkingLotRepo = new HashMap<>(numberOfParkingSlots);
        this.parkingLots = new ArrayList<>(numberOfParkingSlots);
    }

    public static ParkingLotFactory initParkingLots(int numberOfParkingSlots) {
        final ParkingLotFactory parkingLotFactory = new ParkingLotFactory(numberOfParkingSlots);
        for (int i = 0; i < numberOfParkingSlots; i++) {
            parkingLotFactory.parkingLots.add(new ParkingLot(i + 1));
        }
        return parkingLotFactory;
    }

    /**
     * Park car and return slot id.
     *
     * @param car
     * @return parking slot id
     * @throws com.gojek.parkinglot.NoSpaceException : If no space left
     */
    public int park(Car car) throws NoSpaceException {
        Optional<ParkingLot> nextEmptyParkingLot = searchNextEmpty();
        int slotId = nextEmptyParkingLot.map(c -> c.getId())
                .orElseThrow(() -> new NoSpaceException("No Space left for car :" + car.toString()));
        ParkingLot parkingLot = nextEmptyParkingLot.get();
        parkingLot.setParkedCar(car); //car car
        parkingLotRepo.put(parkingLot.getId(), parkingLot);
        return slotId;

    }

    /**
     * Remove car from parking slot if given carInfo is present.
     *
     * @param slotId
     * @return parked lot id
     * @throws com.gojek.parkinglot.exception.NoSuchCarFoundException
     */
    public Car unPark(int slotId) throws NoSuchCarFoundException {
        ParkingLot parkedParkingLot = parkingLotRepo.remove(slotId);
        if (Objects.isNull(parkedParkingLot)) {
            throw new NoSuchCarFoundException("Provide car is not found @ SlotId :" + slotId);
        } else {
            Car parkedCar = parkedParkingLot.getParkedCar();
            parkedParkingLot.setParkedCar(null); //remove car
            return parkedCar;
        }

    }

    /**
     * Registration numbers of all cars of a particular colour that are parked.
     *
     * @param color
     * @return list of registration number of car color
     */
    public List<String> registrationNumbersOfColor(String color) {
        return this.parkingLotRepo.entrySet()
                .stream()
                .map(m -> m.getValue().getParkedCar())
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .map(car -> car.getRegistrationNumber())
                .collect(Collectors.toList());

    }

    /**
     * Slot number in which a car with a given registration number is parked.
     *
     * @param registrationNo
     * @return slot ids for car registration number
     * @throws com.gojek.parkinglot.exception.NoSuchCarFoundException
     */
    public int slotsOfCarRegistrationNumber(String registrationNo) throws NoSuchCarFoundException {
        List<Integer> slots = this.parkingLotRepo.entrySet()
                .stream()
                .map(m -> m.getValue())
                .filter(p -> p.getParkedCar().getRegistrationNumber()
                .equalsIgnoreCase(registrationNo))
                .map(p -> p.getId())
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
     * All slots where a car of a particular colour is parked.
     *
     * @param color
     * @return slot ids for car color
     */
    public List<Integer> slotsOfCarColor(String color) {
        return this.parkingLotRepo.entrySet()
                .stream()
                .map(m -> m.getValue())
                .filter(p -> p.getParkedCar().getColor()
                .equalsIgnoreCase(color))
                .map(p -> p.getId())
                .collect(Collectors.toList());
    }

    /**
     * Search for next empty parking lot. If found return ParkingLotSystem else
     * null.
     *
     * @param fromEntryParking : nearest to entry.
     * @return parkingLot if available
     */
    private Optional<ParkingLot> searchNextEmpty() throws NoSpaceException {
        return this.parkingLots.stream()
                .filter(this::isEmpty)
                .findFirst();
    }

    private boolean isEmpty(ParkingLot parkingLot) {
        return Objects.isNull(parkingLot.getParkedCar());
    }

}
