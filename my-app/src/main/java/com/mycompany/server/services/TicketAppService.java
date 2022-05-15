package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;

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

	public List<Ticket> getBoughtTickets(Consumer consumer) {

		return consumer.getBoughtTickets();
	}

	public void buyTicket(Consumer consumer, String eventName, LocalDate eventDate) {

		// FIXME: only for testing purposes*******************
		// This is a replacement for searching the event in the DB
		Event e = TestDBManager.getInstance().getEvent(eventName, eventDate);
		// ****************************************************

		Ticket ticket = new Ticket(e, consumer);
		consumer.addBoughtTicket(ticket);

		// FIXME: only for testing purposes*******************
		// This is a replacement for storing the ticket in the DB
		TestDBManager.getInstance().storeTicket(ticket);
		// ****************************************************

	}

	public boolean putTicketInResell(Consumer c, String ticketUserMail, String ticketEventName,
			LocalDate ticketEventDate) {

		// FIXME: only for testing purposes*******************
		// This is a replacement for searching the event in the DB
		Ticket t = TestDBManager.getInstance().getTicket(ticketUserMail, ticketEventName, ticketEventDate);
		// ****************************************************
		if (t.getOwner().equals(c)) {
			t.setInResell(true);
			return true;
		}
		return false;

	}
}
