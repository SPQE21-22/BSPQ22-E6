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

/** The Class TicketAppService. */
public class TicketAppService {

	/** The instance. */
	private static TicketAppService instance;

	/**
	 * Gets the single instance of TicketAppService.
	 *
	 * @return single instance of TicketAppService
	 */
	public static TicketAppService getInstance() {
		if (instance == null) {
			instance = new TicketAppService();
		}

		return instance;
	}

	/** Instantiates a new ticket app service.*/
	private TicketAppService() {
	}

	/**
	 * Gets the bought tickets.
	 *
	 * @param consumer the consumer
	 * @return the bought tickets
	 */
	public List<Ticket> getBoughtTickets(Consumer consumer) {

		return consumer.getBoughtTickets();
	}

	/**
	 * Buy ticket.
	 *
	 * @param consumer the consumer
	 * @param eventName the event name
	 * @param eventDate the event date
	 */
	public void buyTicket(Consumer consumer, String eventName, LocalDate eventDate) {


		Event e = EventDAO.getInstance().find(eventName, eventDate.toString());


		Ticket ticket = new Ticket(e, consumer);
		consumer.addBoughtTicket(ticket);


		TicketDAO.getInstance().save(ticket);

	}

	/**
	 * Put ticket in resell.
	 *
	 * @param c the c
	 * @param ticketUserMail the ticket user mail
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 * @return true, if successful
	 */
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

	/**
	 * Buy reselling ticket.
	 *
	 * @param buyer the buyer
	 * @param ticketUserEmail the ticket user email
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 * @return true, if successful
	 */
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

	/**
	 * Gets the reselling tickets.
	 *
	 * @return the reselling tickets
	 */
	public List<Ticket> getResellingTickets() {

		return TicketDAO.getInstance().getInResellTickets();

	}
}
