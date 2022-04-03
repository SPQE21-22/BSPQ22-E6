package com.mycompany.client.remote;


public class ClientTokenManagement {
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails
	
	private static ClientTokenManagement instance;
	
	public static ClientTokenManagement getInstance() {
		if (instance == null) {
			instance = new ClientTokenManagement();
		}

		return instance;
	}
	
	
	public void setToken(long token) {
		this.token = token;
	}
	
	public long getToken() {
		return token;
	}
}
