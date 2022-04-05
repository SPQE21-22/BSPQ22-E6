package com.mycompany.server.services;

import java.util.List;

import com.mycompany.server.data.domain.Ticket;

public class TicketAppService {
	
	private static TicketAppService instance;
	
	public static TicketAppService getInstance() {
		if (instance == null) {
			instance = new TicketAppService();
		}

		return instance;
	}
	
	private TicketAppService() {}
    
	public List<Ticket> getBoughtTickets() {
		/*TODO get list with all the tickets 
	        */
		return null;
        
    }
	
	public void buyTicket() {
        /*TODO create a new ticket
        */
    }
}
