package com.mycompany.server.data.domain;

public class Ticket {
	private Event Event;
	private Organizer organizer;
	
	public Ticket(Event e, Consumer c) {
		// TODO Auto-generated constructor stub
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	
	@Override
	public String toString() {
		return "Event: " + event + ", Organizer: " + organizer;
	}
	
	
}
