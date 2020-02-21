package parkingLot;

import java.util.ArrayList;

public class ParkingLot {
	ArrayList<Slot> slots;
	int size;
	
	public ParkingLot (int size) {
		this.size = size;
		slots = new ArrayList<> ();
		
		for (int slotNumber=1; slotNumber<=size; ++slotNumber) {
			Slot slot = new Slot();
			slot.setSlotNumber(slotNumber);
			slot.setOccupied(false);
			slots.add(slot);
		}	
	}
	
	public String park (Vehicle vehicle) {
		for (Slot slot: this.slots) {
			if (slot.isOccupied() == false) {
				slot.setOccupied(true);
				slot.setVehicle(vehicle);
				
				return "Allocated slot number: " + slot.getSlotNumber();
			}
		}
		return "Sorry, parking lot is full";
	}
	
	public String leave(int index) {
		this.slots.get(index).setOccupied(false);
		return "Slot number " + index + " is free";
	}
}
