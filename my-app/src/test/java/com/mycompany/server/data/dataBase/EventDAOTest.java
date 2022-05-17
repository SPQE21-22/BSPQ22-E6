package com.mycompany.server.data.dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

public class EventDAOTest {
	private EventDAO dao;
	private Event testEvent;
	private Organizer testOg;
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@Before
	public void setup() {
		dao = EventDAO.getInstance();
		testOg = new Organizer("Tester","test123","test@test.com","1111111111","Testing st.","test.com");
		testEvent = new Event("TestingEvent", LocalDate.now(),"Testing place", testOg);
	}
	
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void saveFindTest() {
		dao.save(testEvent);
		
		assertEquals(testEvent,dao.find(testEvent.getName(),testEvent.getDate().toString())); //FIXME yet to be implemented
	}
	
	
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void deleteTest() {
		
		dao.save(testEvent);
		
		dao.delete(testEvent);
		
		assertNull(dao.find(testEvent.getName(),testEvent.getDate().toString()));//FIXME yet to be implemented
	}
	
	@After
	public void tearDown() {
		
		dao.delete(testEvent);
		OrganizerDAO.getInstance().delete(testOg);
	}
	

}
