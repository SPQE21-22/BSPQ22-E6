package com.mycompany.client.remote;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ServiceGateway {
	private static ServiceGateway instance;
	
	public static ServiceGateway getInstance() {
		if (instance == null) {
			instance = new ServiceGateway();
		}

		return instance;
	}
	
	private ServiceGateway() {}
	
	public void initGateway(String hostname, String port) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
	}

}
