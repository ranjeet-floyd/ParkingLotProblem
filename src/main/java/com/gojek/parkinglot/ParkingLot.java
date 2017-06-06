package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Parking lot | Park car to nearest empty parking lot space.
 *
 * @author ranjeet
 */
public class ParkingLot {

    private static final Map<Car, ParkingLot> PARKING_LOOKUP = new HashMap<>();
    private final LinkedList<ParkingLot> adjacent = new LinkedList<>();
    private final int id;
    private Car carInfo;

    /**
     * Init parking lot with carInfo null means empty. Each parking lot must
     * have unique id.
     *
     * @param id
     */
    public ParkingLot(int id) {
        this.id = id;
        this.carInfo = null;
    }

    /**
     * Park car and return slot id.
     *
     * @param car
     * @return parking slot id
     * @throws com.gojek.parkinglot.NoSpaceException : If no space left
     */
    public int park(Car car) throws NoSpaceException {
        ParkingLot nextEmptyParkingLot = searchNextEmpty(this);
        if (Objects.nonNull(nextEmptyParkingLot)) {
            nextEmptyParkingLot.setCarInfo(car);
            PARKING_LOOKUP.put(car, nextEmptyParkingLot);
            return nextEmptyParkingLot.getId();
        }
        throw new NoSpaceException("No space available for car : REG_NO:" + car.getRegistrationNumber());

    }

    /**
     * Remove car from parking slot if given carInfo is present.
     *
     * @param carInfo
     * @throws com.gojek.parkinglot.exception.NoSuchCarFoundException
     */
    public void unPark(Car carInfo) throws NoSuchCarFoundException {
        ParkingLot parkedParkingLot = PARKING_LOOKUP.remove(carInfo.getRegistrationNumber());
        if (Objects.isNull(parkedParkingLot)) {
            throw new NoSuchCarFoundException("Provide car is not found : REG_NO :" + carInfo.getRegistrationNumber());
        } else {
            parkedParkingLot.setCarInfo(null); //remove car
        }

    }

    /**
     * Search for next empty parking lot. If found return ParkingLot else null.
     *
     * @param fromEntryParking : nearest to entry.
     * @return parkingLot if available
     */
    private ParkingLot searchNextEmpty(ParkingLot fromEntryParking) {

        LinkedList<ParkingLot> nextToPark = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        nextToPark.add(fromEntryParking); // search from root.

        while (!nextToPark.isEmpty()) {

            ParkingLot nextParking = nextToPark.remove();

            //if  empty ..we have got parking slot
            if (isEmpty(nextParking)) {
                return nextParking;
            }

            if (visited.contains(nextParking.getId())) {
                continue;//skip ..already checked
            }

            visited.add(nextParking.getId());

            //add all adjacent to check if parking available
            nextParking.adjacent.forEach((p) -> {
                nextToPark.add(p);
            });

        }
        return null; //no parking slot left

    }

    /**
     * If Carinfo is null means parkingLot is empty /available.
     *
     * @param parking
     * @return true if empty.
     */
    public boolean isEmpty(ParkingLot parking) {
        return Objects.isNull(parking.getCarInfo());

    }

    public int getId() {
        return id;
    }

    public Car getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(Car _carInfo) {
        this.carInfo = _carInfo;
    }

    public LinkedList<ParkingLot> getAdjacent() {
        return adjacent;
    }

    public static Map<Car, ParkingLot> getPARKING_LOOKUP() {
        return PARKING_LOOKUP;
    }
    

}
