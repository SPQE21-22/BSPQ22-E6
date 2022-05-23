package com.mycompany.client.controller;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.client.ClientApp;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.EventDTO;

/** The Class EventController.*/
public class EventController {
	
	/** The instance. */
	private static EventController instance;

	/**
	 * Gets the single instance of EventController.
	 *
	 * @return single instance of EventController
	 */
	public static EventController getInstance() {
		if (instance == null) {
			instance = new EventController();
		}

		return instance;
	}

	/** Instantiates a new event controller.*/
	private EventController() {
	}

	/**
	 * Gets the active events.
	 *
	 * @return the active events
	 */
	public List<EventDTO> getActiveEvents() {

		List<EventDTO> list = null;
		try {
			
			list = ServiceGateway.getInstance().getActiveEvents();
			
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//** e.printStackTrace(); */
		}
		return list;

	}

	/**
	 * Creates the event.
	 *
	 * @param name the name
	 * @param date the date
	 * @param place the place
	 */
	public void createEvent(String name, LocalDate date, String place) {
		try {
			ServiceGateway.getInstance().createEvent(name, date, place);

		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//** e.printStackTrace(); */
		}		
	}

}
