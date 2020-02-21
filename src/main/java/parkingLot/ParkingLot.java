package parkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ParkingLot {
	ArrayList<Slot> slots;
	Map<String,Set<Slot>> colourSlotMap;
	Map<String,Slot> regNumberSlotMap;
	
	int size;
	
	public ParkingLot (int size) {
		this.size = size;
		slots = new ArrayList<> ();
		colourSlotMap = new HashMap<>();
		regNumberSlotMap = new HashMap<>();
		
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
				
				if(colourSlotMap.containsKey(vehicle.getColor()))
					colourSlotMap.get(vehicle.getColor()).add(slot);
				else {
					Set<Slot> setSlot = new HashSet<>();
					setSlot.add(slot);
					colourSlotMap.put(vehicle.getColor(), setSlot);
				}				
				regNumberSlotMap.put(vehicle.getRegNumber(), slot);
				
				return "Allocated slot number: " + slot.getSlotNumber();
			}
		}
		return "Sorry, parking lot is full";
	}
	
	public String leave(int index) {
		this.slots.get(index).setOccupied(false);
		return "Slot number " + index + " is free";
	}
	
	public String getSlotForCar(String property, String value) {
		String slotNumbers="";
		switch (property) {
		case "colour":
			for(Slot slot: colourSlotMap.get(value)) 
				slotNumbers = slot.getSlotNumber() + ",";
			break;
		case "registration number":
			slotNumbers = regNumberSlotMap.get(value).getVehicle().getRegNumber();
			break;
		default:
			return property + " is not implemented yet";
		}
		
		if (slotNumbers.length() == 0)
			return "Not found";
		return slotNumbers;
	}
	
	
}
