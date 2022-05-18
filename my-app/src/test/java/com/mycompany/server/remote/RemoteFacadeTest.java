package com.mycompany.server.remote;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.remote.serialization.BuyTicketDTO;
import com.mycompany.remote.serialization.ConsumerDTO;
import com.mycompany.remote.serialization.CreateEventDTO;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.OrganizerDTO;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.remote.serialization.UserLoginDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class RemoteFacadeTest {

	String hostname = "127.0.0.1";
	String port = "8080";
	WebTarget baseTarget;
	String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);
	HttpServer server;

	@Before
	public void setupClient() {
		Client client = ClientBuilder.newClient();
		baseTarget = client.target(BASE_URI);
		baseTarget = baseTarget.path("remote");
	}

	@Before
	public void setupServer() {

		String BASE_URI = String.format("http://%s:%s/myapp", hostname, port);

		// create a resource config that scans for JAX-RS resources and providers
		final ResourceConfig rc = new ResourceConfig().packages("com.mycompany.server.remote");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

	}
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Test
	@PerfTest(invocations =1)
	@Required(max=1500, average=600)
	public void fullOperationTest() {  //max 1,5s

		// *********************register an organizer
		WebTarget uTarget = baseTarget.path("users");
		WebTarget oTarget = uTarget.path("organizers");

		Invocation.Builder i = oTarget.request();

		OrganizerDTO dto = new OrganizerDTO();

		dto.setEmail("org@test.com");
		dto.setPassword("orgpass");
		dto.setName("testName");
		dto.setPhone("testPhone");
		dto.setAddress("testaddress");
		dto.setWebpage("testwebpage");

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		assertEquals("The registering returned OK", Status.OK.getStatusCode(), r.getStatus());

		// *********************register a consumer

		WebTarget cTarget = uTarget.path("consumers");

		Invocation.Builder i2 = cTarget.request();

		ConsumerDTO dtoc = new ConsumerDTO();

		dtoc.setEmail("con@test.com");
		dtoc.setPassword("conpass");
		dtoc.setName("name");
		dtoc.setPhone("phone");
		dtoc.setNickname("nickname");
		dtoc.setSurname("surname");

		Response r2 = i2.post(Entity.entity(dtoc, MediaType.APPLICATION_JSON));

		assertEquals("The registering returned OK", Status.OK.getStatusCode(), r2.getStatus());

		// *********************login of the organizer
		long token = -1;

		Invocation.Builder i3 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto = new UserLoginDTO();

		userdto.setEmail("org@test.com");
		userdto.setPassword("orgpass");

		Response r3 = i3.put(Entity.entity(userdto, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r3.getStatus());
		token = r3.readEntity(Long.class);
		assertTrue("The login returns a valid token", token > 0);
		

		// *********************create an event
		WebTarget eTarget = baseTarget.path("events");
		WebTarget oTarget2 = eTarget.path("organizers");
		Invocation.Builder i4 = oTarget2.request();

		CreateEventDTO dtoe = new CreateEventDTO();

		dtoe.setOrganizerToken(token);
		dtoe.setDate(LocalDate.now().plusDays(1));
		dtoe.setName("testevent");
		dtoe.setPlace("testplace");

		Response r4 = i4.post(Entity.entity(dtoe, MediaType.APPLICATION_JSON));

		assertEquals("Creating an event returned OK", Status.OK.getStatusCode(), r4.getStatus());

		// *********************logout the organizer

		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il = logoutTarget.request();

		Response rl = il.put(Entity.entity(token, MediaType.APPLICATION_JSON));

		assertEquals("The logut returned OK", Status.OK.getStatusCode(), rl.getStatus());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// *********************login of the consumer

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail("con@test.com");
		userdto5.setPassword("conpass");

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);
		

		// *********************getting active events
		List<EventDTO> listevents = null;

		Invocation.Builder i6 = eTarget.request(MediaType.APPLICATION_JSON);

		Response r6 = i6.get();

		assertEquals("Getting events returned OK", Status.OK.getStatusCode(), r6.getStatus());

		String listInJSON = r6.readEntity(String.class);

		// Deserializing the list
		Gson gson = new Gson();
		Type eventDtoListType = new TypeToken<List<EventDTO>>() {
		}.getType();
		listevents = gson.fromJson(listInJSON, eventDtoListType);

		EventDTO expectede = new EventDTO();
		expectede.setDate(dtoe.getDate());
		expectede.setName(dtoe.getName());
		expectede.setOrganizerEmail(dto.getEmail());
		expectede.setOrganizerWeb(dto.getWebpage());
		expectede.setPlace(dtoe.getPlace());

		if(!listevents.isEmpty()) {
			assertEquals("Retrieved event is the same as the created one", expectede, listevents.get(0));

		}else {
			fail("eventList is empty when getting active tickets");
		}

		// *********************buy a ticket for the created event

		if(!listevents.isEmpty()) {
			EventDTO e = listevents.get(0);
			
			WebTarget tTarget = baseTarget.path("tickets");
			WebTarget cTarget2 = tTarget.path("consumers");

			Invocation.Builder i7 = cTarget2.request();

			BuyTicketDTO dto7 = new BuyTicketDTO();

			dto7.setEventDate(e.getDate());
			dto7.setEventName(e.getName());
			dto7.setToken(tokenc);

			Response r7 = i7.post(Entity.entity(dto7, MediaType.APPLICATION_JSON));

			assertEquals("Buying the event returned OK", Status.OK.getStatusCode(), r7.getStatus());

		}else {
			fail("eventList is empty");
		}
		
		

		// *********************getting bought tickets
		WebTarget tTarget = baseTarget.path("tickets");

		WebTarget c2Target = tTarget.path("consumers");

		Invocation.Builder i8 = c2Target.request(MediaType.APPLICATION_JSON);

		Response r8 = i8.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("Getting the tickets returned OK", Status.OK.getStatusCode(), r8.getStatus());

		String listInJSON2 = r8.readEntity(String.class);

		// Deserializing the list
		Type ticketDtoListType = new TypeToken<List<TicketDTO>>() {
		}.getType();
		List<TicketDTO> listtickets = gson.fromJson(listInJSON2, ticketDtoListType);

		TicketDTO expectedt = new TicketDTO();
		expectedt.setEventDate(dtoe.getDate());
		expectedt.setEventName(dtoe.getName());
		expectedt.setUserEmail(dtoc.getEmail());
		expectedt.setPlace(dtoe.getPlace());
		
		if(!listtickets.isEmpty()) {
			assertEquals("Retrieved ticket is correct", expectedt, listtickets.get(0));

		}else {
			fail("listtickets is empty");
		}
		

		// *********************logout

		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());

	}

	@Test
	public void ConnectionTest() {
		WebTarget testTarget = baseTarget.path("test");
		WebTarget newTarget = testTarget.path("Testing name"); // This is the name that will be displayed
		Response r = newTarget.request().get();
		assertEquals(Status.OK.getStatusCode(), r.getStatus());

	}

	@After
	public void teardown() {
		server.stop();
	}

}
