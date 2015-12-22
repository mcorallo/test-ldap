package it.consoft.ldap.example.rest.util;

import static org.junit.Assert.*;

import org.junit.Test;

import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class ConfigurationManagerTest extends LdapRestBaseTest {

	@Test
	public void environmentTest() {
		String systemProperty = ConfigurationManager.getSystemProperty("test");
		assertEquals("test", systemProperty);
	}
}
