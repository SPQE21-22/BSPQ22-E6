package com.mycompany.server.data.domain;

public class Ticket {
	private Event event;
	private User user;

	public Ticket(Event e, User u) {
		this.event = e;
		this.user = u;
	}
	public Ticket() {
		this.event = null;
		this.user = null;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event e){
		this.event = e;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User u) {
		this.user = u;
	}
	@Override
	public String toString(){
		return "Ticket [event=" + event + ", user=" + user + "]";
	}
}

