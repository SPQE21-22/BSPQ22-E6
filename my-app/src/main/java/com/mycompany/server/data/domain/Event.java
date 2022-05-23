package com.mycompany.server.data.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/** The Class Event.*/
@PersistenceCapable(detachable = "true")
public class Event {
	
	/** The name. */
	private String name;
	
	/** The date. */
	private LocalDate date;
	
	/** The place. */
	private String place;
	
	/** The date in string. */
	private String dateInString;
	
	/** The organizer. */
	@Persistent(defaultFetchGroup="true")
	private Organizer organizer;

	/**
	 * Instantiates a new event.
	 *
	 * @param name the name
	 * @param date the date
	 * @param place the place
	 * @param organizer the organizer
	 */
	public Event(String name, LocalDate date, String place, Organizer organizer) {
		super();
		this.name = name;
		this.date = date;
		this.dateInString = this.date.toString();
		this.place = place;
		this.organizer = organizer;
	}

	/** Instantiates a new event.*/
	public Event() {
		super();
		this.name = "";
		this.date = LocalDate.now();
		this.dateInString = this.date.toString();
		this.place = "";
		this.organizer = null;
	}

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
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
		this.dateInString = this.date.toString();
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
	 * Gets the organizer.
	 *
	 * @return the organizer
	 */
	public Organizer getOrganizer() {
		return organizer;
	}

	/**
	 * Sets the organizer.
	 *
	 * @param organizer the new organizer
	 */
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Name: " + name + ", Place: " + place + ", Date: " + date + ", Organizer: " + organizer;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(date, name, organizer, place);
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
		Event other = (Event) obj;
		return Objects.equals(date, other.date) && Objects.equals(name, other.name)
				&& Objects.equals(organizer, other.organizer) && Objects.equals(place, other.place);
	}
	
}
