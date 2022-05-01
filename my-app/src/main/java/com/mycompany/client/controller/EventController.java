package com.mycompany.client.controller;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.client.ClientApp;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.EventDTO;

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
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
		return list;

	}

	public void createEvent(String name, LocalDate date, String place) {
		try {
			ServiceGateway.getInstance().createEvent(name, date, place);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}		
	}

}
