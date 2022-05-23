package com.mycompany.server.data.dataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;

/** The Class TestDBManager. */
public class TestDBManager {
	
	/** The stored users. */
	List<User> storedUsers = new ArrayList<>();
	
	/** The stored tickets. */
	List<Ticket> storedTickets = new ArrayList<>();
	
	/** The stored events. */
	List<Event> storedEvents= new ArrayList<>();


	
	/** The instance. */
	private static TestDBManager instance = null;

	/**
	 * Gets the single instance of TestDBManager.
	 *
	 * @return single instance of TestDBManager
	 */
	public static TestDBManager getInstance() {
		if (instance == null) {
			instance = new TestDBManager();
		}

		return instance;
	}
	
	/**
	 * Store user.
	 *
	 * @param u the u
	 * @return true, if successful
	 */
	public boolean storeUser(User u) {
		if (!storedUsers.contains(u)) {
			storedUsers.add(u);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user
	 */
	public User getUser(String email, String password) {
		User found = null;
		for (User u :storedUsers) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
				found = u;
			}
		}
		return found;
	}
	
	
	/**
	 * Store ticket.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	public boolean storeTicket(Ticket t) {
		if (!storedTickets.contains(t)) {
			storedTickets.add(t);
			return true;
		}
		return false;
	}
	
	/**
	 * Delete ticket.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	public boolean deleteTicket(Ticket t) {
		if (!storedTickets.contains(t)) {
			storedTickets.remove(t);
			return true;
		}
		return false;
	}
	
	/**
	 * Delete user.
	 *
	 * @param u the u
	 * @return true, if successful
	 */
	public boolean deleteUser(User u) {
		if (!storedUsers.contains(u)) {
			storedUsers.remove(u);
			return true;
		}
		return false;
	}
	
	/**
	 * Delete event.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean deleteEvent(Event e) {
		if (!storedEvents.contains(e)) {
			storedEvents.remove(e);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the ticket.
	 *
	 * @param consumerEmail the consumer email
	 * @param eventName the event name
	 * @param eventDate the event date
	 * @return the ticket
	 */
	public Ticket getTicket(String consumerEmail, String eventName, LocalDate eventDate) {
		Ticket found = null;
		for (Ticket t :storedTickets) {
			if (t.getOwner().getEmail().equals(consumerEmail) && t.getEvent().getName().equals(eventName) && t.getEvent().getDate().equals(eventDate))  {
				found = t;
			}
		}
		return found;
	}
	
	
	/**
	 * Store event.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean storeEvent(Event e) {
		if (!storedEvents.contains(e)) {
			storedEvents.add(e);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the event.
	 *
	 * @param name the name
	 * @param date the date
	 * @return the event
	 */
	public Event getEvent(String name, LocalDate date) {
		Event found = null;
		for (Event e :storedEvents) {
			if (e.getName().equals(name) && e.getDate().equals(date))  {
				found = e;
			}
		}
		return found;
	}

	/**
	 * Gets the active events.
	 *
	 * @return the active events
	 */
	public List<Event> getActiveEvents() {
		List<Event> active = new ArrayList<>();
		for (Event e: storedEvents) {
			if (e.getDate().isAfter(LocalDate.now())) {
				active.add(e);
			}
		}
		return active;
	}

	/**
	 * Gets the reselling tickets.
	 *
	 * @return the reselling tickets
	 */
	public List<Ticket> getResellingTickets() {
		List<Ticket> reselling = new ArrayList<>();
		for (Ticket t: storedTickets) {
			if (t.isInResell()) {
				reselling.add(t);
			}
		}
		return reselling;
	}
}
