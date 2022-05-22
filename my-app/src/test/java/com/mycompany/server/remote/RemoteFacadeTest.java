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
import com.mycompany.remote.serialization.ResellTicketDTO;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.remote.serialization.UserLoginDTO;
import com.mycompany.server.data.dataBase.ConsumerDAO;
import com.mycompany.server.data.dataBase.EventDAO;
import com.mycompany.server.data.dataBase.OrganizerDAO;
import com.mycompany.server.data.dataBase.TicketDAO;
import com.mycompany.server.data.domain.Consumer;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;
import com.mycompany.server.data.domain.Ticket;

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

	Consumer regConsumer = null;
	Organizer regOrganizer = null;
	Event creaEvent = null;

	Consumer tConsumer = null;
	Organizer tOrganizer = null;
	Event tEvent = null;
	Ticket tTicket = null;

	Consumer tConsumerResell = null;
	Event tEventResell = null;
	Ticket tTicketResell = null;

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

	@Before
	public void objectsSetUp() {
		// **************************Create the objects for the DB

		tConsumer = new Consumer("Consumer", "remconsumer", "remfac@consumer.com", "010101010", "RemFacC", "remFac");
		tConsumerResell = new Consumer("ConsumerRes", "resellconsumer", "resell@consumer.com", "010101010", "ConResell",
				"Reseller");

		tOrganizer = new Organizer("Organizer", "remorganizer", "remfac@organizer.com", "010101010", "RemFac st.",
				"www.organizer.com");

		tEvent = new Event("Event", LocalDate.parse("2050-12-31"), "RemFac st.", tOrganizer);
		tEventResell = new Event("EventResell", LocalDate.parse("2070-12-31"), "Resell st.", tOrganizer);

		tTicket = new Ticket(tEvent, tConsumer);

		tTicketResell = new Ticket(tEventResell, tConsumerResell);
		tTicketResell.setInResell(true);

		// **************************Add to the DB

		ConsumerDAO.getInstance().save(tConsumer);

		ConsumerDAO.getInstance().save(tConsumerResell);

		OrganizerDAO.getInstance().save(tOrganizer);

		EventDAO.getInstance().save(tEvent);

		TicketDAO.getInstance().save(tTicket);

		TicketDAO.getInstance().save(tTicketResell);

		// ****** regustration and creation set up

		regConsumer = new Consumer("ConsumerReg", "regconsumer", "registration@consumer.com", "010101010",
				"RegisterCon", "Register");

		regOrganizer = new Organizer("OrganizerReg", "regorganizer", "registration@organizer.com", "010101010",
				"Registration st.", "www.organizer.com");

		creaEvent = new Event("CreatedEvent", LocalDate.parse("2099-12-31"), "Created st.", tOrganizer);
	}

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void registerConsumerTest() {

		WebTarget uTarget = baseTarget.path("users");
		WebTarget cTarget = uTarget.path("consumers");

		Invocation.Builder i2 = cTarget.request();

		ConsumerDTO dtoc = new ConsumerDTO();

		dtoc.setEmail(regConsumer.getEmail());
		dtoc.setPassword(regConsumer.getPassword());
		dtoc.setName(regConsumer.getName());
		dtoc.setPhone(regConsumer.getPhone());
		dtoc.setNickname(regConsumer.getNickname());
		dtoc.setSurname(regConsumer.getSurname());

		Response r2 = i2.post(Entity.entity(dtoc, MediaType.APPLICATION_JSON));

		assertEquals("The registering returned OK", Status.OK.getStatusCode(), r2.getStatus());

		// ****delete registered consumer from the DB
		ConsumerDAO.getInstance().delete(regConsumer);

	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void registerOrganizerTest() {
		WebTarget uTarget = baseTarget.path("users");
		WebTarget oTarget = uTarget.path("organizers");

		Invocation.Builder i = oTarget.request();

		OrganizerDTO dto = new OrganizerDTO();

		dto.setEmail(regOrganizer.getEmail());
		dto.setPassword(regOrganizer.getPassword());
		dto.setName(regOrganizer.getName());
		dto.setPhone(regOrganizer.getPhone());
		dto.setAddress(regOrganizer.getAddress());
		dto.setWebpage(regOrganizer.getWebpage());

		Response r = i.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		assertEquals("The registering returned OK", Status.OK.getStatusCode(), r.getStatus());

		// ****delete registered organizer from the DB
		OrganizerDAO.getInstance().delete(regOrganizer);
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void loginTest() {
		// *********************login of the consumer
		WebTarget uTarget = baseTarget.path("users");

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail(tConsumer.getEmail());
		userdto5.setPassword(tConsumer.getPassword());

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);

		// *********************logout
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void createEventTest() {
		// *********************login of the organizer
		WebTarget uTarget = baseTarget.path("users");

		long token = -1;

		Invocation.Builder i3 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto = new UserLoginDTO();

		userdto.setEmail(tOrganizer.getEmail());
		userdto.setPassword(tOrganizer.getPassword());

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
		dtoe.setDate(creaEvent.getDate());
		dtoe.setName(creaEvent.getName());
		dtoe.setPlace(creaEvent.getPlace());

		Response r4 = i4.post(Entity.entity(dtoe, MediaType.APPLICATION_JSON));

		assertEquals("Creating an event returned OK", Status.OK.getStatusCode(), r4.getStatus());

		// *********************logout the organizer

		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il = logoutTarget.request();

		Response rl = il.put(Entity.entity(token, MediaType.APPLICATION_JSON));

		assertEquals("The logut returned OK", Status.OK.getStatusCode(), rl.getStatus());

		// ********delete created event from DB
		EventDAO.getInstance().delete(creaEvent);
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void getActiveEventsTest() {
		WebTarget eTarget = baseTarget.path("events");

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

		assertNotNull("Retrieved a list of events", listevents);
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void getResellingTicketsTest() {
		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");

		List<TicketDTO> listtickets = null;

		Invocation.Builder i6 = rTarget.request(MediaType.APPLICATION_JSON);

		Response r6 = i6.get();

		assertEquals("Getting in resell tickets returned OK", Status.OK.getStatusCode(), r6.getStatus());

		String listInJSON = r6.readEntity(String.class);

		// Deserializing the list
		Gson gson = new Gson();
		Type ticketDtoListType = new TypeToken<List<TicketDTO>>() {
		}.getType();
		listtickets = gson.fromJson(listInJSON, ticketDtoListType);

		assertNotNull("Retrieved a list of tickets", listtickets);
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void buyTicketsTest() {
		// *********************login of the consumer
		WebTarget uTarget = baseTarget.path("users");

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail(tConsumer.getEmail());
		userdto5.setPassword(tConsumer.getPassword());

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);

		// *********************buy a ticket for the created event

		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget cTarget2 = tTarget.path("consumers");

		Invocation.Builder i7 = cTarget2.request();

		BuyTicketDTO dto7 = new BuyTicketDTO();

		dto7.setEventDate(tEvent.getDate());
		dto7.setEventName(tEvent.getName());
		dto7.setToken(tokenc);

		Response r7 = i7.post(Entity.entity(dto7, MediaType.APPLICATION_JSON));

		assertEquals("Buying the event returned OK", Status.OK.getStatusCode(), r7.getStatus());

		// *********************logout
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());

		// ************delete the created ticket from DB
		TicketDAO.getInstance().delete(tTicket);

	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void getBoughtTicketsTest() {
		// *********************login of the consumer
		WebTarget uTarget = baseTarget.path("users");

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail(tConsumer.getEmail());
		userdto5.setPassword(tConsumer.getPassword());

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);

		// *********************getting bought tickets
		WebTarget tTarget = baseTarget.path("tickets");

		WebTarget c2Target = tTarget.path("consumers");

		Invocation.Builder i8 = c2Target.request(MediaType.APPLICATION_JSON);

		Response r8 = i8.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("Getting the tickets returned OK", Status.OK.getStatusCode(), r8.getStatus());

		String listInJSON2 = r8.readEntity(String.class);

		// Deserializing the list
		Gson gson = new Gson();
		Type ticketDtoListType = new TypeToken<List<TicketDTO>>() {
		}.getType();
		List<TicketDTO> listtickets = gson.fromJson(listInJSON2, ticketDtoListType);

		assertNotNull("Retrieved a list of tickets", listtickets);

		// *********************logout
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void putTicketInResellTest() {
		// *********************login of the consumer
		WebTarget uTarget = baseTarget.path("users");

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail(tConsumer.getEmail());
		userdto5.setPassword(tConsumer.getPassword());

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);

		// **********************Put a ticket in resell

		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");

		Invocation.Builder ires = rTarget.request();

		ResellTicketDTO dto = new ResellTicketDTO();
		dto.setToken(tokenc);
		dto.setTicketUserEmail(tConsumer.getEmail());
		dto.setTicketEventDate(tEvent.getDate());
		dto.setTicketEventName(tEvent.getName());

		Response rres = ires.put(Entity.entity(dto, MediaType.APPLICATION_JSON));

		assertEquals("Putting to resell a ticket returned OK", Status.OK.getStatusCode(), rres.getStatus());

		// *********************logout
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());

		// *************reset state of the ticket
		TicketDAO.getInstance().find(tEvent.getName(), tEvent.getDate().toString(), tConsumer.getEmail())
				.setInResell(false);
		TicketDAO.getInstance().save(tTicket);
	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void resellTicketsTest() {
		// *********************login of the consumer
		WebTarget uTarget = baseTarget.path("users");

		Invocation.Builder i5 = uTarget.request(MediaType.APPLICATION_JSON);

		UserLoginDTO userdto5 = new UserLoginDTO();

		userdto5.setEmail(tConsumer.getEmail());
		userdto5.setPassword(tConsumer.getPassword());

		Response r5 = i5.put(Entity.entity(userdto5, MediaType.APPLICATION_JSON));

		assertEquals("The logging in returned OK", Status.OK.getStatusCode(), r5.getStatus());
		long tokenc = r5.readEntity(Long.class);
		assertTrue("The login returns a valid token", tokenc > 0);

		// **********************Buy in resell ticket

		WebTarget tTarget = baseTarget.path("tickets");
		WebTarget rTarget = tTarget.path("resell");

		Invocation.Builder ires = rTarget.request();

		ResellTicketDTO dto = new ResellTicketDTO();
		dto.setToken(tokenc);
		dto.setTicketUserEmail(tConsumer.getEmail());
		dto.setTicketEventDate(tEventResell.getDate());
		dto.setTicketEventName(tEventResell.getName());

		Response rres = ires.post(Entity.entity(dto, MediaType.APPLICATION_JSON));

		assertEquals("Buying a ticket in resell returned OK", Status.OK.getStatusCode(), rres.getStatus());

		// *********************logout
		WebTarget logoutTarget = uTarget.path("logout");
		Invocation.Builder il2 = logoutTarget.request();

		Response rl2 = il2.put(Entity.entity(tokenc, MediaType.APPLICATION_JSON));

		assertEquals("The logout returned OK", Status.OK.getStatusCode(), rl2.getStatus());

		// *************reset state of the ticket
		TicketDAO.getInstance().find(tEvent.getName(), tEvent.getDate().toString(), tConsumer.getEmail())
				.setOwner(tConsumerResell);
		TicketDAO.getInstance().save(tTicket);

	}

	@Test
	@PerfTest(invocations = 50)
	@Required(max = 1500, average = 600)
	public void ConnectionTest() {
		WebTarget testTarget = baseTarget.path("test");
		WebTarget newTarget = testTarget.path("Testing name"); // This is the name that will be displayed
		Response r = newTarget.request().get();
		assertEquals(Status.OK.getStatusCode(), r.getStatus());

	}

	@After
	void deleteMockDB() {
		TicketDAO.getInstance().delete(tTicket);
		EventDAO.getInstance().delete(tEvent);
		OrganizerDAO.getInstance().delete(tOrganizer);
		ConsumerDAO.getInstance().delete(tConsumer);
	}

	@After
	public void teardownServer() {
		server.shutdown();
	}

}
