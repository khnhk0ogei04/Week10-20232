package Manager;
import java.util.ArrayList;
import java.util.Hashtable;

import Entity.Customer;
import Entity.Fruit;
import Entity.Order;

public class Manager {
	static int menu() {
		System.out.println("----------FRUIT SHOP----------");
		System.out.println("1. Create Fruit");
		System.out.println("2. View orders");
		System.out.println("3. Shopping");
		System.out.println("4. ViewHistoryCustomer");
		System.out.println("5. Break");
		int choice = Utils.checkInputIntLimit(1,5);
		return choice;
	}
	
	static void viewOrder(Hashtable<String,ArrayList<Order>>hashTable) {
		for (String name: hashTable.keySet()) {
			System.out.println("Customer " + name);
			ArrayList<Order> lo = hashTable.get(name);
			displayListOrder(lo);
		}
	}
	static void displayFruit(ArrayList<Fruit> lf) {
		int countItem = 0;
		System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruitname", "Origin", "Price");
		for (Fruit fruit : lf) {
			if (fruit.getQuantity() != 0) {
				System.out.printf("%-10s%-20s%-20s%-15s\n",++countItem, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
			}
		}
	}
	
	static void createFruit(ArrayList<Fruit> lf) {	
		while(true) {
			System.out.println("Enter fruit Id");
			String fruitId = Utils.checkInputString();
			
			if (!Utils.checkIdExist(lf, fruitId)) {
				System.out.println("Id exist");
				return;
			}
			System.out.println("Enter fruit name: ");
			String fruitName = Utils.checkInputString();
			System.out.println("Enter price: ");
			double price = Utils.checkInputDouble();
			System.out.println("Enter quantity: ");
			int quantity = Utils.checkInputInteger();
			System.out.println("Enter origin: ");
			String origin = Utils.checkInputString();
			Fruit fruit = new Fruit(fruitId,fruitName,price,quantity,origin);
			lf.add(fruit);
			if (!Utils.checkInputYN()) {
                return;
            }
		}
	}
	private static void displayListOrder(ArrayList<Order> lo) {
		double total = 0;
		System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
		for (Order order : lo) {
			System.out.printf("%15s%15d%15.0f$%15.0f$\n",order.getFruitName(),
					order.getQuantity(), order.getPrice(), order.getQuantity()*order.getPrice());
			total += order.getPrice() * order.getQuantity();
		}
		System.out.println("Total: " + total);
	}
	
	static void updateOrder(ArrayList<Order> lo, String id, int quantity) {
		for (Order order : lo) {
			if (order.getFruitId().equalsIgnoreCase(id)) {
				order.setQuantity(order.getQuantity() + quantity);
				break;
			}
		}
		return;
	}
	static Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
		int countItem = 1;
		for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
	}
	static void shopping(ArrayList<Fruit> lf, Hashtable<String, ArrayList<Order>> ht, ArrayList<Customer> customerList) {
		if (lf.isEmpty()) {
			System.out.println("Sorry, sold out");
			return;
		}
		ArrayList<Order> lo = new ArrayList<>();
		while (true) {
			displayFruit(lf);
			System.out.print("Enter item: ");
			int item = Utils.checkInputIntLimit(1, lf.size());
			Fruit fruit = getFruitByItem(lf,item);
			System.out.print("Enter quantity: ");
			int quantity = Utils.checkInputIntLimit(1, fruit.getQuantity());
			fruit.setQuantity(fruit.getQuantity() - quantity);
			if (!Utils.checkItemExist(lo, fruit.getFruitId())) {
				updateOrder(lo,fruit.getFruitId(),quantity);
			} 
			else {
				Order order = new Order(fruit.getFruitId(), fruit.getFruitName(), quantity, fruit.getPrice());
				lo.add(order);
			}
			if (!Utils.checkInputYN()) {
				break;
			}
		}
		displayListOrder(lo);
		System.out.println("Enter customername: ");
		String name = Utils.checkInputString();
		ht.put(name, lo);
		System.out.println("Add successful");
	}
	static void viewCustomerHistory(ArrayList<Customer> customerList) {
		try {
		for (Customer customer : customerList) {
			System.out.print(customer.getName() + ": ");
			ArrayList<Order> lo = customer.getLo();
			for (Order order : lo) {
				System.out.print(order.getFruitName() + " " + order.getQuantity());
			}
		}
		} catch (Exception e) {
			System.out.println("No customer found");
		}
	}
	static void deleteHistoryCustomerList(ArrayList<Customer> customerList) {
		if (customerList.size() > 5) {
			customerList.subList(5, customerList.size()).clear();
		}
	}
}
