package com.mycompany.server.data.domain;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends User {

	private String nickname;
	private String surname;
	private List<Ticket> boughtTickets;


	public Consumer() {
		super();
		setNickname("");
		setSurname("");
		setBoughtTickets(new ArrayList<Ticket>());
	}

	public Consumer(String name, String password, String email, String phone, String nickname, String surname ) {
		super(name, password, email, phone);
		setNickname(nickname);
		setSurname(surname);
		setBoughtTickets(new ArrayList<Ticket>());
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public List<Ticket> getBoughtTickets() {
		return boughtTickets;
	}

	public void setBoughtTickets(List<Ticket> boughtTickets) {
		this.boughtTickets = boughtTickets;
	}
	public void addBoughtTicket(Ticket t) {
		if(!boughtTickets.contains(t)) boughtTickets.add(t);
	}
	
	public void removeBoughtTicket(Ticket t) {
		if(!boughtTickets.contains(t)) boughtTickets.remove(t);
	}

	@Override
	public String toString() {
		return "Consumer [nickname=" + nickname + ", surname=" + surname + ", boughtTickets=" + boughtTickets + "]";
	}


}
