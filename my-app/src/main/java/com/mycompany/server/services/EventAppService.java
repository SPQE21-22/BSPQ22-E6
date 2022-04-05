package com.mycompany.server.services;

import java.util.List;

import com.mycompany.server.data.domain.Event;

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
        /*TODO get a list of all the already created events
        */
		return null;
    }
    public void createEvent() {
        //TODO: create event with real parameters and check if parameters are valid
    	Event e = new Event();
    	//TODO: save the event to the DB
	}
}
