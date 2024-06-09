package marketplace.model;

import java.util.List;

public class UserData {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber; 
	private List<Product> product;
	private Address address;
	private String shippingDelivery;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Product> getProduct() {
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getShippingDelivery() {
		return shippingDelivery;
	}
	
	public void setShippingDelivery(String shippingDelivery) {
		this.shippingDelivery = shippingDelivery;
	}

}
