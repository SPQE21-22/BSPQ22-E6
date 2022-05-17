package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

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
	public Ticket find(String... params) { //FIXME NOT WORKING!
		if (params.length != 3) {
			return null;
		}

		PersistenceManager pm3 = pmf.getPersistenceManager();
		Transaction tx3 = pm3.currentTransaction();

		Ticket t = null;

		try {

			tx3.begin();
			Query<?> query = pm3.newQuery("SELECT FROM " + Ticket.class.getName()
					+ " WHERE event_event_id_oid IN (SELECT event_id FROM " + Event.class.getName() + " WHERE name == '"
					+ params[0] + "' AND" + " dateInString == '" + params[1] + "') AND ticket_id IN (SELECT ticket_id_eid FROM consumer_boughttickets"
							+ " WHERE consumer_id_oid IN (SELECT consumer_id FROM "+ Consumer.class.getName() + " WHERE email == '" +params[2] + "'))");
		

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

		List<Ticket> users = new ArrayList<>();
		/*
		 * try { tx.begin();
		 * 
		 * Extent<User> extent = pm.getExtent(User.class, true);
		 * 
		 * for (User el : extent) { users.add(el); }
		 * 
		 * tx.commit(); } catch (Exception ex) {
		 * System.out.println("  $ Error retrieving all the users: " + ex.getMessage());
		 * } finally { if (tx != null && tx.isActive()) { tx.rollback(); }
		 * 
		 * pm.close(); }
		 */
		return users;
	}
}
