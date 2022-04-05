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
import com.mycompany.server.services.UserAppService;

@Path("/users")
public class UserRemoteFacade {
	
	@GET
	public void login() {
		
		User user = UserAppService.getInstance().login();
		try {
			long token = TokenManagement.getInstance().createToken(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: return token
	}
	
	@DELETE
	public void logout() {
		
		//TODO: receive real token
		long token = 0;
		
		try {
			TokenManagement.getInstance().removeToken(token);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST
	public void register() {
		//TODO: receive real parameters
		UserAppService.getInstance().register();
	}

}
