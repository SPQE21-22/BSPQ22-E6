package com.mycompany.server.data.dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import com.mycompany.server.data.domain.Consumer;


public class ConsumerDAOTest {
	
	private ConsumerDAO dao;
	private Consumer testConsumer;
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@Before
	public void setup() {
		dao = ConsumerDAO.getInstance();
		testConsumer = new Consumer("Tester","test123","test@test.com","1111111111","TestingKing","Testson");
	}
	
	@Test
	@PerfTest(invocations =100)
	@Required(max=1500, average=600)
	public void saveFindTest() {
		dao.save(testConsumer);
		
		assertEquals(testConsumer,dao.find(testConsumer.getEmail())); //FIXME yet to be implemented
	}
	
	
	@Test
	@PerfTest(invocations =100)
	@Required(max=1500, average=600)
	public void deleteTest() {
		
		dao.save(testConsumer);
		
		dao.delete(testConsumer);
		
		assertNull(dao.find(testConsumer.getEmail()));//FIXME yet to be implemented
	}
	
	@After
	public void tearDown() {
		dao.delete(testConsumer);
	}
	
}
