package com.mycompany.app.basicClass;

import java.sql.Date;

public class Event {
    private String name;
	private String date;
	private float price;
	private String company;



	public Event(String name, String date, float price,
			String company) {
		super();
		this.name = name;
		this.date = date;
		this.price = price;
		this.company = company;
	}
	public Event() {
		super();
		this.name = "";
		this.date = "";
		this.price = 0;
		this.company = "";
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	
	@Override
	public String toString() {
		return "Name: " + name + ", Date: " + date;
	}
}
