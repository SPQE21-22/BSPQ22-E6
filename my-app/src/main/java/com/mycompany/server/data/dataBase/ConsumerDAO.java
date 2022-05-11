package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.mycompany.server.data.domain.Consumer;

public class ConsumerDAO extends DataAccesObjectBase implements IDataAccesObject<Consumer>{
	
	private static ConsumerDAO instance;
	
	@Override
	public void save(Consumer c) {
		super.saveObject(c);
	}

	@Override
	public void delete(Consumer c) {
		super.deleteObject(c);
	}

	@Override
	public Consumer find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Consumer c = null;
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

	public static ConsumerDAO getInstance() {
		if (instance == null) {
			instance = new ConsumerDAO();
		}

		return instance;
	}

	@Override
	public List<Consumer> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Consumer> users = new ArrayList<>();
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
