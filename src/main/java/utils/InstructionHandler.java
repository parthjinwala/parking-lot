package utils;
import parkingLot.*;

public class InstructionHandler {
	ParkingLot parkingLot;
	
	public InstructionHandler() {
		parkingLot = null;
	}
	
	public void executeInstruction (String[] instruction) {
		String printMsg = "No parking lot is initialized";
		
		switch (instruction[0]) {
			case "create_parking_lot":
				int slots = Integer.parseInt(instruction[1]);
				parkingLot = new ParkingLot(slots);
				printMsg = "Created a parking lot with " + slots + " slots";
				break;
			case "park":
				if (parkingLot != null) {
					Vehicle vehicle = new Vehicle();
					vehicle.setRegNumber(instruction[1]);
					vehicle.setColor(instruction[2]);
					
					printMsg = parkingLot.park(vehicle);
				}
				break;
			case "leave":
				if (parkingLot != null) {
					printMsg = parkingLot.leave(Integer.parseInt(instruction[1]));
				}
				break;
			case "slot_numbers_for_cars_with_colour":
				if (parkingLot != null) {
					printMsg = parkingLot.getSlotForCar("colour", instruction[1]);
				}
				break;
			case "slot_number_for_registration_number":
				if (parkingLot != null) {
					printMsg = parkingLot.getSlotForCar("registration number", instruction[1]);
				}
				break;
			case "registration_numbers_for_cars_with_colour":
				if (parkingLot != null) {
					printMsg = parkingLot.getRegistrationNumberForCar(instruction[1]);
				}
				break;
			case "status":
				if (parkingLot != null) {
					printMsg = parkingLot.status();
				}
				break;
			default:
				printMsg = "invalid instruction, try again";
			
		}
		
		System.out.println(printMsg);
	}
}
