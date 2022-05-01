package com.mycompany.client.controller;

import java.util.List;

import com.mycompany.client.remote.ClientTokenManagement;
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
			System.out.println("* Error using the server:");
			e.printStackTrace();
		}
		return list;

	}

}
