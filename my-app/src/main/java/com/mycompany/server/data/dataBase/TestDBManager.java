package com.mycompany.server.data.dataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;

public class TestDBManager {
	List<User> storedUsers = new ArrayList<>();
	List<Ticket> storedTickets = new ArrayList<>();
	List<Event> storedEvents= new ArrayList<>();


	
	private static TestDBManager instance = null;

	public static TestDBManager getInstance() {
		if (instance == null) {
			instance = new TestDBManager();
		}

		return instance;
	}
	
	public boolean storeUser(User u) {
		if (!storedUsers.contains(u)) {
			storedUsers.add(u);
			return true;
		}
		return false;
	}
	
	public User getUser(String email, String password) {
		User found = null;
		for (User u :storedUsers) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
				found = u;
			}
		}
		return found;
	}
	
	
	public boolean storeTicket(Ticket t) {
		if (!storedTickets.contains(t)) {
			storedTickets.add(t);
			return true;
		}
		return false;
	}
	
	public boolean deleteTicket(Ticket t) {
		if (!storedTickets.contains(t)) {
			storedTickets.remove(t);
			return true;
		}
		return false;
	}
	
	public boolean deleteUser(User u) {
		if (!storedUsers.contains(u)) {
			storedUsers.remove(u);
			return true;
		}
		return false;
	}
	public boolean deleteEvent(Event e) {
		if (!storedEvents.contains(e)) {
			storedEvents.remove(e);
			return true;
		}
		return false;
	}
	
	public Ticket getTicket(String consumerEmail, String eventName, LocalDate eventDate) {
		Ticket found = null;
		for (Ticket t :storedTickets) {
			if (t.getOwner().getEmail().equals(consumerEmail) && t.getEvent().getName().equals(eventName) && t.getEvent().getDate().equals(eventDate))  {
				found = t;
			}
		}
		return found;
	}
	
	
	public boolean storeEvent(Event e) {
		if (!storedEvents.contains(e)) {
			storedEvents.add(e);
			return true;
		}
		return false;
	}
	
	public Event getEvent(String name, LocalDate date) {
		Event found = null;
		for (Event e :storedEvents) {
			if (e.getName().equals(name) && e.getDate().equals(date))  {
				found = e;
			}
		}
		return found;
	}

	public List<Event> getAllEvents() {
		return storedEvents;
	}
}
