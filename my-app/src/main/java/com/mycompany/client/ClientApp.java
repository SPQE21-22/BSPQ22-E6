package com.mycompany.client;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.mycompany.client.controller.EventController;
import com.mycompany.client.controller.TicketController;
import com.mycompany.client.controller.UserController;
import com.mycompany.client.remote.ServiceGateway;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.TicketDTO;

public class ClientApp {

	private static final Logger logger = Logger.getLogger("ClientApp");

	public static void main(String[] args) {

		BasicConfigurator.configure(); // loads the log4j properties

		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
		ClientApp.getLogger().info("This is the client side");

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

		// Registering another consumer
		UserController.getInstance().registerConsumer("TestingConsumer22@test.com", "consumerpass22", "Consumer22",
				"222222222", "consumerNick22", "Consumerson22");

		// Logging in with the consumer
		UserController.getInstance().login("TestingConsumer@test.com", "consumerpass");

		// Getting active events
		List<EventDTO> listEvents = EventController.getInstance().getActiveEvents();
		if (listEvents != null) {
			ClientApp.getLogger().info("The active events are:");
			for (EventDTO t : listEvents) {
				ClientApp.getLogger().info(t);
			}
		}

		// Buy a ticket
		EventDTO ev1 = listEvents.get(0);
		TicketController.getInstance().buyTicket(ev1.getName(), LocalDate.parse(ev1.getDate()));

		// Getting Bought Tickets
		List<TicketDTO> list = TicketController.getInstance().getBoughtTickets();
		if (list != null) {
			ClientApp.getLogger().info("The user has bought:");
			for (TicketDTO t : list) {
				ClientApp.getLogger().info(t);
			}
		}

		// Reselling ticket
		TicketDTO toResellTicket = list.get(0);
		TicketController.getInstance().putTicketInResell(toResellTicket.getUserEmail(), toResellTicket.getEventName(),
				LocalDate.parse(toResellTicket.getEventDate()));

		// Logging out
		UserController.getInstance().logout();

		// Logging in with the other consumer
		UserController.getInstance().login("TestingConsumer22@test.com", "consumerpass22");
		
		//Get the reselling tickets
		List<TicketDTO> resellingTickets = TicketController.getInstance().getResellingTickets();
		
		
		//Buy a reselling  ticket
		if (resellingTickets != null && !resellingTickets.isEmpty()) {
			TicketDTO resellingT = resellingTickets.get(0);
			// Buy a resellingTicket
			TicketController.getInstance().buyResellingTicket(resellingT.getUserEmail(), resellingT.getEventName(),
					LocalDate.parse(resellingT.getEventDate()));
		}
		
		
		// Getting Bought Tickets
		List<TicketDTO> list2 = TicketController.getInstance().getBoughtTickets();
		if (list2 != null) {
			ClientApp.getLogger().info("The user has bought:");
			for (TicketDTO t : list2) {
				ClientApp.getLogger().info(t);
			}
		}

		// Logging out
		UserController.getInstance().logout();

	}

	public static Logger getLogger() {
		return logger;
	}

}
