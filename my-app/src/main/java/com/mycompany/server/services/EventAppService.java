package com.mycompany.server.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	private EventAppService() {}

	public List<Event> getActiveEvents() {
		
		List<Event> list = new ArrayList<>();
		
		//FIXME: only for testing  purposes*******************
		Organizer og = new Organizer("testingOG", "testingOGpass", "test@og.com", "945000000", "testing street", "www.test.og.com");
		list.add(new Event("TestingEvent", LocalDate.now(), "testing event place", og));
		list.add(new Event("OldTestingEvent", LocalDate.parse("2010-09-23"), "testing event place", og));
		
		//****************************************************
		return list;
    }
    public void createEvent() {
        //TODO: create event with real parameters and check if parameters are valid
    	Event e = new Event();
    	//TODO: save the event to the DB
	}
}
