package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.mycompany.server.data.domain.Organizer;

public class OrganizerDAO extends DataAccesObjectBase implements IDataAccesObject<Organizer>{
	
	private static OrganizerDAO instance;
	
	@Override
	public void save(Organizer c) {
		super.saveObject(c);
	}

	@Override
	public void delete(Organizer c) {
		super.deleteObject(c);
	}

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

	public static OrganizerDAO getInstance() {
		if (instance == null) {
			instance = new OrganizerDAO();
		}

		return instance;
	}

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
