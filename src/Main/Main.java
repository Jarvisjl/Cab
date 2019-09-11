package Main;

import com.github.javafaker.Faker;
import structures.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		test1();
		test2();
		testQueue();
	}
	
	//tests storePeople() with an output of the number of people left without cabs (int)
	//There is a sufficient amount of cabs to store the customers
	public static void test1() {
		Faker faker = new Faker();// for simulation data
		ArrayList<Cab> cabs = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			cabs.add(new AggregateCab(3));
		for (int i = 0; i < 4; i++) 
			cabs.add(new IndividualsCab(5));// exactly 50 empty seats to begin(30+20)
		ArrayList<People> customers = new ArrayList<>();
		for (int i = 0; i < 50; i++)
			customers.add(new Person(faker.name().fullName()));
		// storePeople returns an integer, print that integer
		System.out.println("(test1) Number of customers left without a cab ride: " +storePeople(cabs, customers));
	}
	
	// Should output a failure that will print the names of all people left without a cab
	public static void test2() {
		Faker faker = new Faker(); // for simulation data
		ArrayList<Cab> cabs = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			cabs.add(new AggregateCab(3));
		for (int i = 0; i < 3; i++) 
			cabs.add(new IndividualsCab(5));// Cabs do not add up to 50 open seats
		ArrayList<People> customers = new ArrayList<>();
		for (int i = 0; i < 50; i++)
			customers.add(new Person(faker.name().fullName()));
		int strandedIndex = customers.size() - storePeople(cabs, customers);
		System.out.println("(test2) Names of customers left without a cab ride: ");
		for (int i = strandedIndex; i < customers.size(); i++) 
			System.out.println(customers.get(i));//print name of stranded customers
	}
	// Tests a queue implementation of quickStorePeople()
	public static void testQueue() {
		//Initialize enough cars to cab 50 people
		Queue<Cab> cabs = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			cabs.add(new AggregateCab(3));
		for (int i = 0; i < 4; i++) 
			cabs.add(new IndividualsCab(5));
		
		// Initialize queue of 50 people using faker to simulate names
		Faker faker = new Faker();
		Queue<People> customers = new LinkedList<>();
		for (int i = 0; i < 50; i++)
			customers.add(new Person(faker.name().fullName()));
		
		// Call quickStorePeople, print results
		System.out.println("(testqueue) Number of customers left without a cab ride: " + fastStorePeople(cabs, customers));
	}
	// Stores people into cabs efficiently 
	public static int storePeople(ArrayList<Cab> cabs, ArrayList<People> people) {
		int j = 0;// iterator for people list
		for (int i = 0; i < cabs.size(); i++) {
			// if cab is full move onto next
			while(!cabs.get(i).isFull()) {
				cabs.get(i).addPassenger(people.get(j));
				j++;
				if (j >= people.size())
					return 0;// there are no more people left to taxi: success
			}
		}
		return people.size() - j; // there are still people to taxi: failure
	}
	
	// storePeople using queue methods
	public static int fastStorePeople(Queue<Cab> cabs, Queue<People> people) {
		// while loop with two exit cases: no people left to taxi, or no cabs are left
		while(!people.isEmpty() && !cabs.isEmpty()) {
			if (!cabs.peek().isFull())
				cabs.peek().addPassenger(people.poll());
			else 
				cabs.remove();
		}
		return people.size(); // Remaining people left to taxi, success = 0
	}
}
