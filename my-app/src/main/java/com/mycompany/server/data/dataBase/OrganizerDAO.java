package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
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
	public Organizer find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Organizer c = null;
		/*
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + param + "'");
			query.setUnique(true);
			u = (User) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	*/
		return c;
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

		List<Organizer> users = new ArrayList<>();
		/*
		try {
			tx.begin();

			Extent<User> extent = pm.getExtent(User.class, true);

			for (User el : extent) {
				users.add(el);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		*/
		return users;
	}
}
