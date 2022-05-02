package com.mycompany.remote.serialization;

import java.time.LocalDate;
import java.util.Objects;

public class TicketDTO {
	private String eventName;
	private String userEmail;
	private String eventDate;
	private String place;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate.toString();
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "TicketDTO [eventName=" + eventName + ", userEmail=" + userEmail + ", eventDate=" + eventDate
				+ ", place=" + place + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(eventDate, eventName, place, userEmail);
	}
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
				&& Objects.equals(place, other.place) && Objects.equals(userEmail, other.userEmail);
	}
	
	
}
