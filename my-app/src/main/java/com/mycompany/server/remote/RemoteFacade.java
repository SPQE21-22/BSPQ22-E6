package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mycompany.remote.serialization.ResellTicketDTO;
import com.mycompany.remote.serialization.BuyTicketDTO;
import com.mycompany.remote.serialization.ConsumerDTO;
import com.mycompany.remote.serialization.CreateEventDTO;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.OrganizerDTO;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.remote.serialization.UserLoginDTO;
import com.mycompany.server.ServerApp;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.services.EventAppService;
import com.mycompany.server.services.TicketAppService;
import com.mycompany.server.services.UserAppService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** The Class RemoteFacade*/
@Path("/remote")
public class RemoteFacade {

	/** The instance. */
	private static RemoteFacade instance;

	/**
	 * Gets the single instance of RemoteFacade.
	 *
	 * @return single instance of RemoteFacade
	 */
	public static RemoteFacade getInstance() {
		if (instance == null) {
			instance = new RemoteFacade();
		}

		return instance;
	}

	/** Instantiates a new remote facade. */
	public RemoteFacade() {
	}

	/**
	 * Login.
	 *
	 * @param userlogindto the userlogindto
	 * @return the response
	 */
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserLoginDTO userlogindto) {

		String username = userlogindto.getEmail();
		String password = userlogindto.getPassword();

		long token = -1;
		User user = UserAppService.getInstance().login(username, password);
		if (user != null) { /** If null user does not exist */
			try {
				token = TokenManagement.getInstance().createToken(user);
				ServerApp.getLogger().info("Login of: " + username + " - " + password + ". [" + token + "]");
			} catch (RemoteException e) {

				/** e.printStackTrace(); */
				ServerApp.getLogger().error("Remote Exception occurred in the login", e);
				return Response.serverError().build();
			}
		}

		return Response.ok(token).build();
	}

	/**
	 * Logout.
	 *
	 * @param token the token
	 * @return the response
	 */
	@PUT
	@Path("/users/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(long token) {

		try {
			TokenManagement.getInstance().removeToken(token);
			ServerApp.getLogger().info("Logout of user with token: " + token);

		} catch (RemoteException e) {

			ServerApp.getLogger().error("Remote Exception occurred in the logout", e);
			return Response.notModified(Long.toString(token)).build();
		}
		return Response.ok().build();

	}

	/**
	 * Register consumer.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@POST
	@Path("/users/consumers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerConsumer(ConsumerDTO dto) {
		ServerApp.getLogger().info("Registering consumer: " + dto.toString());
		UserAppService.getInstance().registerConsumer(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getPhone(),
				dto.getNickname(), dto.getSurname());
		return Response.ok().build();
	}

	/**
	 * Register organizer.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@POST
	@Path("/users/organizers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerOrganizer(OrganizerDTO dto) {
		ServerApp.getLogger().info("Registering organizer: " + dto.toString());
		UserAppService.getInstance().registerOrganizer(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getPhone(),
				dto.getAddress(), dto.getWebpage());
		return Response.ok().build();
	}

	/**
	 * Gets the bought tickets.
	 *
	 * @param token the token
	 * @return the bought tickets
	 */
	@PUT
	@Path("/tickets/consumers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBoughtTickets(long token) {
		ServerApp.getLogger().info("Getting tickets for: " + token);
		User user = null;
		List<TicketDTO> listOfTicketsDTO = new ArrayList<>();
		try {
			TokenManagement tokenManager = TokenManagement.getInstance();
			user = tokenManager.checkToken(token);

			if (user != null) {

				Consumer con = UserAppService.getInstance().isConsumer(user);
				if (con != null) {
					List<Ticket> listTickets = TicketAppService.getInstance().getBoughtTickets(con);
					for (Ticket t : listTickets) {
						TicketDTO dto = new TicketDTO();
						dto.setUserEmail(t.getOwner().getEmail());
						dto.setEventName(t.getEvent().getName());
						dto.setEventDate(t.getEvent().getDate());
						dto.setPlace(t.getEvent().getPlace());
						dto.setInResell(t.isInResell());
						listOfTicketsDTO.add(dto);
					}
				} else {
					return Response.notModified().build();
				}

			}

		} catch (RemoteException e) {

			ServerApp.getLogger().error("Remote Exception occurred in getting bought tickets", e);
			return Response.serverError().build();
		}

		Gson gson = new Gson();/** converts the list to a json */
		return Response.ok().entity(gson.toJson(listOfTicketsDTO)).build();

	}

	/**
	 * Buy ticket.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@POST
	@Path("/tickets/consumers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buyTicket(BuyTicketDTO dto) {
		TokenManagement tokenManager = TokenManagement.getInstance();
		try {
			User user = tokenManager.checkToken(dto.getToken());
			if (user != null) {
				Consumer con = UserAppService.getInstance().isConsumer(user);
				if (con != null) {
					TicketAppService.getInstance().buyTicket(con, dto.getEventName(),
							LocalDate.parse(dto.getEventDate()));

				} else {
					return Response.notModified().build();
				}
			}
		} catch (RemoteException e) {
			ServerApp.getLogger().error("Remote Exception occurred in buying a ticket", e);
			return Response.serverError().build();
		}

		return Response.ok().build();
	}

	/**
	 * Gets the active events.
	 *
	 * @return the active events
	 */
	@GET
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveEvents() {

		ServerApp.getLogger().info("Getting active events");

		List<EventDTO> listOfEventDTO = new ArrayList<>();
		try {

			List<Event> listActiveEvents = EventAppService.getInstance().getActiveEvents();

			for (Event e : listActiveEvents) {
				EventDTO dto = new EventDTO();
				dto.setName(e.getName());
				dto.setDate(e.getDate());
				dto.setPlace(e.getPlace());
				dto.setOrganizerEmail(e.getOrganizer().getEmail());
				dto.setOrganizerWeb(e.getOrganizer().getWebpage());
				listOfEventDTO.add(dto);
			}

		} catch (Exception e) {
			ServerApp.getLogger().error("Exception occurred in getting active events", e);
			return Response.serverError().build();
		}

		Gson gson = new Gson();/** converts the list to a json*/
		return Response.ok().entity(gson.toJson(listOfEventDTO)).build();
	}

	/**
	 * Creates the event.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@POST
	@Path("/events/organizers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(CreateEventDTO dto) {

		/** REMIND to check if user attributes are okey or if this user already exists */
		ServerApp.getLogger().info("Creating an event: " + dto.getName() + " by [" + dto.getOrganizerToken() + "]");

		TokenManagement tokenManager = TokenManagement.getInstance();
		try {
			/** Only organizers can create an event */
			User user = tokenManager.checkToken(dto.getOrganizerToken());
			if (user != null) {

				/** Check if user is organizer */
				Organizer org = UserAppService.getInstance().isOrganizer(user);
				if (org != null) {

					EventAppService.getInstance().createEvent(dto.getName(), LocalDate.parse(dto.getDate()),
							dto.getPlace(), org);
				} else {
					return Response.notModified().build();
				}
			}
		} catch (RemoteException e) {
			ServerApp.getLogger().error("Remote Exception occurred in creating the event", e);
			return Response.serverError().build();
		}

		return Response.ok().build();

	}

	/**
	 * Put ticket in resell.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@PUT
	@Path("/tickets/resell")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putTicketInResell(ResellTicketDTO dto) {

		ServerApp.getLogger().info("Resell Ticket: " + dto.toString());

		TokenManagement tokenManager = TokenManagement.getInstance();
		try {

			User user = tokenManager.checkToken(dto.getToken());
			if (user != null) {

				/** Check if user is consumer */
				Consumer c = UserAppService.getInstance().isConsumer(user);
				if (c != null) {
					if (!TicketAppService.getInstance().putTicketInResell(c, dto.getTicketUserEmail(),
							dto.getTicketEventName(), LocalDate.parse(dto.getTicketEventDate()))) {
						return Response.notModified().build();
					}
				} else {
					return Response.notModified().build();
				}
			}
		} catch (RemoteException e) {
			ServerApp.getLogger().error("Remote Exception occurred in reselling a ticket", e);
			return Response.serverError().build();
		}

		return Response.ok().build();
	}

	/**
	 * Buy reselling ticket.
	 *
	 * @param dto the dto
	 * @return the response
	 */
	@POST
	@Path("/tickets/resell")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buyResellingTicket(ResellTicketDTO dto) {

		ServerApp.getLogger().info("Buy Resell Ticket: " + dto.toString());

		TokenManagement tokenManager = TokenManagement.getInstance();
		try {

			User user = tokenManager.checkToken(dto.getToken());
			if (user != null) {

				/** Check if user is consumer*/
				Consumer c = UserAppService.getInstance().isConsumer(user);
				if (c != null) {
					if (!TicketAppService.getInstance().buyResellingTicket(c, dto.getTicketUserEmail(),
							dto.getTicketEventName(), LocalDate.parse(dto.getTicketEventDate()))) {
						return Response.notModified().build();
					}
				} else {
					return Response.notModified().build();
				}
			}
		} catch (RemoteException e) {
			ServerApp.getLogger().error("Remote Exception occurred in reselling a ticket", e);
			return Response.serverError().build();
		}

		return Response.ok().build();
	}

	/**
	 * Gets the reselling tickets.
	 *
	 * @return the reselling tickets
	 */
	@GET
	@Path("/tickets/resell")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResellingTickets() {

		ServerApp.getLogger().info("Getting reselling tickets");
		List<TicketDTO> listOfTicketsDTO = new ArrayList<>();
		
		try {
			List<Ticket> listTickets = TicketAppService.getInstance().getResellingTickets();
			for (Ticket t : listTickets) {
				TicketDTO dto = new TicketDTO();
				dto.setUserEmail(t.getOwner().getEmail());
				dto.setEventName(t.getEvent().getName());
				dto.setEventDate(t.getEvent().getDate());
				dto.setPlace(t.getEvent().getPlace());
				dto.setInResell(t.isInResell());
				listOfTicketsDTO.add(dto);
			}
		} catch (Exception e) {
			ServerApp.getLogger().error("Remote Exception occurred in reselling a ticket", e);
			return Response.serverError().build();
		}

		Gson gson = new Gson();/** converts the list to a json */
		return Response.ok().entity(gson.toJson(listOfTicketsDTO)).build();
	}

	/**
	 * Testing connection.
	 *
	 * @param name the name
	 * @return the response
	 */
	@GET
	@Path("/test/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testingConnection(@PathParam("name") String name) {
		ServerApp.getLogger().info(name + " has tried to use the connection");
		if (name.length() >= 1) {
			ServerApp.getLogger().info(name + "entered the method!");
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
