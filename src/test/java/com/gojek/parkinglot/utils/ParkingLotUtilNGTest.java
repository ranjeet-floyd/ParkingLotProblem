package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import static com.gojek.parkinglot.utils.ParkingLotUtil.PARKING_LOT_UTIL;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class ParkingLotUtilNGTest {

    public ParkingLotUtilNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ParkingLot entryParking = new ParkingLot(1);
        ParkingLot p2 = new ParkingLot(2);
        ParkingLot p3 = new ParkingLot(3);
        ParkingLot p4 = new ParkingLot(4);
        ParkingLot p5 = new ParkingLot(5);
        entryParking.getAdjacent().add(p2);
        entryParking.getAdjacent().add(p3);
        entryParking.getAdjacent().add(p4);
        entryParking.getAdjacent().add(p5);

        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");
        Car car4 = new Car("Blue", "KA-01-HH-2701");

        //park 1 car |check parking lot id
        entryParking.park(car1);

        //park 2 car |check parking lot id
        entryParking.park(car2);

        //park 3 car |check parking lot id
        entryParking.park(car3);

        //park car 4 and check id ..must be 2 
        entryParking.park(car4);

    }

    /**
     * Test of registrationNumbersOfColor method, of class ParkingLotUtil.
     */
    @Test
    public void testRegistrationNumbersOfColor() {
        System.out.println("registrationNumbersOfColor");
        String color = "White";
        ParkingLotUtil instance = PARKING_LOT_UTIL;
        List<String> result = instance.registrationNumbersOfColor(color);
        assertEquals(2, result.size());
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        boolean allCarPresent = result.stream()
                .allMatch(s -> s.equalsIgnoreCase(car1.getRegistrationNumber())
                || s.equalsIgnoreCase(car2.getRegistrationNumber()));
        assertEquals(true, allCarPresent);
    }

    /**
     * Test of slotsOfCarRegistrationNumber method, of class ParkingLotUtil.
     *
     * @throws com.gojek.parkinglot.exception.NoSuchCarFoundException
     */
    @Test
    public void testSlotsOfCarRegistrationNumber() throws NoSuchCarFoundException {
        System.out.println("slotsOfCarRegistrationNumber");
        String registrationNo = "KA-01-BB-0001";
        ParkingLotUtil instance = PARKING_LOT_UTIL;
        int result = instance.slotsOfCarRegistrationNumber(registrationNo);
        assertEquals(true, result > 0);
    }

    /**
     * Test of slotsOfCarRegistrationNumber method, of class ParkingLotUtil.
     *
     * @throws com.gojek.parkinglot.exception.NoSuchCarFoundException
     */
    @Test(expectedExceptions = NoSuchCarFoundException.class)
    public void testSlotsOfCarRegistrationNumber_NoSuchCarFoundException() throws NoSuchCarFoundException {
        System.out.println("slotsOfCarRegistrationNumber");
        String registrationNo = "KA-01-BB-XXXX";
        ParkingLotUtil instance = PARKING_LOT_UTIL;
        int result = instance.slotsOfCarRegistrationNumber(registrationNo);
    }

    /**
     * Test of slotsOfCarColor method, of class ParkingLotUtil.
     */
    @Test
    public void testSlotsOfCarColor() {
        System.out.println("slotsOfCarColor");
        String color = "Blue";
        ParkingLotUtil instance = PARKING_LOT_UTIL;
        List<Integer> result = instance.slotsOfCarColor(color);
        assertEquals(1, result.size());
    }

}
