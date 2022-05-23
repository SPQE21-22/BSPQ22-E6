package com.mycompany.server.data.dataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.mycompany.server.data.domain.Event;

/** The Class EventDAO.*/
public class EventDAO extends DataAccesObjectBase implements IDataAccesObject<Event>{
	
	/** The instance. */
	private static EventDAO instance;
	
	/**
	 * Save.
	 *
	 * @param c the c
	 */
	@Override
	public void save(Event c) {
		super.saveObject(c);
	}

	/**
	 * Delete.
	 *
	 * @param c the c
	 */
	@Override
	public void delete(Event c) {
		super.deleteObject(c);
	}

	/**
	 * Find.
	 *
	 * @param params the params
	 * @return the event
	 */
	@Override
	/**
	 * Finds a consumer in the DB
	 * @param String, two parameters: 1-Name of the event, 2-Date of the event (Format: YYYY-MM-DD)
	 * @return the event object
	 */
	public Event find(String... params) {
		if(params.length != 2) {
			return null;
		}

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Event e = null;
		
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Event.class.getName() + " WHERE name == '" + params[0] + "' &&"
					+  " dateInString == '" + params[1] + "'");
			
			query.setUnique(true);
			e = (Event) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Event: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
		return e;
	}

	/**
	 * Gets the single instance of EventDAO.
	 *
	 * @return single instance of EventDAO
	 */
	public static EventDAO getInstance() {
		if (instance == null) {
			instance = new EventDAO();
		}

		return instance;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Event> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Event> events = new ArrayList<>();
		
		try {
			tx.begin();

			Extent<Event> extent = pm.getExtent(Event.class, true);

			for (Event e : extent) {
				events.add(e);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the events: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return events;
	}
	
/**
 * Gets the active events.
 *
 * @return the active events
 */
public List<Event> getActiveEvents() {
		
		//TODO: now gets ALL the challenges (getAll() copied)
		//Make it get only the active ones
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Event> events = new ArrayList<>();

		try {
			tx.begin();

			/** DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD"); */
			LocalDate today = LocalDate.now();

			Extent<Event> extent = pm.getExtent(Event.class, true);

			/** Active challenges are those that are being held at the moment */
			for (Event ev : extent) {
				/** System.out.println("* Querying active challenges."); */
				if (ev.getDate().isAfter(today)) {
					events.add(ev);
				}
			}

			if(events.size() == 0) {
				/** System.out.println("* There are no active challenges"); */
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

		return events;
	}
}
