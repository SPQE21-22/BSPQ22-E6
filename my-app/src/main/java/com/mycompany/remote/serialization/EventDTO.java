package com.mycompany.remote.serialization;

import java.time.LocalDate;
import java.util.Objects;

/** The Class EventDTO.*/
public class EventDTO {
	
	/** The name. */
	private String name;
	
	/** The date. */
	private String date;
	
	/** The place. */
	private String place;
	
	/** The organizer email. */
	private String organizerEmail;
	
	/** The organizer web. */
	private String organizerWeb;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date.toString();
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
	 * Gets the organizer email.
	 *
	 * @return the organizer email
	 */
	public String getOrganizerEmail() {
		return organizerEmail;
	}
	
	/**
	 * Sets the organizer email.
	 *
	 * @param organizerEmail the new organizer email
	 */
	public void setOrganizerEmail(String organizerEmail) {
		this.organizerEmail = organizerEmail;
	}
	
	/**
	 * Gets the organizer web.
	 *
	 * @return the organizer web
	 */
	public String getOrganizerWeb() {
		return organizerWeb;
	}
	
	/**
	 * Sets the organizer web.
	 *
	 * @param organizerWeb the new organizer web
	 */
	public void setOrganizerWeb(String organizerWeb) {
		this.organizerWeb = organizerWeb;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EventDTO [name=" + name + ", date=" + date + ", place=" + place + ", organizerEmail=" + organizerEmail
				+ ", organizerWeb=" + organizerWeb + "]";
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(date, name, organizerEmail, organizerWeb, place);
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
		EventDTO other = (EventDTO) obj;
		return Objects.equals(date, other.date) && Objects.equals(name, other.name)
				&& Objects.equals(organizerEmail, other.organizerEmail)
				&& Objects.equals(organizerWeb, other.organizerWeb) && Objects.equals(place, other.place);
	}
	

}
