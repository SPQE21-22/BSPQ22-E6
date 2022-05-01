package com.mycompany.server.services;

import com.mycompany.server.data.dataBase.TestDBManager;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.User;

public class UserAppService {

	private static UserAppService instance;

	public static UserAppService getInstance() {
		if (instance == null) {
			instance = new UserAppService();
		}

		return instance;
	}

	private UserAppService() {
	}

	public User login(String email, String password) {

		// ***************MOCK METHOD TO CHECK IF WORKING

		return TestDBManager.getInstance().getUser(email, password);

		// **********************************************

		// check if the user exist
		// check if the password and the nickname are corrects

		// TODO Search for the user in the DB and if exists return it
		// return null;
	}



	/**
	 * This method checks if the user given is an organizer and returns an organizer
	 * object If the user is not an organizer returns null
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
	 * object If the user is not an consumer returns null
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

	public void registerConsumer(String email, String password, String name, String phone, String nickname,
			String surname) {

		// ***************MOCK METHOD TO CHECK IF WORKING
		Consumer c = new Consumer();
		c.setEmail(email);
		c.setPassword(password);
		c.setName(name);
		c.setPhone(phone);
		c.setNickname(nickname);
		c.setSurname(surname);

		TestDBManager.getInstance().storeUser(c);

		// **********************************************

		// TODO:Receive all user parameters

		// TODO: Check if parameters are valid

		// TODO:Create user
		// User u = new Organizer(); or User u = new Consumer();

		// TODO:Save user in the database

	}

	public void registerOrganizer(String email, String password, String name, String phone, String address,
			String webpage) {

		// ***************MOCK METHOD TO CHECK IF WORKING
		Organizer o = new Organizer();
		o.setEmail(email);
		o.setPassword(password);
		o.setName(name);
		o.setPhone(phone);
		o.setAddress(address);
		o.setWebpage(webpage);

		TestDBManager.getInstance().storeUser(o);

		// **********************************************

		// TODO:Receive all user parameters

		// TODO: Check if parameters are valid

		// TODO:Create user
		// User u = new Organizer(); or User u = new Consumer();

		// TODO:Save user in the database

	}

}
