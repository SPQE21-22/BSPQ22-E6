package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.server.data.domain.*;

public class TokenManagement {
	private static TokenManagement instance;
	
	public static TokenManagement getInstance() {
		if (instance == null) {
			instance = new TokenManagement();
		}

		return instance;
	}
	
	private Map<Long, User> serverState = new HashMap<>();
	
	public TokenManagement() {}
	
	public User checkToken(long token) throws RemoteException {
		if (this.serverState.containsKey(token)) {
			
			return this.serverState.get(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
	public long createToken(User user) throws RemoteException {
		if (user != null) {
			//If user is not logged in 
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
	
	public void removeToken(long token) throws RemoteException  {
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
}
