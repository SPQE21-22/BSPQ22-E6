package com.mycompany.server.remote;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.User;

public class TokenManagementTest {

	User uTest;

	@Before
	public void setup() {
		uTest = new Organizer("AllEvent", "1234", "t@t.com", "600000000", "Test str.", "www.test.com");
	}

	@Test
	public void createTokenTest() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		assertTrue("Valid token is created", token > 0);

	}

	@Test(expected = RemoteException.class)
	public void createTokenTest2() throws RemoteException {

		TokenManagement.getInstance().createToken(uTest);
		TokenManagement.getInstance().createToken(uTest);

	}
	

	@Test(expected = RemoteException.class)
	public void removeTokenTest() throws RemoteException {
		TokenManagement.getInstance().removeToken(-1);
	}
	@Test(expected = RemoteException.class)
	public void removeTokenTest2() throws RemoteException {
		long token = TokenManagement.getInstance().createToken(uTest);
		
		TokenManagement.getInstance().removeToken(token);
		TokenManagement.getInstance().removeToken(token);
	}
	
	@Test
	public void checkTokenTest() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		
		assertEquals("The user checked equals original user",uTest,TokenManagement.getInstance().checkToken(token));
	}
	
	@Test(expected = RemoteException.class)
	public void checkTokenTest2() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		
		TokenManagement.getInstance().removeToken(token);
		
		TokenManagement.getInstance().checkToken(token);

	}
	
	

}
