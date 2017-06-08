package entities;
import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int id;
	private String name;
	private String email;
	private String phoneNo;
	private String taxId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	private List<Product> boughtProducts;

	public Customer() {
		boughtProducts = new ArrayList<>();
	}

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
		this.boughtProducts = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getBoughtProducts() {
		return boughtProducts;
	}

	public void addProduct(Product p) {
		boughtProducts.add(p);
	}

}
