package com.mycompany.server.remote;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tickets")
public class TicketRemoteFacade {
	@GET
	public void getBoughtTickets() {}
	
	@POST
	public void buyTicket() {}
}
