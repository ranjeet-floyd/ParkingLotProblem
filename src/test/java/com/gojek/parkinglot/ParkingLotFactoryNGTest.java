package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import java.util.List;
import java.util.function.Predicate;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class ParkingLotFactoryNGTest {

    public ParkingLotFactoryNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of park method, of class ParkingLotFactory.
     */
    @Test
    public void testPark_FirstCar() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(3);
        int expResult = 1; //park at fi
        int result = instance.park(car1);
        assertEquals(expResult, result);
    }

    /**
     * Test of park method, of class ParkingLotFactory.
     */
    @Test
    public void testPark_Init_6_slots() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark_Init_6_slots()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        Car car5 = new Car("Black", "KA-01-HH-3141");
        Car car6 = new Car("Black", "KA-01-P-333");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(6);
        assertEquals(1, instance.park(car1));
        assertEquals(2, instance.park(car2));
        assertEquals(3, instance.park(car3));
        assertEquals(4, instance.park(car4));
        assertEquals(5, instance.park(car5));
        assertEquals(6, instance.park(car6));

    }

    /**
     * Test of park method, of class ParkingLotFactory.
     */
    @Test(expectedExceptions = NoSpaceException.class)
    public void testPark_NoSpaceException() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testPark_NoSpaceException()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(1);
        assertEquals(1, instance.park(car1));
        assertEquals(2, instance.park(car2));
    }

    /**
     * Test of unPark method, of class ParkingLotFactory.
     */
    @Test
    public void testUnPark() throws NoSuchCarFoundException, NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testUnPark()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(1);
        int result = instance.park(car1);
        int parkedId = instance.unPark(car1);
        assertEquals(parkedId, result);
    }

    /**
     * Test of unPark method, of class ParkingLotFactory.
     */
    @Test(expectedExceptions = NoSuchCarFoundException.class)
    public void testUnPark_NoSuchCarFoundException() throws NoSuchCarFoundException {
        System.out.println("com.gojek.parkinglot.ParkingLotAppNGTest.testUnPark_NoSuchCarFoundException()");
        Car car1 = new Car("White", "KA-01-HH-xxxx");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(1);
        int parkedId = instance.unPark(car1);
    }

    /**
     * Test of registrationNumbersOfColor method, of class ParkingLotFactory.
     */
    @Test
    public void testRegistrationNumbersOfColor() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testRegistrationNumbersOfColor()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        Car car5 = new Car("Black", "KA-01-HH-3141");
        Car car6 = new Car("Black", "KA-01-P-333");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(6);
        instance.park(car1);
        instance.park(car2);
        instance.park(car3);
        instance.park(car4);
        instance.park(car5);
        instance.park(car6);
        String color = "White";
        List<String> regisNums = instance.registrationNumbersOfColor(color);
        assertEquals(2, regisNums.size());
        Predicate<String> allRegpredicate = reg -> reg.equalsIgnoreCase("KA-01-HH-1234")
                || reg.equalsIgnoreCase("KA-01-HH-9999");
        boolean isAllRegPresent = regisNums.stream().allMatch(allRegpredicate);
        assertEquals(true, isAllRegPresent);
    }

    /**
     * Test of slotsOfCarRegistrationNumber method, of class ParkingLotFactory.
     */
    @Test
    public void testSlotsOfCarRegistrationNumber() throws Exception {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testSlotsOfCarRegistrationNumber()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        Car car5 = new Car("Black", "KA-01-HH-3141");
        Car car6 = new Car("Black", "KA-01-P-333");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(6);
        instance.park(car1);
        instance.park(car2);
        instance.park(car3); //parked @ 3
        instance.park(car4);
        instance.park(car5);
        instance.park(car6);
        String registrationNo = "KA-01-BB-0001"; //there is only one
        int parkedId = instance.slotsOfCarRegistrationNumber(registrationNo);
        assertEquals(3, parkedId);
    }

    /**
     * Test of slotsOfCarColor method, of class ParkingLotFactory.
     */
    @Test
    public void testSlotsOfCarColor() throws NoSpaceException {
        System.out.println("com.gojek.parkinglot.ParkingLotFactoryNGTest.testSlotsOfCarColor()");
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        Car car5 = new Car("Black", "KA-01-HH-3141");
        Car car6 = new Car("Black", "KA-01-P-333");
        ParkingLotFactory instance = ParkingLotFactory.initParkingLots(6);
        instance.park(car1); //park @1
        instance.park(car2);//park @2
        instance.park(car3); ////park @3
        instance.park(car4); //park @4
        instance.park(car5);//park @5
        instance.park(car6);//park @6
        String color = "black"; //park @3, 5 and 6
        List<Integer> slots = instance.slotsOfCarColor(color);
        assertEquals(3, slots.size());

        Predicate<Integer> allSlotIdpredicate = slotId -> slotId == 5
                || slotId == 6 || slotId == 3;
        boolean isAllSlotPresent = slots.stream().allMatch(allSlotIdpredicate);
        assertEquals(true, isAllSlotPresent);
    }

}
