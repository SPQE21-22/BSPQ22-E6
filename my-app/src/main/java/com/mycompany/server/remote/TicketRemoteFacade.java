package com.mycompany.server.remote;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.services.TicketAppService;

@Path("/tickets")
public class TicketRemoteFacade {
	@GET
	public void getBoughtTickets() {
		//TODO: receive real parameters
		List<Ticket> listOfTickets = TicketAppService.getInstance().getBoughtTickets();
		//TODO: return the list
	}
	
	@POST
	public void buyTicket() {
		//TODO: receive real parameters
		TicketAppService.getInstance().buyTicket();
	}
}
