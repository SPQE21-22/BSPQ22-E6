package com.mycompany.server.data.dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Organizer;

/**
 * The Class OrganizerDAOTest.
 */
public class OrganizerDAOTest {
	
	/** The dao. */
	private OrganizerDAO dao;
	
	/** The test organizer. */
	private Organizer testOrganizer;

	/** The rule. */
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		dao = OrganizerDAO.getInstance();
		testOrganizer = new Organizer("Tester", "test123", "og@test.com", "1111111111", "Testing st.", "test.com");
	}

	/**
	 * Save find delete test.
	 */
	@Test
	@PerfTest(invocations = 1)
	@Required(max = 1500, average = 600)
	public void saveFindDeleteTest() {
		dao.save(testOrganizer);

		assertEquals(testOrganizer, dao.find(testOrganizer.getEmail()));
		dao.delete(testOrganizer);

		assertNull(dao.find(testOrganizer.getEmail()));

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
		dao.save(testOrganizer);
		List<Organizer> found = dao.getAll();
		assertTrue(found.contains(testOrganizer));
		
		dao.delete(testOrganizer);
	}
}