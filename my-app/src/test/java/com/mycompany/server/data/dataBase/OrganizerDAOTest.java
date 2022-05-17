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
import com.mycompany.server.data.domain.Organizer;

public class OrganizerDAOTest {
	private OrganizerDAO dao;
	private Organizer testOrganizer;
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@Before
	public void setup() {
		dao = OrganizerDAO.getInstance();
		testOrganizer = new Organizer("Tester","test123","test@test.com","1111111111","Testing st.","test.com");
	}
	
	@Test
	@PerfTest(invocations =100)
	@Required(max=1500, average=600)
	public void saveFindTest() {
		dao.save(testOrganizer);
		
		assertEquals(testOrganizer,dao.find("YET")); //FIXME yet to be implemented
	}
	
	
	@Test
	@PerfTest(invocations =100)
	@Required(max=1500, average=600)
	public void deleteTest() {
		
		dao.save(testOrganizer);
		
		dao.delete(testOrganizer);
		
		assertNull(dao.find("YET"));//FIXME yet to be implemented
	}
	
	@After
	public void tearDown() {
		dao.delete(testOrganizer);
	}

}
