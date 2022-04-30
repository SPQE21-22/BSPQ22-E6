package com.mycompany.client;

import com.mycompany.client.controller.UserController;
import com.mycompany.client.remote.ServiceGateway;

public class ClientApp {
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
		System.out.println("This is the client side");
		

		//Here we call the testing methods
		UserController.getInstance().register("TestingUser@test.com", "testingpass","Tester","696969696");
		UserController.getInstance().login("TestingUser@test.com", "testingpass");
		UserController.getInstance().logout();
		
	}
	
	
	

}
