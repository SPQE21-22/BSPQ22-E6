package com.mycompany.server.remote;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/events")
public class EventRemoteFacade {
	
	@GET
	public void getActiveEvents() {}
	
	@POST
	public void createActiveEvents() {
		/*TODO:Call EventService to add a new event with the info received from the client.
		 * REMIND to check if user attributes are okey or if this user already exists
		 * */
	}
}
