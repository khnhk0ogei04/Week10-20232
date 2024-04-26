package Manager;

import java.util.ArrayList;
import java.util.Hashtable;

import Entity.Customer;
import Entity.Fruit;
import Entity.Order;

public class Menu {
	public static void main(String[] args) {
		ArrayList<Fruit> lf = new ArrayList<>();
		Hashtable<String,ArrayList<Order>> ht = new Hashtable<>();
		ArrayList<Customer> customerList = new ArrayList<>();
		while (true) {
			int choice = Manager.menu();
			switch(choice) {
				case 1:
					Manager.createFruit(lf);
					break;
				case 2:
					Manager.viewOrder(ht);
					break;
				case 3:
					Manager.shopping(lf, ht, customerList);
					break;
				case 4:
					Manager.deleteHistoryCustomerList(customerList);
					Manager.viewCustomerHistory(customerList);
					break;
				case 5:
					return;
			}
		}
	}
}
