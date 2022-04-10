package com.mycompany.server.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import com.mycompany.server.data.domain.User;

public class UserAppService {
	
	private static UserAppService instance;
	
	public static UserAppService getInstance() {
		if (instance == null) {
			instance = new UserAppService();
		}

		return instance;
	}
	
	private UserAppService() {}
    
	public User login() {	
		
		
		//check if the user exist
		//check if the password and the nickname are corrects
		
		
		
        //TODO Search for the user in the DB and if exists return it
		return null;
	}
	
	public void register() {
		
		

        //TODO:Receive all user parameters
		
		//TODO: Check if parameters are valid
		
		//TODO:Create user
		//User u = new Organizer(); or User u = new Consumer();
		
		//TODO:Save user in the database
		
	}
	
	
}
