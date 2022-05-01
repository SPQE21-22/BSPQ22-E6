package com.mycompany.server.data.domain;

import java.time.LocalDate;

public class Event {
    private String name;
	private LocalDate date;
	private String place;
	private Organizer organizer;



	public Event(String name, LocalDate date, 
			String place, Organizer organizer) {
		super();
		this.name = name;
		this.date = date;
		this.place = place;
		this.organizer = organizer;
	}
	public Event() {
		super();
		this.name = "";
		this.date = LocalDate.now();
		this.place = "";
		this.organizer = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	
	@Override
	public String toString() {
		return "Name: " + name + ", Place: " + place + ", Date: " + date + ", Organizer: " + organizer;
	}
}
