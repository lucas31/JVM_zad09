package tests;

import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Product;

/**
 * This is just a proposal for test data generator. You can build your own solution!
 */
public class DataProducer {
	public static List<Customer> getTestData(int count) {
		List<Customer> result = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			Customer c = new Customer(i, "Customer: " + i);
			c.setTaxId("000" + i + "-212-3" + i + "2-5");
			for (int j = count - i; j > 3; j--) {
				c.addProduct(new Product(j, "Product: " + j, (double) j * 0.1));
			}
			result.add(c);
		}
		return result;

	}
}
