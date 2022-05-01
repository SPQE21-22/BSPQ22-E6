package com.mycompany.server.services;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

public class TicketAppService {

	private static TicketAppService instance;

	public static TicketAppService getInstance() {
		if (instance == null) {
			instance = new TicketAppService();
		}

		return instance;
	}

	private TicketAppService() {
	}

	public List<Ticket> getBoughtTickets(User user) {

		return user.getBoughtTickets();
	}

	public void buyTicket(User user, String eventName, LocalDate eventDate) {

		// FIXME: only for testing purposes*******************
		//This is a replacement for searching the event in the DB
		Organizer og = new Organizer("testingOG", "testingOGpass", "test@og.com", "945000000", "testing street", "www.test.og.com");
		Event event = new Event(eventName, eventDate, "testing event place", og);
		// ****************************************************

		Ticket ticket = new Ticket(event, user);
		user.addBoughtTicket(ticket);
		// TODO store the ticket in the database
	}
}
