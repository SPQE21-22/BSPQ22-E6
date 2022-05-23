package com.mycompany.remote.serialization;

import java.time.LocalDate;
import java.util.Objects;

/** The Class TicketDTO.*/
public class TicketDTO {
	
	/** The event name. */
	private String eventName;
	
	/** The user email. */
	private String userEmail;
	
	/** The event date. */
	private String eventDate;
	
	/** The place. */
	private String place;
	
	/** The in resell. */
	private boolean inResell;
	
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
	 * Gets the user email.
	 *
	 * @return the user email
	 */
	public String getUserEmail() {
		return userEmail;
	}
	
	/**
	 * Sets the user email.
	 *
	 * @param userEmail the new user email
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
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
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate.toString();
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
	 * Gets the place.
	 *
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(String place) {
		this.place = place;
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
		return "TicketDTO [eventName=" + eventName + ", userEmail=" + userEmail + ", eventDate=" + eventDate
				+ ", place=" + place + ", inResell=" + inResell + "]";
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(eventDate, eventName, inResell, place, userEmail);
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
		TicketDTO other = (TicketDTO) obj;
		return Objects.equals(eventDate, other.eventDate) && Objects.equals(eventName, other.eventName)
				&& inResell == other.inResell && Objects.equals(place, other.place)
				&& Objects.equals(userEmail, other.userEmail);
	}
	
	
	
	
}
