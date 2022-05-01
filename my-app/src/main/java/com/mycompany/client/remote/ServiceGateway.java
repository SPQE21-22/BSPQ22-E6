package com.mycompany.client.remote;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.remote.serialization.UserDTO;
import com.mycompany.remote.serialization.UserLoginDTO;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ServiceGateway {
	private static ServiceGateway instance;
	private WebTarget baseTarget; 
	
	public static ServiceGateway getInstance() {
		if (instance == null) {
			instance = new ServiceGateway();
		}

		return instance;
	}
	
	private ServiceGateway() {}
	
	public void initGateway(String hostname, String port) {
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		Client client = ClientBuilder.newClient();
		baseTarget = client.target(BASE_URI);
		baseTarget = baseTarget.path("remote");
		
		
	}
	
	public void testingServer(String name) {
		WebTarget testTarget = baseTarget.path("test");
		WebTarget newTarget = testTarget.path(name); //This is the name that will be displayed
		Response r = newTarget.request().get();
		
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly done the testing");
		}else {
			System.out.println("Server has problems with the testing");
		}
	}

	public void login(String email, String password) { //TODO: Throw an exception if it fails 
		WebTarget uTarget = baseTarget.path("users");
		Invocation.Builder i = uTarget.request(MediaType.APPLICATION_JSON);
		
		UserLoginDTO userdto = new UserLoginDTO();
		
		userdto.setEmail(email);
		userdto.setPassword(password);
		
		Response r = i.put(Entity.entity(userdto,MediaType.APPLICATION_JSON));
		
		//TODO: if error throw an exception
		
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly done the login");
			ClientTokenManagement.getInstance().setToken(r.readEntity(Long.class));
		}else {
			System.out.println("Server has problems with the login");
		}	
	}

	public void register(String email, String password, String name, String phone) {
		WebTarget uTarget = baseTarget.path("users");
		Invocation.Builder i = uTarget.request();
		
		UserDTO userdto = new UserDTO();
		
		userdto.setEmail(email);
		userdto.setPassword(password);
		userdto.setName(name);
		userdto.setPhone(phone);
		
		Response r = i.post(Entity.entity(userdto,MediaType.APPLICATION_JSON));
		
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly done the register");
		}else {
			System.out.println("Server has problems with the register");
		}
		
	}

	public void logout() {
		WebTarget uTarget = baseTarget.path("users");
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder i = logoutTarget.request();
		
		Long token = ClientTokenManagement.getInstance().getToken();
		
		Response r = i.put(Entity.entity(token,MediaType.APPLICATION_JSON));
		
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly done the logout");
		}else {
			System.out.println("Server has problems with the logout");
		}
		
	}

	public List<TicketDTO> getBoughtTickets() {
		List<TicketDTO> list = null;
		
		WebTarget tTarget = baseTarget.path("tickets");
		Invocation.Builder i = tTarget.request(MediaType.APPLICATION_JSON);
		
		long token = ClientTokenManagement.getInstance().getToken();
		
		Response r = i.put(Entity.entity(token,MediaType.APPLICATION_JSON));
		
		if(r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Server has correctly got the bought tickets");

			String listInJSON = r.readEntity(String.class);
			
			//Deserializing the list
			Gson gson = new Gson();
			Type ticketDtoListType= new TypeToken<List<TicketDTO>>() {}.getType();
			list = gson.fromJson(listInJSON, ticketDtoListType);
			
		}else {
			System.out.println("Server has problems with getting the tickets");
		}
		
		return list;
		
	}

}
