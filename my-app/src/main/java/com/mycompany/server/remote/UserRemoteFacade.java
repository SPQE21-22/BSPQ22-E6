package com.mycompany.server.remote;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.server.data.domain.User;

@Path("/users")
public class UserRemoteFacade {
	
	@GET
	public void login() {
		
		/*TODO: Call the UserAppService to look up the user in the DB and check if it exists
		 * That method should return a User object that then we will use for the token creation*/
		
		User user = null;
		try {
			long token = TokenManagement.getInstance().createToken(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return token
	}
	
	@DELETE
	public void logout() {
		try {
			TokenManagement.getInstance().removeToken(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO: use real token 
	}
	
	@POST
	public void register() {
		/*TODO:Call UserService to add a new user with the info received from the client.
		 * REMIND to check if user attributes are okey or if this user already exists
		 * */
	}

}
