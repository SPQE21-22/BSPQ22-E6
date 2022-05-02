package com.mycompany.server.services;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

public class EventAppServiceTest {

	List<Event> testingList;
	Organizer allEventOrg;

	@Before
	public void setup() {
		// Initialize the list
		testingList = new ArrayList<>();

		allEventOrg = new Organizer("AllEvent", "1234", "t@t.com", "600000000", "Test str.", "www.test.com");

		for (int i = 0; i < 100; i++) {
			Event e = new Event("Event-" + i, LocalDate.now(), "TestPlace", allEventOrg);
			
			testingList.add(e);
		}
		
	}

	@Test
	public void getActiveEventsTest() {
		List<Event> list = EventAppService.getInstance().getActiveEvents();
		
		assertTrue("Not Implemented yet", true);
	}

	@Test
	public void createEventTest() {
		
		for (Event e: testingList) {
			
			EventAppService.getInstance().createEvent(e.getName(),e.getDate(),e.getPlace(),e.getOrganizer());
			
		}
		
	}

}
