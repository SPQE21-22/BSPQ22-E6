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
	public Ticket find(String... params) { // FIXME NOT WORKING!
		if (params.length != 3) {
			return null;
		}

		PersistenceManager pm3 = pmf.getPersistenceManager();
		Transaction tx3 = pm3.currentTransaction();

		Ticket t = null;

		try {

			tx3.begin();
			Query<?> query = pm3.newQuery(
					"SELECT FROM " + Ticket.class.getName() + " WHERE event_event_id_oid IN (SELECT event_id FROM "
							+ Event.class.getName() + " WHERE name == '" + params[0] + "' AND" + " dateInString == '"
							+ params[1] + "') AND ticket_id IN (SELECT ticket_id_eid FROM consumer_boughttickets"
							+ " WHERE consumer_id_oid IN (SELECT consumer_id FROM " + Consumer.class.getName()
							+ " WHERE email == '" + params[2] + "'))");

			query.setUnique(true);
			t = (Ticket) query.execute();
			tx3.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Ticket: " + ex.getMessage());
		} finally {
			if (tx3 != null && tx3.isActive()) {
				tx3.rollback();
			}

			pm3.close();
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
