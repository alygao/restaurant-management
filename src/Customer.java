
public class Customer {
	
	private String name;
	private int numPeople;

	public Customer(String name, int numPeople) {
		this.name = name;
		this.numPeople = numPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
}
