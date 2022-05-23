package com.mycompany.server.services;

import com.mycompany.server.data.dataBase.ConsumerDAO;
import com.mycompany.server.data.dataBase.OrganizerDAO;
import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.User;

/** The Class UserAppService.*/
public class UserAppService {

	/** The instance. */
	private static UserAppService instance;

	/**
	 * Gets the single instance of UserAppService.
	 *
	 * @return single instance of UserAppService
	 */
	public static UserAppService getInstance() {
		if (instance == null) {
			instance = new UserAppService();
		}

		return instance;
	}

	/** Instantiates a new user app service.*/
	private UserAppService() {
	}

	/**
	 * Login.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user
	 */
	public User login(String email, String password) {
		
		User u = null;
		
		Consumer c = ConsumerDAO.getInstance().findLogin(email,password);
		Organizer o = OrganizerDAO.getInstance().findLogin(email,password);
		
		if (c!=null) return c;
		if (o!=null) return o;
		
		return u;
	}



	/**
	 * This method checks if the user given is an organizer and returns an organizer
	 * object If the user is not an organizer returns null.
	 *
	 * @param user user object
	 * @return organizer object / null if user is not an organizer
	 */
	public Organizer isOrganizer(User user) {
		Organizer org = null;

		if (user != null && user instanceof Organizer) {
			org = (Organizer) user;
		}
		return org;
	}

	/**
	 * This method checks if the user given is an consumer and returns an consumer
	 * object If the user is not an consumer returns null.
	 *
	 * @param user user object
	 * @return consumer object / null if user is not an consumer
	 */
	public Consumer isConsumer(User user) {
		Consumer con = null;

		if (user != null && user instanceof Consumer) {
			con = (Consumer) user;
		}
		return con;
	}

	/**
	 * Register consumer.
	 *
	 * @param email the email
	 * @param password the password
	 * @param name the name
	 * @param phone the phone
	 * @param nickname the nickname
	 * @param surname the surname
	 */
	public void registerConsumer(String email, String password, String name, String phone, String nickname,
			String surname) {

		Consumer c = new Consumer();
		c.setEmail(email);
		c.setPassword(password);
		c.setName(name);
		c.setPhone(phone);
		c.setNickname(nickname);
		c.setSurname(surname);

		ConsumerDAO.getInstance().save(c);

	}

	/**
	 * Register organizer.
	 *
	 * @param email the email
	 * @param password the password
	 * @param name the name
	 * @param phone the phone
	 * @param address the address
	 * @param webpage the webpage
	 */
	public void registerOrganizer(String email, String password, String name, String phone, String address,
			String webpage) {

		Organizer o = new Organizer();
		o.setEmail(email);
		o.setPassword(password);
		o.setName(name);
		o.setPhone(phone);
		o.setAddress(address);
		o.setWebpage(webpage);

		OrganizerDAO.getInstance().save(o);

	}

}
