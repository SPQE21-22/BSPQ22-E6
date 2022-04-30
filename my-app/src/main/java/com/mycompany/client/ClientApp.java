package com.mycompany.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mycompany.client.remote.ServiceGateway;

public class ClientApp {
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
		System.out.println("This is the client side");
		
		System.out.println("This is a testing service, enter your name:\n");
		
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	 
	        // Reading data using readLine
	        String name = "No Name";
			try {
				name = reader.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		//Here we call the testing methods
		try {
		ServiceGateway.getInstance().testingServer(name);
		}catch(Exception e) {
			System.out.println("* Error using the server:");
			//e.printStackTrace();
		}
	}
	
	
	

}
