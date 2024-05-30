package marketplace.model;

import java.util.List;

public class UserData {
	
	String email;
	String password;
	List<Product> product;

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
	
	public List<Product> getProduct() {
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
