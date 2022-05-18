package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.dataBase.EventDAO;
import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

public class EventAppService {

	private static EventAppService instance;

	public static EventAppService getInstance() {
		if (instance == null) {
			instance = new EventAppService();
		}

		return instance;
	}

	private EventAppService() {
	}

	public List<Event> getActiveEvents() {

		return EventDAO.getInstance().getActiveEvents();
	}

	public void createEvent(String name, LocalDate date, String place, Organizer org) {
		// TODO: check if parameters are valid
		Event e = new Event(name, date, place, org);
		
		
		EventDAO.getInstance().save(e);
	}

	
}
