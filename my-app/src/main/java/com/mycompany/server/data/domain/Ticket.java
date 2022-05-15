package com.mycompany.server.data.domain;

import java.util.Objects;

public class Ticket {
	private Event event;
	private Consumer owner;
	private boolean inResell = false;

	public Ticket(Event e, Consumer c) {
		this.event = e;
		this.owner = c;
	}
	public Ticket() {
		this.event = null;
		this.owner = null;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Consumer getOwner() {
		return owner;
	}
	public void setOwner(Consumer owner) {
		this.owner = owner;
	}
	
	public boolean isInResell() {
		return inResell;
	}
	public void setInResell(boolean inResell) {
		this.inResell = inResell;
	}
	@Override
	public String toString() {
		return "Ticket [event=" + event + ", owner=" + owner + ", inResell=" + inResell + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(event, inResell, owner);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(event, other.event) && inResell == other.inResell && Objects.equals(owner, other.owner);
	}
	
	
}

