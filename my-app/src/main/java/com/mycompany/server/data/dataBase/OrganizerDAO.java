package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.mycompany.server.ServerApp;
import com.mycompany.server.data.domain.Organizer;

/** The Class OrganizerDAO. */
public class OrganizerDAO extends DataAccesObjectBase implements IDataAccesObject<Organizer>{
	
	/** The instance. */
	private static OrganizerDAO instance;
	
	/**
	 * Save.
	 *
	 * @param c the c
	 */
	@Override
	public void save(Organizer c) {
		super.saveObject(c);
	}

	/**
	 * Delete.
	 *
	 * @param c the c
	 */
	@Override
	public void delete(Organizer c) {
		super.deleteObject(c);
	}

	/**
	 * Find.
	 *
	 * @param params the params
	 * @return the organizer
	 */
	@Override
	/**
	 * Finds a consumer in the DB
	 * @param String, only one parameter with the email of the consumer
	 * @return the organizer object
	 */
	public Organizer find(String... params) {
		if(params.length != 1) {
			return null;
		}
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Organizer o = null;
		
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Organizer.class.getName() + " WHERE email == '" + params[0] + "'");
			query.setUnique(true);
			o = (Organizer) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Organizer: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
		return o;
	}
	
	/**
	 * Finds a organizer in the DB.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the organizer object
	 */
	public Organizer findLogin(String email, String password) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Organizer o = null;
		
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Organizer.class.getName() + " WHERE email == '" + email + "' AND password == '"+password+"'");
			query.setUnique(true);
			o = (Organizer) query.execute();

			tx.commit();
		} catch (Exception ex) {
			ServerApp.getLogger().error("  $ Error querying a Organizer: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
		return o;
	}

	/**
	 * Gets the single instance of OrganizerDAO.
	 *
	 * @return single instance of OrganizerDAO
	 */
	public static OrganizerDAO getInstance() {
		if (instance == null) {
			instance = new OrganizerDAO();
		}

		return instance;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Organizer> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Organizer> organizers = new ArrayList<>();
		
		try {
			tx.begin();

			Extent<Organizer> extent = pm.getExtent(Organizer.class, true);

			for (Organizer o : extent) {
				organizers.add(o);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the organizers: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		return organizers;
	}
}
