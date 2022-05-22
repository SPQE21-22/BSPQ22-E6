package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Ticket;

public class TicketDAO extends DataAccesObjectBase implements IDataAccesObject<Ticket> {

	private static TicketDAO instance;

	@Override
	public void save(Ticket c) {
		super.saveObject(c);
	}

	@Override
	public void delete(Ticket c) {
		super.deleteObject(c);
	}

	@Override
	/**
	 * Finds a consumer in the DB
	 * 
	 * @param String, three parameters: 1-Name of the event of the ticket 2-Date of
	 *                the Event (Format: YYYY-MM-DD) 3- Email of the Consumer
	 * @return the ticket object
	 */
	public Ticket find(String... params) {
		if (params.length != 3) {
			return null;
		}

		PersistenceManager pm3 = pmf.getPersistenceManager();
		Transaction tx3 = pm3.currentTransaction();

		Ticket t = null;

		Consumer c = ConsumerDAO.getInstance().find(params[2]);
		Event e = EventDAO.getInstance().find(params[0],params[1]);
		
		if (c!=null && e!=null) {
			for (Ticket ticket: c.getBoughtTickets()) {
				if (ticket.getEvent().equals(e)) return ticket;
			}
		}

		return t;

	}

	public static TicketDAO getInstance() {
		if (instance == null) {
			instance = new TicketDAO();
		}

		return instance;
	}

	@Override
	public List<Ticket> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Ticket> tickets = new ArrayList<>();

		try {
			tx.begin();

			Extent<Ticket> extent = pm.getExtent(Ticket.class, true);

			for (Ticket el : extent) {
				tickets.add(el);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the tickets: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return tickets;
	}

	public List<Ticket> getInResellTickets() {

		// TODO: now gets ALL the challenges (getAll() copied)
		// Make it get only the active ones

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Ticket> tickets = new ArrayList<>();

		try {
			tx.begin();

			Extent<Ticket> extent = pm.getExtent(Ticket.class, true);

			// Active challenges are those that are being held at the moment
			for (Ticket t : extent) {
				// System.out.println("* Querying active challenges.");
				if (t.isInResell()) {
					tickets.add(t);
				}
			}

			if (tickets.size() == 0) {
				// System.out.println("* There are no active challenges");
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying active events: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return tickets;
	}
}
