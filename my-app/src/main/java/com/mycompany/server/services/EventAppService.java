package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.dataBase.EventDAO;
import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

/**  The Class EventAppService.*/
public class EventAppService {

	/** The instance. */
	private static EventAppService instance;

	/**
	 * Gets the single instance of EventAppService.
	 *
	 * @return single instance of EventAppService
	 */
	public static EventAppService getInstance() {
		if (instance == null) {
			instance = new EventAppService();
		}

		return instance;
	}

	/** Instantiates a new event app service.*/
	private EventAppService() {
	}

	/**
	 * Gets the active events.
	 *
	 * @return the active events
	 */
	public List<Event> getActiveEvents() {

		return EventDAO.getInstance().getActiveEvents();
	}

	/**
	 * Creates the event.
	 *
	 * @param name the name
	 * @param date the date
	 * @param place the place
	 * @param org the org
	 */
	public void createEvent(String name, LocalDate date, String place, Organizer org) {
		// TODO: check if parameters are valid
		Event e = new Event(name, date, place, org);
		
		
		EventDAO.getInstance().save(e);
	}

	
}
