package com.mycompany.client;

import com.mycompany.client.remote.ServiceGateway;

public class ClientApp {
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
		System.out.println("This is the client side");
		
		//Here we call the testing methods
		ServiceGateway.getInstance().testingServer("JACOBTHETESTER");
	}
	
	
	

}
