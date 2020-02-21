package parkingLot;

public class Slot {
	int slotNumber;
	boolean isOccupied;
	Vehicle vehicle;
	
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
		if (this.isOccupied == false) {
			//remove vehicle as well
			Vehicle dummyVehicle = new Vehicle ();
			this.vehicle = dummyVehicle;
		}
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Override
	public String toString() {
		return slotNumber + "\t" + vehicle.regNumber + "\t" + vehicle.color;
	}
	
	
	
}
