package com.gojek.parkinglot;

import com.gojek.parkinglot.bean.Car;
import com.gojek.parkinglot.exception.NoSpaceException;
import com.gojek.parkinglot.exception.NoSuchCarFoundException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class ParkingLotNGTest {

    private static ParkingLot entryParking;

    public ParkingLotNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        entryParking = new ParkingLot(1);
        ParkingLot p2 = new ParkingLot(2);
        ParkingLot p3 = new ParkingLot(3);
        ParkingLot p4 = new ParkingLot(4);
        ParkingLot p5 = new ParkingLot(5);
        entryParking.getAdjacent().add(p2);
        entryParking.getAdjacent().add(p3);
        entryParking.getAdjacent().add(p4);
        entryParking.getAdjacent().add(p5);

    }

    /**
     * Park first car @ parking id : 1
     */
    @Test(priority = 1)
    public void testParkMyCar_AT_ID_1() throws Exception {
        Car carInfo = new Car("White", "KA-01-HH-1234");
        System.out.println("park my car : " + carInfo.toString());
        int expResult = 1;
        int result = entryParking.park(carInfo);
        //check parking lot id
        assertEquals(result, expResult);
        //check parked car
        assertEquals(entryParking.getCarInfo(), carInfo);
        //check is it empty.
        assertEquals(entryParking.isEmpty(entryParking), false);
    }

    /**
     * UnPark first car ..parkingLot must be empty now.
     */
    @Test(priority = 2)
    public void testUnParkFirstCar() throws Exception {
        Car carInfo = new Car("White", "KA-01-HH-1234");
        System.out.println("unPark first car : " + carInfo.toString());
        entryParking.unPark(carInfo);
        boolean isParkingLotEmpty = entryParking.isEmpty(entryParking);
        assertEquals(true, isParkingLotEmpty);

    }

    /**
     * UnPark first car ..parkingLot will be empty now.
     */
    @Test(priority = 3, expectedExceptions = NoSuchCarFoundException.class)
    public void testNoCarFoundException() throws Exception {
        Car carInfo = new Car("White", "KA-01-HH-1234");
        System.out.println("car not found : " + carInfo.toString());
        entryParking.unPark(carInfo);

    }

    /**
     * Park multiple car and check if parking at next nearest slot or not.
     */
    @Test(priority = 4)
    public void testForNextParkingLot() throws Exception {
        Car car1 = new Car("White", "KA-01-HH-1234");
        Car car2 = new Car("White", "KA-01-HH-9999");
        Car car3 = new Car("Black", "KA-01-BB-0001");

        //park 1 car |check parking lot id
        System.out.println("park my car @Id 1 : " + car1.toString());
        assertEquals("park my car @Id 1 : " + car1.toString(), 1, entryParking.park(car1));

        //park 2 car |check parking lot id
        System.out.println("park my car @Id 2 : " + car2.toString());
        assertEquals(2, entryParking.park(car2));

        //park 3 car |check parking lot id
        System.out.println("park my car @Id 3 : " + car3.toString());
        assertEquals(3, entryParking.park(car3));

        //unpark 2 car |check parking lot id
        System.out.println("unpark my car @Id 2 : " + car2.toString());
        entryParking.unPark(car2);
        //park car 4 and check id ..must be 2 
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        System.out.println("park my car @Id 2 : " + car4.toString());
        assertEquals(2, entryParking.park(car4));
    }

    /**
     * No space available for parking. | only 2 space left
     */
    @Test(priority = 5, expectedExceptions = NoSpaceException.class)
    public void testNoSpaceException() throws Exception {
        Car car4 = new Car("Blue", "KA-01-HH-2701");
        Car car5 = new Car("Black", "KA-01-HH-3141");
        Car car6 = new Car("Black", "KA-01-P-333");

        //park  car |check parking lot id
        assertEquals("park my car @Id 4 : " + car4.toString(), 4, entryParking.park(car4));
        assertEquals("park my car @Id 5 : " + car5.toString(), 5, entryParking.park(car5));
        assertEquals("park my car @Id 6 : " + car6.toString(), 6, entryParking.park(car6));
    }

}
