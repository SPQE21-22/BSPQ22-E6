package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.mycompany.server.ServerApp;
import com.mycompany.server.data.domain.Consumer;

/** The Class ConsumerDAO.*/
public class ConsumerDAO extends DataAccesObjectBase implements IDataAccesObject<Consumer>{
	
	/** The instance. */
	private static ConsumerDAO instance;
	
	/**
	 * Save.
	 *
	 * @param c the c
	 */
	@Override
	public void save(Consumer c) {
		super.saveObject(c);
	}

	/**
	 * Delete.
	 *
	 * @param c the c
	 */
	@Override
	public void delete(Consumer c) {
		super.deleteObject(c);
	}
	
	
	
	/**
	 * Find.
	 *
	 * @param params the params
	 * @return the consumer
	 */
	@Override
	/**
	 * Finds a consumer in the DB
	 * @param String, only one parameter with the email of the consumer
	 * @return the consumer object
	 */
	public Consumer find(String... params) {
		if(params.length != 1) {
			return null;
		}
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Consumer c = null;
		
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Consumer.class.getName() + " WHERE email == '" + params[0] + "'");
			query.setUnique(true);
			c = (Consumer) query.execute();

			tx.commit();
		} catch (Exception ex) {
			ServerApp.getLogger().error("  $ Error querying a Consumer: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
		return c;
	}
	
	/**
	 * Finds a consumer in the DB.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the consumer object
	 */
	public Consumer findLogin(String email, String password) {

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Consumer c = null;
		
		try {
			
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Consumer.class.getName() + " WHERE email == '" + email + "' AND password == '"+password+"'");
			query.setUnique(true);
			c = (Consumer) query.execute();

			tx.commit();
		} catch (Exception ex) {
			ServerApp.getLogger().error("  $ Error querying a Consumer: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
		return c;
	}

	/**
	 * Gets the single instance of ConsumerDAO.
	 *
	 * @return single instance of ConsumerDAO
	 */
	public static ConsumerDAO getInstance() {
		if (instance == null) {
			instance = new ConsumerDAO();
		}

		return instance;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Consumer> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Consumer> users = new ArrayList<>();
		
		try {
			
			tx.begin();
			Extent<Consumer> extent = pm.getExtent(Consumer.class, true);
			for (Consumer c : extent) {
				users.add(c);
			}
			tx.commit();
			
		} catch (Exception ex) {
			ServerApp.getLogger().error("  $ Error retrieving all the consumers: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
		return users;
	}
}
