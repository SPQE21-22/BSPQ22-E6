package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.server.data.domain.*;

/** The Class TokenManagement.*/
public class TokenManagement {
	
	/** The instance. */
	private static TokenManagement instance;
	
	/**
	 * Gets the single instance of TokenManagement.
	 *
	 * @return single instance of TokenManagement
	 */
	public static TokenManagement getInstance() {
		if (instance == null) {
			instance = new TokenManagement();
		}

		return instance;
	}
	
	/** The server state. */
	private Map<Long, User> serverState = new HashMap<>();
	
	/** Instantiates a new token management.*/
	public TokenManagement() {}
	
	/**
	 * Check token.
	 *
	 * @param token the token
	 * @return the user
	 * @throws RemoteException the remote exception
	 */
	public User checkToken(long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {
			
			return this.serverState.get(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
	/**
	 * Creates the token.
	 *
	 * @param user the user
	 * @return the long
	 * @throws RemoteException the remote exception
	 */
	public long createToken(User user) throws RemoteException {
		if (user != null) {
			/** If user is not logged in */
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
	}
	
	/**
	 * Removes the token.
	 *
	 * @param token the token
	 * @throws RemoteException the remote exception
	 */
	public void removeToken(long token) throws RemoteException  {
		if (this.serverState.containsKey(token)) {
			/** Logout means remove the User from Server State */
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
}
