package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.bean.Vehicle;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import com.gojek.parkinglot.objects.ParkingLotSingleton;
import java.util.List;
import java.util.function.Predicate;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class ParkingLotFactoryNGTest {

    private static final ParkingLotSingleton PARKING_LOT_SINGLETON = ParkingLotSingleton.getInstance();

    public ParkingLotFactoryNGTest() {
    }

    /**
     * Test of park method, of class ParkingLotSingleton.
     */
    @Test
    public void testPark_FirstCar() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        int expResult = 1; //park at fi
        int result = PARKING_LOT_SINGLETON.park(car1);
        assertEquals(expResult, result);
    }

    /**
     * Test of park method, of class ParkingLotSingleton.
     */
    @Test
    public void testPark_Init_6_slots() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark_Init_6_slots()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        Vehicle car2 = new Car("White", "KA-01-HH-9999");
        Vehicle car3 = new Car("Black", "KA-01-BB-0001");
        Vehicle car4 = new Car("Blue", "KA-01-HH-2701");
        Vehicle car5 = new Car("Black", "KA-01-HH-3141");
        Vehicle car6 = new Car("Black", "KA-01-P-333");
        assertEquals(1, PARKING_LOT_SINGLETON.park(car1));
        assertEquals(2, PARKING_LOT_SINGLETON.park(car2));
        assertEquals(3, PARKING_LOT_SINGLETON.park(car3));
        assertEquals(4, PARKING_LOT_SINGLETON.park(car4));
        assertEquals(5, PARKING_LOT_SINGLETON.park(car5));
        assertEquals(6, PARKING_LOT_SINGLETON.park(car6));

    }

    /**
     * Test of park method, of class ParkingLotSingleton.
     */
    @Test(expectedExceptions = NoSpaceException.class)
    public void testPark_NoSpaceException() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark_NoSpaceException()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        Vehicle car2 = new Car("White", "KA-01-HH-9999");
        assertEquals(1, PARKING_LOT_SINGLETON.park(car1));
        assertEquals(2, PARKING_LOT_SINGLETON.park(car2));
    }

    /**
     * Test of unPark method, of class ParkingLotSingleton.
     */
    @Test
    public void testUnPark() throws NoSuchCarFoundException, NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testUnPark()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        int result = PARKING_LOT_SINGLETON.park(car1);
        Vehicle parkedCar = PARKING_LOT_SINGLETON.unPark(result);
        assertEquals(car1, parkedCar);
    }

    /**
     * Test of unPark method, of class ParkingLotSingleton.
     */
    @Test(expectedExceptions = NoSuchCarFoundException.class)
    public void testUnPark_NoSuchCarFoundException() throws NoSuchCarFoundException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testUnPark_NoSuchCarFoundException()");
       Vehicle  parkedCar = PARKING_LOT_SINGLETON.unPark(2);
    }

    /**
     * Test of registrationNumbersOfColor method, of class ParkingLotSingleton.
     */
    @Test
    public void testRegistrationNumbersOfColor() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testRegistrationNumbersOfColor()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        Vehicle car2 = new Car("White", "KA-01-HH-9999");
        Vehicle car3 = new Car("Black", "KA-01-BB-0001");
        Vehicle car4 = new Car("Blue", "KA-01-HH-2701");
        Vehicle car5 = new Car("Black", "KA-01-HH-3141");
        Vehicle car6 = new Car("Black", "KA-01-P-333");
        PARKING_LOT_SINGLETON.park(car1);
        PARKING_LOT_SINGLETON.park(car2);
        PARKING_LOT_SINGLETON.park(car3);
        PARKING_LOT_SINGLETON.park(car4);
        PARKING_LOT_SINGLETON.park(car5);
        PARKING_LOT_SINGLETON.park(car6);
        String color = "White";
        List<String> regisNums = PARKING_LOT_SINGLETON.registrationNumbersOfColor(color);
        assertEquals(2, regisNums.size());
        Predicate<String> allRegpredicate = reg -> reg.equalsIgnoreCase("KA-01-HH-1234")
                || reg.equalsIgnoreCase("KA-01-HH-9999");
        boolean isAllRegPresent = regisNums.stream().allMatch(allRegpredicate);
        assertEquals(true, isAllRegPresent);
    }

    /**
     * Test of slotsOfCarRegistrationNumber method, of class ParkingLotSingleton.
     */
    @Test
    public void testSlotsOfCarRegistrationNumber() throws Exception {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testSlotsOfCarRegistrationNumber()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        Vehicle car2 = new Car("White", "KA-01-HH-9999");
        Vehicle car3 = new Car("Black", "KA-01-BB-0001");
        Vehicle car4 = new Car("Blue", "KA-01-HH-2701");
        Vehicle car5 = new Car("Black", "KA-01-HH-3141");
        Vehicle car6 = new Car("Black", "KA-01-P-333");
        PARKING_LOT_SINGLETON.park(car1);
        PARKING_LOT_SINGLETON.park(car2);
        PARKING_LOT_SINGLETON.park(car3); //parked @ 3
        PARKING_LOT_SINGLETON.park(car4);
        PARKING_LOT_SINGLETON.park(car5);
        PARKING_LOT_SINGLETON.park(car6);
        String registrationNo = "KA-01-BB-0001"; //there is only one
        int parkedId = PARKING_LOT_SINGLETON.slotsOfCarRegistrationNumber(registrationNo);
        assertEquals(3, parkedId);
    }

    /**
     * Test of slotsOfCarColor method, of class ParkingLotSingleton.
     */
    @Test
    public void testSlotsOfCarColor() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testSlotsOfCarColor()");
        Vehicle car1 = new Car("White", "KA-01-HH-1234");
        Vehicle car2 = new Car("White", "KA-01-HH-9999");
        Vehicle car3 = new Car("Black", "KA-01-BB-0001");
        Vehicle car4 = new Car("Blue", "KA-01-HH-2701");
        Vehicle car5 = new Car("Black", "KA-01-HH-3141");
        Vehicle car6 = new Car("Black", "KA-01-P-333");
        PARKING_LOT_SINGLETON.park(car1); //park @1
        PARKING_LOT_SINGLETON.park(car2);//park @2
        PARKING_LOT_SINGLETON.park(car3); ////park @3
        PARKING_LOT_SINGLETON.park(car4); //park @4
        PARKING_LOT_SINGLETON.park(car5);//park @5
        PARKING_LOT_SINGLETON.park(car6);//park @6
        String color = "black"; //park @3, 5 and 6
        List<Integer> slots = PARKING_LOT_SINGLETON.slotsOfCarColor(color);
        assertEquals(3, slots.size());

        Predicate<Integer> allSlotIdpredicate = slotId -> slotId == 5
                || slotId == 6 || slotId == 3;
        boolean isAllSlotPresent = slots.stream().allMatch(allSlotIdpredicate);
        assertEquals(true, isAllSlotPresent);
    }

}
