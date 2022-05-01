package com.mycompany.remote.serialization;

import java.time.LocalDate;

public class CreateEventDTO {
	private String name;
	private String date;
	private String place;
	private long organizerToken;
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
	public long getOrganizerToken() {
		return organizerToken;
	}
	public void setOrganizerToken(long organizerToken) {
		this.organizerToken = organizerToken;
	}
	@Override
	public String toString() {
		return "CreateEventDTO [name=" + name + ", date=" + date + ", place=" + place + ", organizerToken="
				+ organizerToken + "]";
	}


}
