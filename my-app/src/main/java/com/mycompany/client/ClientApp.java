package com.mycompany.client;

import java.time.LocalDate;
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

		// Registering

		// Deprecated method

		// UserController.getInstance().register("TestingUser@test.com", "testingpass",
		// "Tester", "696969696");

		// Registering an organizer
		UserController.getInstance().registerOrganizer("TestingOrganizer@test.com", "organizerpass", "Organizer",
				"000000000", "TestingStreet", "wwww.Testing.com");

		// Registering an consumer
		UserController.getInstance().registerConsumer("TestingConsumer@test.com", "consumerpass", "Consumer",
				"111111111", "consumerNick", "Consumerson");

		/**** TESTING THE ORGANIZER ****/

		// Logging in with the organizer
		UserController.getInstance().login("TestingOrganizer@test.com", "organizerpass");

		// Create an event
		EventController.getInstance().createEvent("testing event name", LocalDate.parse("2023-04-11"), "testing place");

		// Logging out with the organizer
		UserController.getInstance().logout();

		/**** TESTING THE CONSUMER ****/

		// Logging in with the consumer
		UserController.getInstance().login("TestingConsumer@test.com", "consumerpass");

		// Getting active events
		List<EventDTO> listEvents = EventController.getInstance().getActiveEvents();
		if (listEvents != null) {
			System.out.println("The active events are:");
			for (EventDTO t : listEvents) {
				System.out.println(t);
			}
		}

		// Buy a ticket
		EventDTO ev1 = listEvents.get(0);
		TicketController.getInstance().buyTicket(ev1.getName(), LocalDate.parse(ev1.getDate()));

		// Getting Bought Tickets
		List<TicketDTO> list = TicketController.getInstance().getBoughtTickets();
		if (list != null) {
			System.out.println("The user has bought:");
			for (TicketDTO t : list) {
				System.out.println(t);
			}
		}

		// Logging out
		UserController.getInstance().logout();

	}

}
