package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.dataBase.ConsumerDAO;
import com.mycompany.server.data.dataBase.EventDAO;
import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.dataBase.TicketDAO;
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


		Event e = EventDAO.getInstance().find(eventName, eventDate.toString());


		Ticket ticket = new Ticket(e, consumer);
		consumer.addBoughtTicket(ticket);


		TicketDAO.getInstance().save(ticket);

	}

	public boolean putTicketInResell(Consumer c, String ticketUserMail, String ticketEventName,
			LocalDate ticketEventDate) {
		
		Ticket t = TicketDAO.getInstance().find(ticketEventName, ticketEventDate.toString(),ticketUserMail);
	
		if (t.getOwner().equals(c)) {
			t.setInResell(true);
			TicketDAO.getInstance().save(t);
			return true;
		}
		return false;

	}

	public boolean buyResellingTicket(Consumer buyer, String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {

		Ticket t = TicketDAO.getInstance().find(ticketEventName, ticketEventDate.toString(),ticketUserEmail);
		
		if (t.isInResell()) {
			
			TicketDAO.getInstance().delete(t);
			
			t.getOwner().removeBoughtTicket(t);
			t.setInResell(false);
			
			t.setOwner(buyer);
			buyer.addBoughtTicket(t);
			
			ConsumerDAO.getInstance().save(buyer);
			return true;
		}
		return false;
	}

	public List<Ticket> getResellingTickets() {

		return TicketDAO.getInstance().getInResellTickets();

	}
}
