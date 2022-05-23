package com.mycompany.server;

import java.io.IOException;
import java.net.URI;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


/** The Class ServerApp.*/
public class ServerApp {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger("ServerApp");
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		BasicConfigurator.configure(); /** loads the log4j properties */
		
		String hostname = args[0];
		String port = args[1];
		
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		
		HttpServer server = startServer(BASE_URI);
		
		logger.info("Server running in: "+BASE_URI+"/remote");
		System.out.println("Running the server in: "+BASE_URI+"/remote...\nCLICK ENTER TO STOP THE SERVER...");
		
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			/** TODO Auto-generated catch block*/
			e.printStackTrace();
		}
		
		
		server.stop();
		logger.info("Server stopped");

	}
	
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @param uri the uri
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String uri) {
    	HttpServer server = null;
    	try {
        /** create a resource config that scans for JAX-RS resources and providers */
        final ResourceConfig rc = new ResourceConfig().packages("com.mycompany.server.remote");

        /** create and start a new instance of grizzly http server */
        /** exposing the Jersey application at BASE_URI */
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    	}catch(Exception e) {
    		/** System.out.println("* Starting the server failed: \n");
    		logger.error("Starting the server failed",e); */
    		/** e.printStackTrace(); */
    	}
    	return server;
    }

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
    
    
}
