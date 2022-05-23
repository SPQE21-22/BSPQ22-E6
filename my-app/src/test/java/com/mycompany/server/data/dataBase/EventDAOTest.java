package com.mycompany.server.data.dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

/** The Class EventDAOTest.*/
public class EventDAOTest {
	
	/** The dao. */
	private EventDAO dao;
	
	/** The test event. */
	private Event testEvent;
	
	/** The test event 2. */
	private Event testEvent2;
	
	/** The test og. */
	private Organizer testOg;
	
	/** The rule. */
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		dao = EventDAO.getInstance();
		testOg = new Organizer("Tester","test123","test@test.com","1111111111","Testing st.","test.com");
		testEvent = new Event("TestingEvent", LocalDate.now().plusDays(1),"Testing place", testOg);
		testEvent2 = new Event("TestingEvent2", LocalDate.now().minusDays(1),"Testing place", testOg);
	}
	
	/**
	 * Save find delete test.
	 */
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void saveFindDeleteTest() {
		dao.save(testEvent);
		
		Event found = dao.find(testEvent.getName(),testEvent.getDate().toString());
		assertEquals(testEvent,found);
		
		dao.delete(testEvent);
		assertNull(dao.find(testEvent.getName(),testEvent.getDate().toString()));
	
	}
	
	/**
	 * Gets the all test.
	 *
	 * @return the all test
	 */
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void getAllTest() {
		dao.save(testEvent);
		List<Event> found = dao.getAll();
		assertTrue(found.contains(testEvent));
		
		dao.delete(testEvent);
	}
	
	/**
	 * Gets the active test.
	 *
	 * @return the active test
	 */
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void getActiveTest() {
		dao.save(testEvent);
		dao.save(testEvent2);
		List<Event> found = dao.getAll();
		assertTrue(found.contains(testEvent));
		assertFalse(found.contains(testEvent2));
		
		dao.delete(testEvent);
		dao.delete(testEvent2);
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {

		OrganizerDAO.getInstance().delete(testOg);
	}
	

}
