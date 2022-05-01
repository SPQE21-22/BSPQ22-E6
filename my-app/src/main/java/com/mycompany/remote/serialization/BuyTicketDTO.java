package com.mycompany.remote.serialization;


public class BuyTicketDTO { 
	
	private long token;
	private String eventDate;
	private String eventName;


	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setToken(long token) {
		this.token = token;
	}

	public long getToken() {
		return this.token;
	}

	@Override
	public String toString() {
		return "BuyTicketDTO [token=" + token + ", eventDate=" + eventDate + ", eventName=" + eventName + "]";
	}
	

}
