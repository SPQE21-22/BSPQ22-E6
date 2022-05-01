package com.mycompany.server.data.dataBase;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.server.data.domain.User;

public class TestDBManager {
	List<User> storedUsers = new ArrayList<>();
	
	private static TestDBManager instance = null;

	public static TestDBManager getInstance() {
		if (instance == null) {
			instance = new TestDBManager();
		}

		return instance;
	}
	
	public boolean storeUser(User u) {
		if (!storedUsers.contains(u)) {
			storedUsers.add(u);
			return true;
		}
		return false;
	}
	
	public User getUser(String email, String password) {
		User found = null;
		for (User u :storedUsers) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
				found = u;
			}
		}
		return found;
	}

}
