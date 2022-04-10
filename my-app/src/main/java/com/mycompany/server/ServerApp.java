package com.mycompany.server;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class ServerApp {
	private final static AtomicBoolean running = new AtomicBoolean(false);
	
	public static void main(String[] args) {
		String hostname = args[0];
		String port = args[1];
		
		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
		
		running.set(true); //Starts the server
		
		HttpServer server = startServer(BASE_URI);
		
		System.out.println("Running the server in: "+BASE_URI);
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        server.stop();
        
        //FIXME: error->java.lang.reflect.InvocationTargetException when compiling
		
		//while (running.get()) {
			
		//}
        
        
        

	}
	
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String uri) {
        // create a resource config that scans for JAX-RS resources and providers
        // in es.deusto.spq package
        final ResourceConfig rc = new ResourceConfig().packages("com.mycompany.server.remote");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    }
}
