package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.services.EventAppService;
import com.mycompany.server.services.TicketAppService;
import com.mycompany.server.services.UserAppService;

public class RemoteFacade {

	private WebTarget webtarget = null;
	
	private static RemoteFacade instance;

	public static RemoteFacade getInstance() {
		if (instance == null) {
			instance = new RemoteFacade();
		}

		return instance;
	}

	public RemoteFacade() {
	}
	
	public void initFacade(WebTarget wt) {
		this.webtarget = wt;
	}


	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login() {
		
		long token = -1;
		User user = UserAppService.getInstance().login();
		if (user != null) { // If null user does not exist
			try {
				token = TokenManagement.getInstance().createToken(user);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return Response.ok(token).build();
	}

	@DELETE
	@Path("/users")
	public Response logout() {

		// TODO: receive real token
		long token = 0;

		try {
			TokenManagement.getInstance().removeToken(token);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	@POST
	@Path("/users")
	public Response register() {
		// TODO: receive real parameters
		UserAppService.getInstance().register();
		return Response.ok().build();
	}

	@GET
	@Path("/tickets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBoughtTickets() {
		// TODO: receive real parameters
		List<Ticket> listOfTickets = TicketAppService.getInstance().getBoughtTickets();
		// TODO: return the list
		return Response.ok(listOfTickets).build();
	}

	@POST
	@Path("/tickets")
	public Response buyTicket() {
		// TODO: receive real parameters
		TicketAppService.getInstance().buyTicket();
		return Response.ok().build();
	}

	@GET
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveEvents() {
		List<Event> listActiveEvents = EventAppService.getInstance().getActiveEvents();
		// TODO: return list of active events
		return Response.ok(listActiveEvents).build();
	}

	@POST
	@Path("/events")
	public Response createActiveEvents() {
		/*
		 * TODO:Call EventService to add a new event with the info received from the
		 * client. REMIND to check if user attributes are okey or if this user already
		 * exists
		 */
		EventAppService.getInstance().createEvent();
		return Response.ok().build();
	}

}
