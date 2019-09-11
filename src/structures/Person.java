package structures;

public class Person implements People {
	private String name;
	public Person(String name) {
		this.name = name;
	}
	
	public String toString() { // overrides toString() to display a name whenever object is printed
		return this.name;
	}

}