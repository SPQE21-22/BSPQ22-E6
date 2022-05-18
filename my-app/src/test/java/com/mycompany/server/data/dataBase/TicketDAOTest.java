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
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.Ticket;

public class TicketDAOTest {
	private TicketDAO dao;
	private Event testEv;
	private Event testEv2;
	private Organizer testOg;
	private Consumer testCon;
	private Ticket testTicket;
	private Ticket testTicket2;

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Before
	public void setup() {
		dao = TicketDAO.getInstance();
		testOg = new Organizer("Tester", "test123", "test@test.com", "1111111111", "Testing st.", "test.com");
		testCon = new Consumer("Testerino", "test1234", "test1234@test.com", "1111111111", "TestingKing", "Testson");
		testEv = new Event("TestingEvent", LocalDate.now(), "Testing place", testOg);
		testEv2 = new Event("TestingEvent2", LocalDate.now().plusDays(1), "Testing place", testOg);
		testTicket = new Ticket(testEv, testCon);
		testTicket2 = new Ticket(testEv2, testCon);
	}

	@Test
	@PerfTest(invocations = 1)
	@Required(max = 1500, average = 600)
	public void saveFindDeletetest() {
		dao.save(testTicket);

		assertEquals(testTicket, dao.find(testTicket.getEvent().getName(), testTicket.getEvent().getDate().toString(),
				testTicket.getOwner().getEmail()));

		dao.delete(testTicket);

		assertNull(dao.find(testTicket.getEvent().getName(), testTicket.getEvent().getDate().toString(),
				testTicket.getOwner().getEmail()));

	}
	
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void getAllTest() {
		dao.save(testTicket);
		List<Ticket> found = dao.getAll();
		assertTrue(found.contains(testTicket));
		
		dao.delete(testTicket);
	}
	
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void getInResellTest() {
		dao.save(testTicket);
		
		testTicket2.setInResell(true);
		
		dao.save(testTicket2);
		
		List<Ticket> found = dao.getInResellTickets();
		assertTrue(found.contains(testTicket2));
		assertFalse(found.contains(testTicket));
		
		dao.delete(testTicket);
		dao.delete(testTicket);
	}

	@After
	public void tearDown() {

		ConsumerDAO.getInstance().delete(testCon);
		EventDAO.getInstance().delete(testEv);
		OrganizerDAO.getInstance().delete(testOg);
	}

}
