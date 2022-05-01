package com.mycompany.server.services;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import com.mycompany.server.data.domain.Ticket;
import com.mycompany.server.data.domain.User;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;


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
		
		//FIXME: only for testing  purposes*******************
		Organizer og = new Organizer("testingOG", "testingOGpass", "test@og.com", "945000000", "testing street", "www.test.og.com");
		Event event = new Event("TestingEvent", LocalDate.now(), "testing event place", og);
		user.getBoughtTickets().add(new Ticket(event,user));
		//****************************************************
		
		return user.getBoughtTickets();
    }
	
	public void buyTicket(User user, Event event) throws RemoteException {
	
		Ticket ticket = new Ticket(event, user);	
		user.addBoughtTicket(ticket);
		System.out.println(ticket.toString());	
		
		//TODO store the ticket in the database
    }
}
