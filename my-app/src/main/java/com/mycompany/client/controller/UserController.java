package com.mycompany.client.controller;

import com.mycompany.client.remote.ClientTokenManagement;
import com.mycompany.client.remote.ServiceGateway;

public class UserController {

	private static UserController instance;

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}

		return instance;
	}

	private UserController() {
	}

	public void login(String email, String password) {
		
		try {
			ServiceGateway.getInstance().login(email, password);
		} catch (Exception e) {
			System.out.println("* Error using the server:");
			e.printStackTrace();
		}
	}
	public void register(String email, String password, String name, String phone) {
		try {
			ServiceGateway.getInstance().register(email, password,name,phone);
		} catch (Exception e) {
			System.out.println("* Error using the server:");
			e.printStackTrace();
		}

	}
	
	public void logout() {
		try {
			ServiceGateway.getInstance().logout();
		} catch (Exception e) {
			System.out.println("* Error using the server:");
			e.printStackTrace();
		}

	}
}
