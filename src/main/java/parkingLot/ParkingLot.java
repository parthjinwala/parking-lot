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
	int occupied;
	
	public ParkingLot (int size) {
		this.size = size;
		occupied = 0;
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
				occupied++;
				
				if(colourSlotMap.containsKey(vehicle.getColor())) {
					Set<Slot> slotSet = colourSlotMap.get(vehicle.getColor());
					slotSet.add(slot);
					colourSlotMap.put(vehicle.getColor(), slotSet);
				}
				else {
					Set<Slot> slotSet = new HashSet<>();
					slotSet.add(slot);
					colourSlotMap.put(vehicle.getColor(), slotSet);
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
				slotNumbers = slotNumbers + slot.getSlotNumber() + ",";
			break;
		case "registration number":
			slotNumbers = regNumberSlotMap.get(value).getVehicle().getRegNumber();
			break;
		default:
			return property + " is not implemented yet";
		}
		
		if (slotNumbers.length() == 0)
			return "Not found";
		
		return slotNumbers.substring(0, slotNumbers.length()-1);
	}
	
	public String getRegistrationNumberForCar(String colour) {
		String registrationNumbers = "";
		
		for(Slot slot: colourSlotMap.get(colour))
			registrationNumbers = registrationNumbers + slot.getVehicle().getRegNumber();
		
		if (registrationNumbers.length() == 0)
			return "Not found";
		
		return registrationNumbers.substring(0, registrationNumbers.length()-1);
	}
	
	public String status() {
		String stat = "";
		for(Slot slot: slots) {
			if(slot.isOccupied() == false)
				continue;
			stat = stat + slot + "\n";
		}
		if (stat.compareTo("") != 0)
			stat = stat.substring(0, stat.length() - 1);
		return stat;
	}
	
}
