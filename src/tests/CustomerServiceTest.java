package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;
import entities.Product;

public class CustomerServiceTest {

	@Test
	public void testFindByName() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		List<Customer> res = cs.findByName("Customer: 1");
		
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
	}
	
	@Test
	public void testFindByField() {
		//String fieldName, Object value
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		List<Customer> res = cs.findByField("taxId", "0003-212-332-5");
		
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
	}
	
	@Test
	public void testCustomersWhoBoughtMoreThan() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		List<Customer> res = cs.customersWhoBoughtMoreThan(5);
		
		assertNotNull("Result can't be null", res);
		assertEquals(2, res.size());		
	}
	
	@Test
	public void testCustomersWhoSpentMoreThan() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		List<Customer> res = cs.customersWhoSpentMoreThan(1.0);
		
		assertNotNull("Result can't be null", res);
		assertEquals(5, res.size());
	}
	
	@Test
	public void testCustomersWithNoOrders() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		List<Customer> res = cs.customersWithNoOrders();
		
		assertNotNull("Result can't be null", res);
		assertEquals(3, res.size());
	}
	
	@Test
	public void testAddProductToAllCustomers() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		Product p = new Product(40, "Latarka na baterie słoneczne", 0.5);
		
		cs.addProductToAllCustomers(p);
		
		int customerCount = cs.countCustomersWhoBought(p);
		
		assertNotNull("Result can't be null", customerCount);
		assertEquals(10, customerCount);
	}
	
	@Test
	public void testAvgOrders() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		double avg = cs.avgOrders(false);

		assertEquals(2.4, avg, 0.5);
	}
	
	@Test
	public void testAvgOrdersIncludeEmpty() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		double avg = cs.avgOrders(true);
		
		assertEquals(1.68, avg, 0.5);
	}
	
	@Test
	public void testWasProductBought() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		
		Product p = new Product(42, "14 McRoyali", 25.0);
		cs.addProductToAllCustomers(p);
		
		assertTrue(cs.wasProductBought(p));
	}
	
	@Test
	public void testMostPopularProduct() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));

		List<Product> list = cs.mostPopularProduct();

		assertNotNull("Result can't be null", list);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testCountBuys() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));

		int boughtCount = cs.countCustomersWhoBought(new Product(8, "Product: 8", 0.8));
		
		assertEquals(3, boughtCount);
	}
	
	@Test
	public void testCountCustomersWhoBought() {
		CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));

		int boughtCount = cs.countCustomersWhoBought(new Product(5, "Product: 5", 0.5));
		
		assertEquals(6, boughtCount);
	}
}
