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

/**
 * The Class ConsumerDAOTest.
 */
public class ConsumerDAOTest {
	
	/** The dao. */
	private ConsumerDAO dao;
	
	/** The test consumer. */
	private Consumer testConsumer;
	
	/** The rule. */
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		dao = ConsumerDAO.getInstance();
		testConsumer = new Consumer("Tester","test123","con@test.com","1111111111","TestingKing","Testson");
	}
	
	/**
	 * Save find delete test.
	 */
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void saveFindDeleteTest() {
		dao.save(testConsumer);
		Consumer found = dao.find(testConsumer.getEmail());
		assertEquals(testConsumer,found); 
		dao.delete(testConsumer);
		assertNull(dao.find(testConsumer.getEmail()));
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
		dao.save(testConsumer);
		List<Consumer> found = dao.getAll();
		assertTrue(found.contains(testConsumer));
		
		dao.delete(testConsumer);
	}
	
	

	
	
}
