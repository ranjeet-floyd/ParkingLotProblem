package com.example.parkinglot.dao;

import com.example.parkinglot.bean.ParkingLot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author ranjeet
 */
public class ParkingLotDaoImp implements ParkingLotDao {

    private final Map<Integer, ParkingLot> parkingLotRepo;
    private final List<ParkingLot> parkingLots;

    public ParkingLotDaoImp() {
        this.parkingLotRepo = new HashMap<>();
        this.parkingLots = new ArrayList<>();
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
        parkingLots.clear();
    }

    @Override
    public void delete(int slotId) {
        parkingLotRepo.remove(slotId);
    }

    @Override
    public ParkingLot get(int slotId) {
        return parkingLotRepo.get(slotId);
    }

    @Override
    public Optional<ParkingLot> searchNextEmpty() {
        return this.parkingLots.stream()
                .filter(p -> Objects.isNull(p.getVehicle()))
                .findFirst();
    }

    @Override
    public void createSlots(int numberOfParkingSlots) {
        this.clear();
        for (int i = 0; i < numberOfParkingSlots; i++) {
            this.parkingLots.add(new ParkingLot(i + 1));
        }
    }

}
