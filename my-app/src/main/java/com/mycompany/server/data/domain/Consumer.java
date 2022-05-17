package com.mycompany.server.data.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Consumer extends User {
	
	private String nickname;
	private String surname;

	// Associations
	@Join
	// This annotation maps the 1-N relationship as an intermediate table.
	@Persistent(mappedBy="owner", dependentElement = "true", defaultFetchGroup = "true")
	private List<Ticket> boughtTickets;

	public Consumer() {
		super();
		setNickname("");
		setSurname("");
		setBoughtTickets(new ArrayList<Ticket>());
	}

	public Consumer(String name, String password, String email, String phone, String nickname, String surname) {
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
		if (!boughtTickets.contains(t))
			boughtTickets.add(t);
	}

	public void removeBoughtTicket(Ticket t) {
		if (!boughtTickets.contains(t))
			boughtTickets.remove(t);
	}

	@Override
	public String toString() {
		return "Consumer [nickname=" + nickname + ", surname=" + surname + ", boughtTickets=" + boughtTickets + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(boughtTickets, nickname, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		return Objects.equals(boughtTickets, other.boughtTickets) && Objects.equals(nickname, other.nickname)
				&& Objects.equals(surname, other.surname);
	}

}
