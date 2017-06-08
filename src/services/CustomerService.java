package services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Product;

public class CustomerService implements CustomerServiceInterface {

	private List<Customer> customers;

	public CustomerService(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public List<Customer> findByName(String name) {
		return this.customers.stream()
				.filter(c -> c.getName().equals(name))
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> findByField(String fieldName, Object value) {
		return this.customers.stream()
				.filter(n -> {
					try {
						if (n.getClass().getDeclaredField(fieldName).isAccessible()) return n.getClass().getDeclaredField(fieldName).get(n).equals(value);
						else return n.getClass().getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(n, null).equals(value);
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
						System.out.println(e.getMessage());
						return false;
					}
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoBoughtMoreThan(int number) {
		return this.customers.stream()
				.filter(x -> x.getBoughtProducts().size() > number)
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoSpentMoreThan(double price) {
		return this.customers.stream()
				.filter(x -> x.getBoughtProducts().stream()
					.mapToDouble(y -> y.getPrice()).sum() > price)
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWithNoOrders() {
		return this.customers.stream()
				.filter(c -> c.getBoughtProducts().size() == 0)
				.collect(Collectors.toList());
	}

	@Override
	public void addProductToAllCustomers(Product p) {
		this.customers.stream().forEach(c -> c.addProduct(p));
	}
	
	@Override
	public double avgOrders(boolean includeEmpty) {
		return this.customers.stream()
				.filter(c -> (includeEmpty ? c.getBoughtProducts().size() >= 0 : c.getBoughtProducts().size() > 0))
				.mapToDouble(p -> p.getBoughtProducts().stream()
						.mapToDouble(bp -> bp.getPrice()).sum()).average().getAsDouble();
	}

	@Override
	public boolean wasProductBought(Product p) {
		return this.customers.stream()
				.filter(c -> c.getBoughtProducts().stream()
					.filter(x -> x.equals(p)).count() > 0)
				.count() > 0;
	}

	@Override
	public List<Product> mostPopularProduct() {
		List<List<Product>> products = this.customers.stream()
			.filter(c -> c.getBoughtProducts().size() > 0)
			.map(Customer::getBoughtProducts)
			.collect(Collectors.toList());

		List<Product> combined = new ArrayList();
		for(List<Product> list : products) combined.addAll(list);		
		
		Map<Integer, Long> map = combined.stream()
		.collect(Collectors.groupingBy(Product::getId, Collectors.counting()));
		
		Long max = Collections.max(map.values());
		
		List<Product> result = new ArrayList();
		
		for(Integer key : map.keySet()) {
			if (map.get(key) == max) result.add(combined.stream().filter(p -> p.getId() == key).collect(Collectors.toList()).get(0));
		}
		
		return result;
	}
	
	@Override
	public int countBuys(Product p) {
		return this.customers.stream()
				.filter(a -> a.getBoughtProducts().contains(p))
				.mapToInt(c -> (int)c.getBoughtProducts().stream().filter(d -> d == p).count()).sum();
	}

	@Override
	public int countCustomersWhoBought(Product p) {
		return (int)this.customers.stream()
				.filter(c -> c.getBoughtProducts().stream()
					.filter(x -> x.equals(p)).count() > 0)
				.count();
	}

}
