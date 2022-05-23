package com.mycompany.remote.serialization;

import java.time.LocalDate;

/** The Class BuyTicketDTO.*/
public class BuyTicketDTO { 
	
	/** The token. */
	private long token;
	
	/** The event date. */
	private String eventDate;
	
	/** The event name. */
	private String eventName;


	/**
	 * Gets the event date.
	 *
	 * @return the event date
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * Sets the event date.
	 *
	 * @param eventDate the new event date
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	/**
	 * Sets the event date.
	 *
	 * @param date the new event date
	 */
	public void setEventDate(LocalDate date) {
		this.eventDate = date.toString();
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(long token) {
		this.token = token;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public long getToken() {
		return this.token;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BuyTicketDTO [token=" + token + ", eventDate=" + eventDate + ", eventName=" + eventName + "]";
	}
	

}
