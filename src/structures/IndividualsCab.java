package structures;

public class IndividualsCab implements Cab {
	private Person[] passengers;// stores passengers as people objects with name variables
	private int passengerCount;
	
	public IndividualsCab(int seats) {
		passengers = new Person[seats];
	}
	
	public boolean addPassenger(People person) {
		// Only adds passenger if there is available capacity
		if (!isFull()) {
			passengers[passengerCount] = (Person) person;
			passengerCount++;
			return true;
		}
		else {
			System.out.println("Cab full");
			return false;
		}
	}

	public boolean isFull() {
		return passengers.length == passengerCount;
	}

}
