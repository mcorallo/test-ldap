package it.consoft.ldap.web.auth;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.tomakehurst.wiremock.WireMockServer;

public abstract class GenericRestTest extends GenericSpringTest {

	private static WireMockServer server;

	@BeforeClass
	public static void setUpClass() throws Exception {
		server = new WireMockServer(8080);
		server.start();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		server.stop();
	}

	public GenericRestTest() {
		super();
	}

	@Before
	public void setup() {
		server.resetMappings();
		reset();
	}

}