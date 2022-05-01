package com.mycompany.client.controller;

import java.util.List;

import com.mycompany.client.remote.ClientTokenManagement;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.TicketDTO;

public class EventController {
	private static EventController instance;

	public static EventController getInstance() {
		if (instance == null) {
			instance = new EventController();
		}

		return instance;
	}

	private EventController() {
	}

	public List<EventDTO> getActiveEvents() {

		List<EventDTO> list = null;
		try {
			
			list = ServiceGateway.getInstance().getActiveEvents();
			
		} catch (Exception e) {
			System.out.println("* Error using the server:");
			e.printStackTrace();
		}
		return list;

	}

}
