package com.mycompany.server;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.mycompany.server.remote.RemoteFacade;


public class ServerApp {
	private final static AtomicBoolean running = new AtomicBoolean(false);
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		String hostname = args[0];
		String port = args[1];
		WebTarget webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
		
		RemoteFacade.getInstance().initFacade(webTarget);
		
		running.set(true); //Starts the server
		System.out.println("Running the server in: "+String.format("http://%s:%s/rest", hostname, port));
		while (running.get()) {
			
		}

	}
}
