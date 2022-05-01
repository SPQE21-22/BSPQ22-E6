package com.mycompany.server.remote;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.mycompany.remote.serialization.UserDTO;
import com.mycompany.remote.serialization.UserLoginDTO;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.services.EventAppService;
import com.mycompany.server.services.TicketAppService;
import com.mycompany.server.services.UserAppService;

@Path("/remote")
public class RemoteFacade {
	
	private static RemoteFacade instance;
	private final Logger logger = Logger.getLogger("RemoteFacade");

	public static RemoteFacade getInstance() {
		if (instance == null) {
			instance = new RemoteFacade();
		}

		return instance;
	}

	public RemoteFacade() {
	}
	


	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserLoginDTO userlogindto) {
		
		String username = userlogindto.getEmail();
		String password = userlogindto.getPassword();
		
		
		
		long token = -1;
		User user = UserAppService.getInstance().login(username, password);
		if (user != null) { // If null user does not exist
			try {
				token = TokenManagement.getInstance().createToken(user);
				System.out.println("Login of: "+username+" - "+password+". ["+token+"]");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.serverError().build();
			}
		}

		return Response.ok(token).build();
	}
	
	
	@PUT
	@Path("/users/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(long token) {
		
		try {
			TokenManagement.getInstance().removeToken(token);
			System.out.println("Logout of user with token: "+token);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.notModified(Long.toString(token)).build();
		}
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(UserDTO userdto) {
		System.out.println("Registering user: "+userdto.toString());
		UserAppService.getInstance().register(userdto.getEmail(),userdto.getPassword(),userdto.getName(),userdto.getPhone());
		return Response.ok().build();
	}
	/*
	@GET
	@Path("/tickets")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getBoughtTickets(long token) {
		TokenManagement tokenManager = TokenManagement.getInstance();

		User user;
		List<Ticket> listOfTickets = null;
		try {
			user = tokenManager.checkToken(token);
			listOfTickets = TicketAppService.getInstance().getBoughtTickets(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return Response.ok(listOfTickets).build();
	}

	@POST
	@Path("/tickets")
	@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
	public Response buyTicket(long token, Event event) {
		TokenManagement tokenManager = TokenManagement.getInstance();
		try {
			User user = tokenManager.checkToken(token);	
			TicketAppService.getInstance().buyTicket(user, event);
		} catch (RemoteException e) {
			e.printStackTrace();
			//TODO: handle exception
		}
		
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
		//
		 // TODO:Call EventService to add a new event with the info received from the
		 // client. REMIND to check if user attributes are okey or if this user already
		 // exists
		 
		EventAppService.getInstance().createEvent();
		return Response.ok().build();
	}
	*/
	@GET
	@Path("/test/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response testingConnection(@PathParam("name") String name) {
		logger.info(name + " has tried to use the connection");
		if (name.length() >= 1) {
            System.out.println("Hello "+name+"!");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
	}

}
