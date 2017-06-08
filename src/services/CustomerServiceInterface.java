package services;
import java.util.List;

import entities.Customer;
import entities.Product;

public interface CustomerServiceInterface {

	/**
	 * Finds all customers with name equal to given name
	 * @param name
	 * @return
	 */
	List<Customer> findByName(String name);

	/**
	 * Finds all customers such that the value of filed with name given by fieldName is equal to given value.
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	List<Customer> findByField(String fieldName, Object value);

	/**
	 * Finds all customers who bought more than a given number of products.
	 * @param number
	 * @return
	 */
	List<Customer> customersWhoBoughtMoreThan(int number);
	
	/**
	 * Customers who spent more than a given amount of money on their products.
	 * @param price
	 * @return
	 */
	List<Customer> customersWhoSpentMoreThan(double price);
	
	/**
	 * Customers with empty order list.
	 * 
	 * @return
	 */
	List<Customer> customersWithNoOrders();
	
	/**
	 * Add given product to all order lists (used for promotions).
	 * @param p
	 */
	void addProductToAllCustomers(Product p);
	
	/**
	 * Calculates the average of total order costs per client, i.e. for each client with add up all prices of orders
	 * and average this over all clients (add and divide over the number of clients). The includeEmpty parameter controls
	 * if clients with empty order lists should be included in this or not.
	 * 
	 * @param includeEmpty
	 * @return
	 */
	double avgOrders(boolean includeEmpty);
	
	/** 
	 * Did any client bought given product
	 * @param p
	 * @return
	 */
	boolean wasProductBought(Product p);
	
	/**
	 * Gives the product/products that was bought the maximum number of times.
	 * This returns a list, since few products could have the same number of buys. 
	 * @return
	 */
	List<Product> mostPopularProduct();
	
	/**
	 * How many times given product was bought.
	 * @param p
	 * @return
	 */
	int countBuys(Product p);
	
	/**
	 * How many different customers bought given product.
	 * @param p
	 * @return
	 */
	int countCustomersWhoBought(Product p);
	
	
}
