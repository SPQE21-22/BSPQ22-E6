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

		return TestDBManager.getInstance().getUser(email,password);

		// **********************************************

		// check if the user exist
		// check if the password and the nickname are corrects

		// TODO Search for the user in the DB and if exists return it
		//return null;
	}

	public void register(String email, String password, String name, String phone) {

		// ***************MOCK METHOD TO CHECK IF WORKING
		User u = new Consumer();
		u.setEmail(email);
		u.setPassword(password);
		u.setName(name);
		u.setPhone(phone);

		TestDBManager.getInstance().storeUser(u);

		// **********************************************

		// TODO:Receive all user parameters

		// TODO: Check if parameters are valid

		// TODO:Create user
		// User u = new Organizer(); or User u = new Consumer();

		// TODO:Save user in the database

	}

	
	/**
	 * This method checks if the user given is an organizer and returns an organizer object
	 * If the user is not an organizer returns null
	 * @param user user object 
	 * @return organizer object / null if user is not an organizer
	 */
	public Organizer isOrganizer(User user) {
		Organizer org = null;
		
		if(user != null && user instanceof Organizer) {
			org = (Organizer) user;
		}
		return org;
	}

}
