package com.mycompany.client.controller;

import com.mycompany.client.ClientApp;
import com.mycompany.client.remote.ServiceGateway;

/**
 * The Class UserController.
 */
public class UserController {

	/** The instance. */
	private static UserController instance;

	/**
	 * Gets the single instance of UserController.
	 *
	 * @return single instance of UserController
	 */
	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}

		return instance;
	}

	/**
	 * Instantiates a new user controller.
	 */
	private UserController() {
	}

	/**
	 * Login.
	 *
	 * @param email the email
	 * @param password the password
	 */
	public void login(String email, String password) {
		
		try {
			ServiceGateway.getInstance().login(email, password);
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}
	}
	
	/**
	 * Register.
	 *
	 * @param email the email
	 * @param password the password
	 * @param name the name
	 * @param phone the phone
	 */
	@Deprecated
	/**
	 * Deprecated method that was used before differencing the two register types: consumer and organizer
	 * PLEASE, do not use, change it to consumer or organizer in each case
	 * @param email
	 * @param password
	 * @param name
	 * @param phone
	 */
	public void register(String email, String password, String name, String phone) {
		try {
			ServiceGateway.getInstance().registerConsumer(email, password,name,phone, "", "");
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

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
	public void registerConsumer(String email, String password, String name, String phone, String nickname, String surname) {
		try {
			ServiceGateway.getInstance().registerConsumer(email, password,name,phone, nickname, surname);
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

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
	public void registerOrganizer(String email, String password, String name, String phone, String address, String webpage ) {
		try {
			ServiceGateway.getInstance().registerOrganizer(email, password,name,phone, address, webpage);
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

	}
	
	
	
	/**
	 * Logout.
	 */
	public void logout() {
		try {
			ServiceGateway.getInstance().logout();
		} catch (Exception e) {
			ClientApp.getLogger().error("* Error using the server:",e);
			//e.printStackTrace();
		}

	}
}
