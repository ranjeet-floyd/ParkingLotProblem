package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.CarInfo;
import java.util.LinkedList;

/**
 * Parking lot | Park car to nearest empty parking lot space.
 *
 * @author ranjeet
 */
public class ParkingLot {

    private final LinkedList<ParkingLot> adjacent = new LinkedList<>();
    private final int id;
    private final CarInfo carInfo;

    public ParkingLot(int _id) {
        this.id = _id;
        carInfo = null;
    }
}
