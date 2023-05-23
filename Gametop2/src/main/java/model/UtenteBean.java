package model;

import java.util.List;


public class UtenteBean {

	private int id;
	private String email, password, firstname, lastname;
	private List<OrdineBean> orders;
	private boolean admin;
	
	

	public UtenteBean() {
		super();
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<OrdineBean> getOrders() {
		return orders;
	}
	

	public void setOrders(List<OrdineBean> orders) {
		this.orders = orders;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}

