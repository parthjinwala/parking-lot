package parkingLot;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParkingLotTest {
	@Test
	public void testPark() {
		ParkingLot pl = new ParkingLot(6);
		Vehicle v = new Vehicle();
		v.setColor("White");v.setRegNumber("KA-01-HH-1234");
		assertEquals("Allocated slot number: 1", pl.park(v));
		v.setColor("White");v.setRegNumber("KA-01-HH-1235");
		pl.park(v);
		v.setColor("White");v.setRegNumber("KA-01-HH-1236");
		pl.park(v);
		v.setColor("White");v.setRegNumber("KA-01-HH-1237");
		pl.park(v);
		v.setColor("White");v.setRegNumber("KA-01-HH-1238");
		pl.park(v);
		v.setColor("White");v.setRegNumber("KA-01-HH-1239");
		pl.park(v);
		v.setColor("White");v.setRegNumber("KA-01-HH-1230");
		assertEquals("Sorry, parking lot is full", pl.park(v));
	}
	
	@Test
	public void testLeave() {
		ParkingLot pl = new ParkingLot(6);
		Vehicle v = new Vehicle();
		v.setColor("White");v.setRegNumber("KA-01-HH-1234");
		
		assertEquals("Slot number 1 is free", pl.leave(1));
		assertEquals("Please enter slot number within Parking Lot size", pl.leave(7));
	}
	
	@Test
	public void testGetSlotForCar() {
		ParkingLot pl = new ParkingLot(6);
		Vehicle v1 = new Vehicle();
		v1.setColor("White");v1.setRegNumber("KA-01-HH-1234");
		pl.park(v1);
		Vehicle v2 = new Vehicle();
		v2.setColor("White");v2.setRegNumber("KA-01-HH-1235");
		pl.park(v2);
		assertEquals("1, 2",pl.getSlotForCar("colour", "White"));
		assertEquals("2",pl.getSlotForCar("registration number", "KA-01-HH-1235"));
		pl.leave(2);
		assertEquals("1",pl.getSlotForCar("colour", "White"));
		
	}
	
	@Test
	public void testGetRegistrationNumberForCar() {
		
	}
}
