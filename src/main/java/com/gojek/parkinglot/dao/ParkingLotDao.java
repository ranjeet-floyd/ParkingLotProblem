package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.bean.ParkingLot;
import java.util.Map;

/**
 *
 * @author ranjeet
 */
public interface ParkingLotDao {

    public Map<Integer, ParkingLot> getAllParkedSlot();

    public void addToParkedSlot(ParkingLot parkingLot);
    
    public void clear();
    
    public void delete(int slotId);
    
    public ParkingLot get(int slotId);
}
