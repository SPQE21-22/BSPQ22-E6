package com.mycompany.remote.serialization;

import java.time.LocalDate;

/** The Class ResellTicketDTO.*/
public class ResellTicketDTO {
	
	/** The token. */
	private long token;
	
	/** The ticket event name. */
	private String ticketEventName;
	
	/** The ticket user email. */
	private String ticketUserEmail;
	
	/** The ticket event date. */
	private String ticketEventDate;
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public long getToken() {
		return token;
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
	 * Gets the ticket event name.
	 *
	 * @return the ticket event name
	 */
	public String getTicketEventName() {
		return ticketEventName;
	}
	
	/**
	 * Sets the ticket event name.
	 *
	 * @param ticketEventName the new ticket event name
	 */
	public void setTicketEventName(String ticketEventName) {
		this.ticketEventName = ticketEventName;
	}
	
	/**
	 * Gets the ticket user email.
	 *
	 * @return the ticket user email
	 */
	public String getTicketUserEmail() {
		return ticketUserEmail;
	}
	
	/**
	 * Sets the ticket user email.
	 *
	 * @param ticketUserEmail the new ticket user email
	 */
	public void setTicketUserEmail(String ticketUserEmail) {
		this.ticketUserEmail = ticketUserEmail;
	}
	
	/**
	 * Gets the ticket event date.
	 *
	 * @return the ticket event date
	 */
	public String getTicketEventDate() {
		return ticketEventDate;
	}
	
	/**
	 * Sets the ticket event date.
	 *
	 * @param ticketEventDate the new ticket event date
	 */
	public void setTicketEventDate(String ticketEventDate) {
		this.ticketEventDate = ticketEventDate;
	}
	
	/**
	 * Sets the ticket event date.
	 *
	 * @param ticketEventDate the new ticket event date
	 */
	public void setTicketEventDate(LocalDate ticketEventDate) {
		this.ticketEventDate = ticketEventDate.toString();
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ResellTicketDTO [token=" + token + ", ticketEventName=" + ticketEventName + ", ticketUserEmail="
				+ ticketUserEmail + ", ticketEventDate=" + ticketEventDate + "]";
	}
	

}
