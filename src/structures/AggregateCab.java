package structures;

// Cab implementation that only stores the # of seats and # of passengers
// Does not store People information
public class AggregateCab implements Cab {
	private int passengerSeats;
	private int passengers;
	
	public AggregateCab(int seats){
		this.passengerSeats = seats;
		this.passengers = 0;
	}
	
	// Even though this constructor ignores People data
	// the Interface specifies a People parameter
	public boolean addPassenger(People p) {
		if (!isFull()) {
			passengers++;
			return true;
		}
		else 
			return false;
	}
	
	public boolean isFull() {
		return this.passengerSeats == this.passengers;
	}
	
}