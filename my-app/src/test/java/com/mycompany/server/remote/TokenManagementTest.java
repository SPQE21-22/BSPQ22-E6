package com.mycompany.server.remote;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.User;

/**
 * The Class TokenManagementTest.
 */
public class TokenManagementTest {

	/** The u test. */
	User uTest;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		uTest = new Organizer("AllEvent", "1234", "t@t.com", "600000000", "Test str.", "www.test.com");
	}

	/**
	 * Creates the token test.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test
	public void createTokenTest() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		assertTrue("Valid token is created", token > 0);

	}

	/**
	 * Creates the token test 2.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test(expected = RemoteException.class)
	public void createTokenTest2() throws RemoteException {

		TokenManagement.getInstance().createToken(uTest);
		TokenManagement.getInstance().createToken(uTest);

	}
	

	/**
	 * Removes the token test.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test(expected = RemoteException.class)
	public void removeTokenTest() throws RemoteException {
		TokenManagement.getInstance().removeToken(-1);
	}
	
	/**
	 * Removes the token test 2.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test(expected = RemoteException.class)
	public void removeTokenTest2() throws RemoteException {
		long token = TokenManagement.getInstance().createToken(uTest);
		
		TokenManagement.getInstance().removeToken(token);
		TokenManagement.getInstance().removeToken(token);
	}
	
	/**
	 * Check token test.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test
	public void checkTokenTest() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		
		assertEquals("The user checked equals original user",uTest,TokenManagement.getInstance().checkToken(token));
	}
	
	/**
	 * Check token test 2.
	 *
	 * @throws RemoteException the remote exception
	 */
	@Test(expected = RemoteException.class)
	public void checkTokenTest2() throws RemoteException {
		long token;

		token = TokenManagement.getInstance().createToken(uTest);
		
		TokenManagement.getInstance().removeToken(token);
		
		TokenManagement.getInstance().checkToken(token);

	}
	
	

}
