package com.mycompany.client.remote;

/** The Class ClientTokenManagement.*/
public class ClientTokenManagement {
	
	/** The token. */
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails
	
	/** The instance. */
	private static ClientTokenManagement instance;
	
	/**
	 * Gets the single instance of ClientTokenManagement.
	 *
	 * @return single instance of ClientTokenManagement
	 */
	public static ClientTokenManagement getInstance() {
		if (instance == null) {
			instance = new ClientTokenManagement();
		}

		return instance;
	}
	
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(long token) {
		this.token = token;
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public long getToken() {
		return token;
	}
}
