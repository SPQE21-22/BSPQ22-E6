package com.mycompany.app.basicClass;

public class Ticket {
	Event event;
	String personName;
	int id;
	
	public Ticket(Event event, String personName, int id) {
		super();
		this.event = event;
		this.personName = personName;
		this.id = id;
	}
	
	public Ticket() {
		super();
		this.event = new Event();
		this.personName = "";
		this.id = 0;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ticket [event=" + event + ", personName=" + personName + ", id=" + id + "]";
	}
	

}
