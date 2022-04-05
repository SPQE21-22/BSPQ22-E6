package com.mycompany.server.services;

import java.rmi.RemoteException;
import java.util.List;

import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.remote.TokenManagement;


public class TicketAppService {
	
	private static TicketAppService instance;
	
	public static TicketAppService getInstance() {
		if (instance == null) {
			instance = new TicketAppService();
		}

		return instance;
	}
	
	private TicketAppService() {}
    
	public List<Ticket> getBoughtTickets(User user) {
		return user.getBoughtTickets();
    }
	
	public void buyTicket(User user, Event event) throws RemoteException {
	
		Ticket ticket = new Ticket(event, user);	
		user.addBoughtTicket(ticket);
		System.out.println(ticket.toString());	
		
		//TODO store the ticket in the database
    }
}
