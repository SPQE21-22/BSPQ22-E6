package com.mycompany.client;

import java.util.List;

import com.mycompany.client.controller.EventController;
import com.mycompany.client.controller.TicketController;
import com.mycompany.client.controller.UserController;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.TicketDTO;

public class ClientApp {
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
		System.out.println("This is the client side");

		
		
		// Here we call the testing methods
		
		
		//Registering 
		UserController.getInstance().register("TestingUser@test.com", "testingpass", "Tester", "696969696");
		
		//Logging in
		UserController.getInstance().login("TestingUser@test.com", "testingpass");

		
		//Getting active events
		List<EventDTO> listEvents = EventController.getInstance().getActiveEvents();
		if (listEvents != null) {
			System.out.println("The active events are:");
			for (EventDTO t : listEvents) {
				System.out.println(t);
			}
		}
		
		//Getting Bought Tickets
		List<TicketDTO> list = TicketController.getInstance().getBoughtTickets();
		if (list != null) {
			System.out.println("The user has bought:");
			for (TicketDTO t : list) {
				System.out.println(t);
			}
		}

		
		//Logging out
		UserController.getInstance().logout();

	}

}
