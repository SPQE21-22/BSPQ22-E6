package com.mycompany.client.controller;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.client.ClientApp;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.TicketDTO;

/**
 * The Class TicketController.
 */
public class TicketController {
	
	/** The instance. */
	private static TicketController instance;

	/**
	 * Gets the single instance of TicketController.
	 *
	 * @return single instance of TicketController
	 */
	public static TicketController getInstance() {
		if (instance == null) {
			instance = new TicketController();
		}

		return instance;
	}

	/**
	 * Instantiates a new ticket controller.
	 */
	private TicketController() {
	}

	/**
	 * Gets the bought tickets.
	 *
	 * @return the bought tickets
	 */
	public List<TicketDTO> getBoughtTickets() {

		List<TicketDTO> list = null;
		try {

			list = ServiceGateway.getInstance().getBoughtTickets();

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		return list;

	}

	/**
	 * Buy ticket.
	 *
	 * @param eventName the event name
	 * @param date the date
	 */
	public void buyTicket(String eventName, LocalDate date) {

		try {
			ServiceGateway.getInstance().buyTicket(eventName, date);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

	}

	/**
	 * Put ticket in resell.
	 *
	 * @param ticketUserEmail the ticket user email
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 */
	public void putTicketInResell(String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {
		try {
			ServiceGateway.getInstance().putTicketInResell(ticketUserEmail, ticketEventName, ticketEventDate);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		
	}
	
	/**
	 * Buy reselling ticket.
	 *
	 * @param ticketUserEmail the ticket user email
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 */
	public void buyResellingTicket(String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {
		try {
			ServiceGateway.getInstance().buyResellingTicket(ticketUserEmail, ticketEventName, ticketEventDate);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		
	}

	/**
	 * Gets the reselling tickets.
	 *
	 * @return the reselling tickets
	 */
	public List<TicketDTO> getResellingTickets() {
		List<TicketDTO> list = null;
		try {

			list = ServiceGateway.getInstance().getResellingTickets();

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		return list;

	}

}
