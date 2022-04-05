package com.mycompany.server.remote;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.server.data.domain.Event;
import com.mycompany.server.services.EventAppService;

@Path("/events")
public class EventRemoteFacade {
	
	@GET
	public void getActiveEvents() {
		List<Event> listActiveEvents = EventAppService.getInstance().getActiveEvents();
		//TODO: return list of active events
	}
	
	@POST
	public void createActiveEvents() {
		/*TODO:Call EventService to add a new event with the info received from the client.
		 * REMIND to check if user attributes are okey or if this user already exists
		 * */
		EventAppService.getInstance().createEvent();
	}
}
