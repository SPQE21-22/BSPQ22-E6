package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.services.EventAppService;
import com.mycompany.server.services.TicketAppService;
import com.mycompany.server.services.UserAppService;

public class RemoteFacade {

	private WebTarget webtarget;
	
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
	public void login() {

		User user = UserAppService.getInstance().login();
		if (user != null) { // If null user does not exist
			try {
				long token = TokenManagement.getInstance().createToken(user);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO: return token
	}

	@DELETE
	@Path("/users")
	public void logout() {

		// TODO: receive real token
		long token = 0;

		try {
			TokenManagement.getInstance().removeToken(token);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@POST
	@Path("/users")
	public void register() {
		// TODO: receive real parameters
		UserAppService.getInstance().register();
	}

	@GET
	@Path("/tickets")
	public void getBoughtTickets() {
		// TODO: receive real parameters
		List<Ticket> listOfTickets = TicketAppService.getInstance().getBoughtTickets();
		// TODO: return the list
	}

	@POST
	@Path("/tickets")
	public void buyTicket() {
		// TODO: receive real parameters
		TicketAppService.getInstance().buyTicket();
	}

	@GET
	@Path("/events")
	public void getActiveEvents() {
		List<Event> listActiveEvents = EventAppService.getInstance().getActiveEvents();
		// TODO: return list of active events
	}

	@POST
	@Path("/events")
	public void createActiveEvents() {
		/*
		 * TODO:Call EventService to add a new event with the info received from the
		 * client. REMIND to check if user attributes are okey or if this user already
		 * exists
		 */
		EventAppService.getInstance().createEvent();
	}

}
