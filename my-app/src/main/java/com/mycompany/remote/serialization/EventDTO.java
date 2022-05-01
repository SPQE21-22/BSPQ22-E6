package com.mycompany.remote.serialization;

import java.time.LocalDate;

public class EventDTO {
	private String name;
	private String date;
	private String place;
	private String organizerEmail;
	private String organizerWeb;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDate(LocalDate date) {
		this.date = date.toString();
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOrganizerEmail() {
		return organizerEmail;
	}
	public void setOrganizerEmail(String organizerEmail) {
		this.organizerEmail = organizerEmail;
	}
	public String getOrganizerWeb() {
		return organizerWeb;
	}
	public void setOrganizerWeb(String organizerWeb) {
		this.organizerWeb = organizerWeb;
	}
	@Override
	public String toString() {
		return "EventDTO [name=" + name + ", date=" + date + ", place=" + place + ", organizerEmail=" + organizerEmail
				+ ", organizerWeb=" + organizerWeb + "]";
	}
	

}
