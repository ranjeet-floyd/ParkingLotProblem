package com.gojek.parkinglot.objects;

import com.gojek.parkinglot.bl.ParkingLotBl;
import com.gojek.parkinglot.dao.ParkingLotDao;
import com.gojek.parkinglot.dao.ParkingLotDaoImp;
import java.util.Objects;

/**
 * Singleton ParkingLot
 *
 * @author ranjeet
 */
public enum ParkingLotSingleton {

    INSTANCE;
    private static ParkingLotBl parkingLotBl;

    public ParkingLotBl getParkingLot() {
        if (Objects.isNull(parkingLotBl)) {
            ParkingLotDao parkingLotDao = new ParkingLotDaoImp();
            parkingLotBl = new ParkingLotBl(parkingLotDao);
        }

        return parkingLotBl;
    }

}
