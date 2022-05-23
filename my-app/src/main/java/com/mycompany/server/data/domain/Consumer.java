package com.mycompany.server.data.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/** The Class Consumer. */
@PersistenceCapable(detachable = "true")
public class Consumer extends User {
	
	/** The nickname. */
	private String nickname;
	
	/** The surname. */
	private String surname;

	/**  Associations. */
	@Join
	/** This annotation maps the 1-N relationship as an intermediate table. */
	@Persistent(mappedBy="owner", dependentElement = "true", defaultFetchGroup = "true")
	private List<Ticket> boughtTickets;

	/**
	 * Instantiates a new consumer.
	 */
	public Consumer() {
		super();
		setNickname("");
		setSurname("");
		setBoughtTickets(new ArrayList<Ticket>());
	}

	/**
	 * Instantiates a new consumer.
	 *
	 * @param name the name
	 * @param password the password
	 * @param email the email
	 * @param phone the phone
	 * @param nickname the nickname
	 * @param surname the surname
	 */
	public Consumer(String name, String password, String email, String phone, String nickname, String surname) {
		super(name, password, email, phone);
		setNickname(nickname);
		setSurname(surname);
		setBoughtTickets(new ArrayList<Ticket>());
	}

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the bought tickets.
	 *
	 * @return the bought tickets
	 */
	public List<Ticket> getBoughtTickets() {
		return boughtTickets;
	}

	/**
	 * Sets the bought tickets.
	 *
	 * @param boughtTickets the new bought tickets
	 */
	public void setBoughtTickets(List<Ticket> boughtTickets) {
		this.boughtTickets = boughtTickets;
	}

	/**
	 * Adds the bought ticket.
	 *
	 * @param t the t
	 */
	public void addBoughtTicket(Ticket t) {
		if (!boughtTickets.contains(t))
			boughtTickets.add(t);
	}

	/**
	 * Removes the bought ticket.
	 *
	 * @param t the t
	 */
	public void removeBoughtTicket(Ticket t) {
		if (!boughtTickets.contains(t))
			boughtTickets.remove(t);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Consumer [nickname=" + nickname + ", surname=" + surname + ", boughtTickets=" + boughtTickets + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(boughtTickets, nickname, surname);
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
		Consumer other = (Consumer) obj;
		return Objects.equals(boughtTickets, other.boughtTickets) && Objects.equals(nickname, other.nickname)
				&& Objects.equals(surname, other.surname);
	}

}
