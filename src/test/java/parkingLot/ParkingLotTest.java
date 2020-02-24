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
		Vehicle v1 = new Vehicle();
		v1.setColor("White");v1.setRegNumber("KA-01-HH-1235");
		pl.park(v1);
		Vehicle v2 = new Vehicle();
		v2.setColor("White");v2.setRegNumber("KA-01-HH-1236");
		pl.park(v2);
		Vehicle v3 = new Vehicle();
		v3.setColor("White");v3.setRegNumber("KA-01-HH-1237");
		pl.park(v3);
		Vehicle v4 = new Vehicle();
		v4.setColor("White");v4.setRegNumber("KA-01-HH-1238");
		pl.park(v4);
		Vehicle v5 = new Vehicle();
		v5.setColor("White");v5.setRegNumber("KA-01-HH-1239");
		pl.park(v5);
		Vehicle v6 = new Vehicle();
		v6.setColor("White");v6.setRegNumber("KA-01-HH-1230");
		assertEquals("Sorry, parking lot is full", pl.park(v6));
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
		ParkingLot pl = new ParkingLot(6);
		Vehicle v = new Vehicle();
		v.setColor("White");v.setRegNumber("KA-01-HH-1234");
		pl.park(v);
		assertEquals("KA-01-HH-1234", pl.getRegistrationNumberForCar("White"));
		Vehicle v2 = new Vehicle();
		v2.setColor("White");v2.setRegNumber("KA-01-HH-1235");
		pl.park(v2);
		assertEquals("KA-01-HH-1234, KA-01-HH-1235", pl.getRegistrationNumberForCar("White"));
		Vehicle v3 = new Vehicle();
		v3.setColor("Black");v3.setRegNumber("KA-01-HH-1236");
		pl.park(v3);
		assertEquals("KA-01-HH-1236", pl.getRegistrationNumberForCar("Black"));
	}
	
	@Test
	public void testStatus() {
		ParkingLot pl = new ParkingLot(6);
		Vehicle v = new Vehicle();
		v.setColor("White");v.setRegNumber("KA-01-HH-1234");
		pl.park(v);
		Vehicle v1 = new Vehicle();
		v1.setColor("Black");v1.setRegNumber("KA-01-HH-1235");
		pl.park(v1);
		Vehicle v2 = new Vehicle();
		v2.setColor("Green");v2.setRegNumber("KA-01-HH-1236");
		pl.park(v2);
		Vehicle v3 = new Vehicle();
		v3.setColor("White");v3.setRegNumber("KA-01-HH-1237");
		pl.park(v3);
		Vehicle v4 = new Vehicle();
		v4.setColor("Blue");v4.setRegNumber("KA-01-HH-1238");
		pl.park(v4);
		Vehicle v5 = new Vehicle();
		v5.setColor("White");v5.setRegNumber("KA-01-HH-1239");
		pl.park(v5);
		
		assertEquals("Slot No.    Registration No    Colour\n" + 
				"1           KA-01-HH-1234      White\n" + 
				"2           KA-01-HH-1235      Black\n" + 
				"3           KA-01-HH-1236      Green\n" + 
				"4           KA-01-HH-1237      White\n" + 
				"5           KA-01-HH-1238      Blue\n" + 
				"6           KA-01-HH-1239      White" , pl.status());
		
	}
}
