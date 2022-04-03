package com.mycompany.server.data.domain;


public class Event {
    private String name;
	private String date;
	private String place;



	public Event(String name, String date, 
			String place) {
		super();
		this.name = name;
		this.date = date;
		this.place = place;
	}
	public Event() {
		super();
		this.name = "";
		this.date = "";
		this.place = "";
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

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}

	
	@Override
	public String toString() {
		return "Name: " + name + ", Place: " + place + ", Date: " + date;
	}
}
