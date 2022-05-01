package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.Ticket;

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

		// FIXME: only for testing purposes*******************
		// This is a replacement for searching in the DB
		List<Event> list = TestDBManager.getInstance().getAllEvents();
		// ****************************************************

		return list;
	}

	public void createEvent(String name, LocalDate date, String place, Organizer org) {
		// TODO: check if parameters are valid
		Event e = new Event(name, date, place, org);
		
		
		// FIXME: only for testing purposes*******************
		// This is a replacement for storing the event in the DB
		TestDBManager.getInstance().storeEvent(e);
		// ****************************************************
	}
}
