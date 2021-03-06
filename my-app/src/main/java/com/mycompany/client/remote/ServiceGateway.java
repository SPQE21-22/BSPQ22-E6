package com.mycompany.client.remote;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.client.ClientApp;
import com.mycompany.remote.serialization.BuyTicketDTO;
import com.mycompany.remote.serialization.ConsumerDTO;
import com.mycompany.remote.serialization.CreateEventDTO;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.OrganizerDTO;
import com.mycompany.remote.serialization.ResellTicketDTO;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.remote.serialization.UserLoginDTO;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/** The Class ServiceGateway.*/
public class ServiceGateway {
	
	/** The instance. */
	private static ServiceGateway instance;
	
	/** The base target. */
	private WebTarget baseTarget;

	/**
	 * Gets the single instance of ServiceGateway.
	 *
	 * @return single instance of ServiceGateway
	 */
	public static ServiceGateway getInstance() {
		if (instance == null) {
			instance = new ServiceGateway();
		}

		return instance;
	}

	/** Instantiates a new service gateway.*/
	private ServiceGateway() {
	}

	/**
	 * Inits the gateway.
	 *
	 * @param hostname the hostname
	 * @param port the port
	 */
	public void initGateway(String hostname, String port) {
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		Client client = ClientBuilder.newClient();
		baseTarget = client.target(BASE_URI);
		baseTarget = baseTarget.path("remote");

	}

	/**
	 * Testing server.
	 *
	 * @param name the name
	 */
	public void testingServer(String name) {
		WebTarget testTarget = baseTarget.path("test");
		WebTarget newTarget = testTarget.path(name); // This is the name that will be displayed
		Response r = newTarget.request().get();

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly done the testing");
		} else {
			ClientApp.getLogger().info("Server has problems with the testing");
		}
	}

	/**
	 * Login.
	 *
	 * @param email the email
	 * @param password the password
	 */
	public void login(String email, String password) { // TODO: Throw an exception if it fails
		WebTarget uTarget = baseTarget.path("users");
		
		Invocation.Builder i = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto = new UserLoginDTO();

		userdto.setEmail(email);
		userdto.setPassword(password);

		Response r = i.put(Entity.entity(userdto, MediaType.APPLICATION_JSON));

		/** TODO: if error throw an exception */

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly done the login");
			ClientTokenManagement.getInstance().setToken(r.readEntity(Long.class));
		} else {
			ClientApp.getLogger().info("Server has problems with the login");
		}
	}



	/** Logout.*/
	public void logout() {
		WebTarget uTarget = baseTarget.path("users");
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder i = logoutTarget.request();

		Long token = ClientTokenManagement.getInstance().getToken();

		Response r = i.put(Entity.entity(token, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly done the logout");
		} else {
			ClientApp.getLogger().info("Server has problems with the logout");
		}

	}

	/**
	 * Gets the bought tickets.
	 *
	 * @return the bought tickets
	 */
	public List<TicketDTO> getBoughtTickets() {
		List<TicketDTO> list = null;

		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget cTarget = tTarget.path("consumers");

		Invocation.Builder i = cTarget.request(MediaType.APPLICATION_JSON);

		long token = ClientTokenManagement.getInstance().getToken();

		Response r = i.put(Entity.entity(token, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly got the bought tickets");

			String listInJSON = r.readEntity(String.class);

			/** Deserializing the list */
			Gson gson = new Gson();
			Type ticketDtoListType = new TypeToken<List<TicketDTO>>() {
			}.getType();
			list = gson.fromJson(listInJSON, ticketDtoListType);

		} else {
			ClientApp.getLogger().info("Server has problems with getting the tickets");
		}

		return list;
	}

	/**
	 * Gets the active events.
	 *
	 * @return the active events
	 */
	public List<EventDTO> getActiveEvents() {
		List<EventDTO> list = null;

		WebTarget tTarget = baseTarget.path("events");
		Invocation.Builder i = tTarget.request(MediaType.APPLICATION_JSON);

		Response r = i.get();

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly got the active events");

			String listInJSON = r.readEntity(String.class);

			/** Deserializing the list */
			Gson gson = new Gson();
			Type eventDtoListType = new TypeToken<List<EventDTO>>() {
			}.getType();
			list = gson.fromJson(listInJSON, eventDtoListType);

		} else {
			ClientApp.getLogger().info("Server has problems with getting the active events");
		}

		return list;
	}

	/**
	 * Buy ticket.
	 *
	 * @param name the name
	 * @param date the date
	 */
	public void buyTicket(String name, LocalDate date) {
		WebTarget uTarget = baseTarget.path("tickets");
		WebTarget cTarget = uTarget.path("consumers");
		Invocation.Builder i = cTarget.request();

		BuyTicketDTO dto = new BuyTicketDTO();

		dto.setEventDate(date.toString());
		dto.setEventName(name);
		dto.setToken(ClientTokenManagement.getInstance().getToken());

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		/** TODO: if error throw an exception */

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly bought the ticket");
		} else {
			ClientApp.getLogger().info("Server has problems with buying the tickets");
		}

	}

	/**
	 * Creates the event.
	 *
	 * @param name the name
	 * @param date the date
	 * @param place the place
	 */
	public void createEvent(String name, LocalDate date, String place) {
		WebTarget uTarget = baseTarget.path("events");
		WebTarget oTarget = uTarget.path("organizers");

		Invocation.Builder i = oTarget.request();

		CreateEventDTO dto = new CreateEventDTO();

		dto.setOrganizerToken(ClientTokenManagement.getInstance().getToken());
		dto.setDate(date);
		dto.setName(name);
		dto.setPlace(place);

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		/** TODO: if error throw an exception*/

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly create the event");

		} else if (r.getStatus() == Status.NOT_MODIFIED.getStatusCode()) {

			ClientApp.getLogger().info("Not modified: check that the user is an organizer");

		} else {

			ClientApp.getLogger().info("Server has problems with creating the event");
		}

	}

	/**
	 * Register consumer.
	 *
	 * @param email the email
	 * @param password the password
	 * @param name the name
	 * @param phone the phone
	 * @param nickname the nickname
	 * @param surname the surname
	 */
	public void registerConsumer(String email, String password, String name, String phone, String nickname,
			String surname) {
		WebTarget uTarget = baseTarget.path("users");
		WebTarget cTarget = uTarget.path("consumers");
		
		Invocation.Builder i = cTarget.request();

		ConsumerDTO dto = new ConsumerDTO();

		dto.setEmail(email);
		dto.setPassword(password);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setNickname(nickname);
		dto.setSurname(surname);

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly registered an consumer");
		} else {
			ClientApp.getLogger().info("Server has problems with registering an consumer");
		}
		
	}

	/**
	 * Register organizer.
	 *
	 * @param email the email
	 * @param password the password
	 * @param name the name
	 * @param phone the phone
	 * @param address the address
	 * @param webpage the webpage
	 */
	public void registerOrganizer(String email, String password, String name, String phone, String address,
			String webpage) {
		WebTarget uTarget = baseTarget.path("users");
		WebTarget cTarget = uTarget.path("organizers");
		
		Invocation.Builder i = cTarget.request();

		OrganizerDTO dto = new OrganizerDTO();

		dto.setEmail(email);
		dto.setPassword(password);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setAddress(address);
		dto.setWebpage(webpage);

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly registered an organizer");
		} else {
			ClientApp.getLogger().info("Server has problems with registering an organizer");
		}
		
	}

	/**
	 * Put ticket in resell.
	 *
	 * @param ticketUserEmail the ticket user email
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 */
	public void putTicketInResell(String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {
		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");
		
		Invocation.Builder i = rTarget.request();

		ResellTicketDTO dto = new ResellTicketDTO();

		dto.setTicketUserEmail(ticketUserEmail);
		dto.setTicketEventName(ticketEventName);
		dto.setTicketEventDate(ticketEventDate);
		dto.setToken(ClientTokenManagement.getInstance().getToken());

		Response r = i.put(Entity.entity(dto, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly put in resell a ticket");
		} else {
			ClientApp.getLogger().info("Server has problems with reselling a ticket");
		}
		
	}
	
	/**
	 * Buy reselling ticket.
	 *
	 * @param ticketUserEmail the ticket user email
	 * @param ticketEventName the ticket event name
	 * @param ticketEventDate the ticket event date
	 */
	public void buyResellingTicket(String ticketUserEmail, String ticketEventName, LocalDate ticketEventDate) {
		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");
		
		Invocation.Builder i = rTarget.request();

		ResellTicketDTO dto = new ResellTicketDTO();

		dto.setTicketUserEmail(ticketUserEmail);
		dto.setTicketEventName(ticketEventName);
		dto.setTicketEventDate(ticketEventDate);
		dto.setToken(ClientTokenManagement.getInstance().getToken());

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly buy a reselled a ticket");
		} else {
			ClientApp.getLogger().info("Server has problems with buying reselled tickets");
		}
		
	}

	/**
	 * Gets the reselling tickets.
	 *
	 * @return the reselling tickets
	 */
	public List<TicketDTO> getResellingTickets() {
		List<TicketDTO> list = null;

		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");
		Invocation.Builder i = rTarget.request(MediaType.APPLICATION_JSON);

		Response r = i.get();

		if (r.getStatus() == Status.OK.getStatusCode()) {
			ClientApp.getLogger().info("Server has correctly got the reselling tickets");

			String listInJSON = r.readEntity(String.class);

			/** Deserializing the list */
			Gson gson = new Gson();
			Type ticketDtoListType = new TypeToken<List<TicketDTO>>() {
			}.getType();
			list = gson.fromJson(listInJSON, ticketDtoListType);

		} else {
			ClientApp.getLogger().info("Server has problems with getting the reselling tickets");
		}

		return list;
	}

}
