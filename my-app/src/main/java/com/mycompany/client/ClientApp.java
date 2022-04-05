package com.mycompany.client;

import com.mycompany.client.remote.ServiceGateway;

public class ClientApp {
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		ServiceGateway.getInstance().initGateway(hostname, port);
	}

}
