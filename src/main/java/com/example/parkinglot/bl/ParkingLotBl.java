package com.example.parkinglot.bl;

import com.example.parkinglot.bean.ParkingLot;
import com.example.parkinglot.bean.Vehicle;
import com.example.parkinglot.dao.ParkingLotDao;
import com.example.parkinglot.exception.NoSpaceException;
import com.example.parkinglot.exception.NoSuchCarFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Parking Lot Bussiness layer
 *
 * @author ranjeet
 */
public class ParkingLotBl {

    private final ParkingLotDao parkingLotDao;

    public ParkingLotBl(ParkingLotDao parkingLotDao) {
        this.parkingLotDao = parkingLotDao;
    }

    public void initParkingLots(int numberOfParkingSlots) {
        parkingLotDao.createSlots(numberOfParkingSlots);
    }

    /**
     * Park car and return slot id.
     *
     * @param vehicle
     * @return parking slot id
     * @throws com.gojek.parkinglot.NoSpaceException : If no space left
     */
    public int park(Vehicle vehicle) throws NoSpaceException {
        Optional<ParkingLot> nextEmptyParkingLot = searchNextEmpty();
        int slotId = nextEmptyParkingLot.map(c -> c.getId())
                .orElseThrow(() -> new NoSpaceException("No Space left for vehicle :" + vehicle.toString()));
        ParkingLot parkingLot = nextEmptyParkingLot.get();
        parkingLot.setParkedVehicle(vehicle); //park vehicle
        parkingLotDao.addToParkedSlot(parkingLot);
        return slotId;

    }

    /**
     * Remove car from parking slot if given carInfo is present.
     *
     * @param slotId
     * @return parked lot id
     * @throws com.example.parkinglot.exception.NoSuchCarFoundException
     */
    public Vehicle unPark(int slotId) throws NoSuchCarFoundException {
        ParkingLot parkedParkingLot = parkingLotDao.get(slotId);
        if (Objects.isNull(parkedParkingLot)) {
            throw new NoSuchCarFoundException("Provide vehicle is not found @ SlotId :" + slotId);
        } else {
            parkingLotDao.delete(slotId);
            Vehicle parkedVehicle = parkedParkingLot.getVehicle();
            parkedParkingLot.setParkedVehicle(null); //remove car
            return parkedVehicle;
        }

    }

    /**
     * Registration numbers of all cars of a particular colour that are parked.
     *
     * @param color
     * @return list of registration number of car color
     */
    public List<String> registrationNumbersOfColor(String color) {
        return parkingLotDao.getAllParkedSlot().entrySet()
                .stream()
                .map(m -> m.getValue().getVehicle())
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .map(car -> car.getRegistrationNumber())
                .collect(Collectors.toList());

    }

    /**
     * Slot number in which a car with a given registration number is parked.
     *
     * @param registrationNo
     * @return slot ids for car registration number
     * @throws com.example.parkinglot.exception.NoSuchCarFoundException
     */
    public int slotsOfCarRegistrationNumber(String registrationNo) throws NoSuchCarFoundException {
        List<Integer> slots = parkingLotDao.getAllParkedSlot().entrySet()
                .stream()
                .map(m -> m.getValue())
                .filter(p -> p.getVehicle().getRegistrationNumber()
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
     * Slot numbers of all slots where a car of a particular colour is parked.
     *
     * @param color
     * @return slot ids for car color
     */
    public List<Integer> slotsOfCarColor(String color) {
        return parkingLotDao.getAllParkedSlot().entrySet()
                .stream()
                .map(m -> m.getValue())
                .filter(p -> p.getVehicle().getColor()
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
        return parkingLotDao.searchNextEmpty();
    }

    /**
     * Get the current status of parking lot | Display cars
     *
     * @return parkingLotRepo
     */
    public Map<Integer, ParkingLot> status() {
        return parkingLotDao.getAllParkedSlot();

    }

}
