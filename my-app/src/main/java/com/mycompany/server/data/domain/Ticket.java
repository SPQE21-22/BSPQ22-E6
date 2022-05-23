package com.mycompany.server.data.domain;

import java.util.Objects;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/** The Class Ticket.*/
@PersistenceCapable(detachable = "true")
public class Ticket {
	
	/** The event. */
	@Persistent(defaultFetchGroup="true")
	private Event event;
	
	/** The owner. */
	@Persistent(defaultFetchGroup="true")
	private Consumer owner;
	
	/** The in resell. */
	private boolean inResell = false;

	/**
	 * Instantiates a new ticket.
	 *
	 * @param e the e
	 * @param c the c
	 */
	public Ticket(Event e, Consumer c) {
		this.event = e;
		this.owner = c;
	}
	
	/** Instantiates a new ticket. */
	public Ticket() {
		this.event = null;
		this.owner = null;
	}
	
	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public Consumer getOwner() {
		return owner;
	}
	
	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(Consumer owner) {
		this.owner = owner;
	}
	
	/**
	 * Checks if is in resell.
	 *
	 * @return true, if is in resell
	 */
	public boolean isInResell() {
		return inResell;
	}
	
	/**
	 * Sets the in resell.
	 *
	 * @param inResell the new in resell
	 */
	public void setInResell(boolean inResell) {
		this.inResell = inResell;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Ticket [event=" + event + ", owner=" + owner + ", inResell=" + inResell + "]";
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(event, inResell, owner);
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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

