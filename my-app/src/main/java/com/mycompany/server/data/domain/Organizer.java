package com.mycompany.server.data.domain;

public class Organizer extends User {
	
	private String address;
	private String webpage;
	
	public Organizer() {
		super();
		setAddress("");
		setWebpage("");
	}

	public Organizer(String name, String password, String email, String phone, String address, String webpage) {
		super(name, password, email, phone);
		this.address = address;
		this.webpage = webpage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	
	



}
