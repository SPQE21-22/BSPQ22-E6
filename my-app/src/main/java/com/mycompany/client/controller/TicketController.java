package com.mycompany.client.controller;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.client.ClientApp;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.TicketDTO;

public class TicketController {
	private static TicketController instance;

	public static TicketController getInstance() {
		if (instance == null) {
			instance = new TicketController();
		}

		return instance;
	}

	private TicketController() {
	}

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

	public void buyTicket(String name, LocalDate date) {

		try {
			ServiceGateway.getInstance().buyTicket(name, date);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

	}

	public void putTicketInResell(String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {
		try {
			ServiceGateway.getInstance().putTicketInResell(ticketUserEmail, ticketEventName, ticketEventDate);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		
	}

}
