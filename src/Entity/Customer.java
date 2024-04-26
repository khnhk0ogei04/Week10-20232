package Entity;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Order> lo = new ArrayList<>();
	public Customer() {
		
	}
	public Customer(String name, ArrayList<Order> lo) {
		this.name = name;
		this.lo = lo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Order> getLo() {
		return lo;
	}
	public void setLo(ArrayList<Order> lo) {
		this.lo = lo;
	}
}
