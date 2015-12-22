package it.consoft.ldap.example.rest.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class JsonUtilsTest extends LdapRestBaseTest {

	@Test
	public void serializeTest() {
		User u = new User();
		String username = "test-username";
		u.setUsername(username);
		String serializedUser = JsonUtils.serialize(u);
		assertNotNull(serializedUser);
		assertTrue(serializedUser.contains(username));
	}

	@Test
	public void deserializeTest() throws IOException {
		String serializedUser = IOUtils.toString(this.getClass().getResourceAsStream("/it/consoft/ldap/example/rest/user.json"));
		User user = JsonUtils.deserialize(serializedUser, User.class);
		assertNotNull(user);
		assertEquals("test-username", user.getUsername());
	}

	@Test
	public void deserializeExceptionTest() throws IOException {
		String serializedUser = "{wrong}";
		boolean exceptionThrown = false;
		try {
			JsonUtils.deserialize(serializedUser, User.class);
		} catch (JsonException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
