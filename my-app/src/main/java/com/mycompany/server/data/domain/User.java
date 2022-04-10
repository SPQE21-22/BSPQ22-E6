package com.mycompany.server.data.domain;

import java.util.List;

public abstract class User{
	
	protected String password;
	protected String email;
	protected String name;
	protected String phone;
	protected List<Ticket> boughtTickets;


	protected User(String name, String password, String email,String phone) {
		this.password = password;
		this.email = email;
		this.name = name;
        this.phone = phone;
		this.boughtTickets = null;
	}
	protected User() {
		this.password = "";
		this.email = "";
		this.name = "";
        this.phone = "";
		this.boughtTickets = null;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Ticket> getBoughtTickets(){
		return boughtTickets;
	}
	public void addBoughtTicket(Ticket ticket){
			this.boughtTickets.add(ticket);
	}
	@Override
	public String toString() {
		return "User [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
	}
	

	
}
