package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.bean.ParkingLot;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ranjeet
 */
public class ParkingLotDaoImp implements ParkingLotDao {

    private final Map<Integer, ParkingLot> parkingLotRepo;

    public ParkingLotDaoImp() {
        this.parkingLotRepo = new HashMap<>();
    }

    @Override
    public Map<Integer, ParkingLot> getAllParkedSlot() {
        return parkingLotRepo;
    }

    @Override
    public void addToParkedSlot(ParkingLot parkingLot) {
        parkingLotRepo.put(parkingLot.getId(), parkingLot);

    }

    @Override
    public void clear() {
        parkingLotRepo.clear();
    }

    @Override
    public void delete(int slotId) {
        parkingLotRepo.remove(slotId);
    }

    @Override
    public ParkingLot get(int slotId) {
        return parkingLotRepo.get(slotId);
    }

}
