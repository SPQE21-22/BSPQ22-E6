package com.mycompany.remote.serialization;

import java.time.LocalDate;

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
	
	
}
