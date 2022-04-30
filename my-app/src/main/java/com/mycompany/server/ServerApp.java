package com.mycompany.server;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class ServerApp {
	private final static AtomicBoolean running = new AtomicBoolean(false);
	private static final Logger logger = Logger.getLogger("Main");
	
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		
		running.set(true); //Starts the server
		HttpServer server = startServer(BASE_URI);
		
		
		System.out.println("Running the server in: "+BASE_URI+"/remote");
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		server.stop();
        

	}
	
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String uri) {
    	HttpServer server = null;
    	try {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig().packages("com.mycompany.server.remote");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    	}catch(Exception e) {
    		System.out.println("* Starting the server failed: \n");
    		e.printStackTrace();
    	}
    	return server;
    }
}
