package parkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
					Set<Slot> slotSet = new TreeSet<>();
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
		if (index >= 1 && index <= size) {
			Slot s = this.slots.get(index-1);
			if (s.isOccupied() == true) {
				regNumberSlotMap.remove(s.getVehicle().getRegNumber());
				Set<Slot> slotSet = colourSlotMap.get(s.getVehicle().getColor());
				boolean b = slotSet.remove(s);
				colourSlotMap.put(s.getVehicle().getColor(), slotSet);
				s.setOccupied(false);
				this.slots.set(index-1, s);
			}
			return "Slot number " + index + " is free";
		}
		return "Please enter slot number within Parking Lot size";
	}
	
	public String getSlotForCar(String property, String value) {
		String slotNumbers="";
		switch (property) {
		case "colour":
			if (colourSlotMap.get(value) == null)
				break;
			for(Slot slot: colourSlotMap.get(value)) 
				slotNumbers = slotNumbers + slot.getSlotNumber() + ", ";
			
			if (slotNumbers.length() != 0)
				slotNumbers = slotNumbers.substring(0, slotNumbers.length()-2);
			break;
		case "registration number":
			Slot slot = regNumberSlotMap.get(value);
			if(slot != null)
				slotNumbers = slot.getSlotNumber() + "";
			break;
		default:
			return property + " is not implemented yet";
		}
		
		if (slotNumbers.length() == 0)
			return "Not found";
		
		return slotNumbers;
	}
	
	public String getRegistrationNumberForCar(String colour) {
		String registrationNumbers = "";
		if (colourSlotMap.get(colour) == null)
			return "";
		
		for(Slot slot: colourSlotMap.get(colour))
			registrationNumbers = registrationNumbers + slot.getVehicle().getRegNumber() + ", ";
		
		if (registrationNumbers.length() == 0)
			return "Not found";
		
		return registrationNumbers.substring(0, registrationNumbers.length()-2);
	}
	
	public String status() {
		String stat = "Slot No.    Registration No    Colour\n";
		
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
