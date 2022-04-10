package com.mycompany.client.remote;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ServiceGateway {
	private static ServiceGateway instance;
	private WebTarget baseTarget; 
	
	public static ServiceGateway getInstance() {
		if (instance == null) {
			instance = new ServiceGateway();
		}

		return instance;
	}
	
	private ServiceGateway() {}
	
	public void initGateway(String hostname, String port) {
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		Client client = ClientBuilder.newClient();
		baseTarget = client.target(BASE_URI);
		
		
	}
	
	public void testingServer(String name) {
		WebTarget testTarget = baseTarget.path("test");
		WebTarget newTarget = testTarget.path(name); //This is the name that will be displayed
		Response r = newTarget.request().get();
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly done the testing");
		}else {
			System.out.println("Server has problems with the testing");
		}
	}

}
