package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.mycompany.server.data.domain.Ticket;

public class TicketDAO extends DataAccesObjectBase implements IDataAccesObject<Ticket>{
	
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
	public Ticket find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Ticket c = null;
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
