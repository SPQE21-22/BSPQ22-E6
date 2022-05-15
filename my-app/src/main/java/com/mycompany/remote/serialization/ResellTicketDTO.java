package com.mycompany.remote.serialization;

import java.time.LocalDate;

public class ResellTicketDTO {
	
	private long token;
	private String ticketEventName;
	private String ticketUserEmail;
	private String ticketEventDate;
	
	public long getToken() {
		return token;
	}
	public void setToken(long token) {
		this.token = token;
	}
	public String getTicketEventName() {
		return ticketEventName;
	}
	public void setTicketEventName(String ticketEventName) {
		this.ticketEventName = ticketEventName;
	}
	public String getTicketUserEmail() {
		return ticketUserEmail;
	}
	public void setTicketUserEmail(String ticketUserEmail) {
		this.ticketUserEmail = ticketUserEmail;
	}
	public String getTicketEventDate() {
		return ticketEventDate;
	}
	public void setTicketEventDate(String ticketEventDate) {
		this.ticketEventDate = ticketEventDate;
	}
	public void setTicketEventDate(LocalDate ticketEventDate) {
		this.ticketEventDate = ticketEventDate.toString();
	}
	@Override
	public String toString() {
		return "ResellTicketDTO [token=" + token + ", ticketEventName=" + ticketEventName + ", ticketUserEmail="
				+ ticketUserEmail + ", ticketEventDate=" + ticketEventDate + "]";
	}
	

}
