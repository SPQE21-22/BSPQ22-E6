package com.mycompany.remote.serialization;

import java.time.LocalDate;

/** The Class CreateEventDTO. */
public class CreateEventDTO {
	
	/** The name. */
	private String name;
	
	/** The date. */
	private String date;
	
	/** The place. */
	private String place;
	
	/** The organizer token. */
	private long organizerToken;
	
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
	 * Gets the organizer token.
	 *
	 * @return the organizer token
	 */
	public long getOrganizerToken() {
		return organizerToken;
	}
	
	/**
	 * Sets the organizer token.
	 *
	 * @param organizerToken the new organizer token
	 */
	public void setOrganizerToken(long organizerToken) {
		this.organizerToken = organizerToken;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CreateEventDTO [name=" + name + ", date=" + date + ", place=" + place + ", organizerToken="
				+ organizerToken + "]";
	}


}
