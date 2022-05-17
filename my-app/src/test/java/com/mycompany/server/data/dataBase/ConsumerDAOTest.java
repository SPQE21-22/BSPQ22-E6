package com.mycompany.server.data.dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
		testConsumer = new Consumer("Tester","test123","con@test.com","1111111111","TestingKing","Testson");
	}
	
	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void test() {
		dao.save(testConsumer);
		Consumer found = dao.find(testConsumer.getEmail());
		assertEquals(testConsumer,found); 
		dao.delete(testConsumer);
		assertNull(dao.find(testConsumer.getEmail()));
	}
	
	

	
	
}
