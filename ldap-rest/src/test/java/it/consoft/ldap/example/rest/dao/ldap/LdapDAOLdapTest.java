package it.consoft.ldap.example.rest.dao.ldap;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class LdapDAOLdapTest extends LdapRestBaseTest {

	@Test
	public void getExistingUserTest() throws NamingException {
		String username = "tesla";
		String password = "password";
		User user = DAOFactory.getLdapDAO().getUser(username, password);
		assertNotNull(user);
		assertEquals(username, user.getUsername());
		assertEquals(1, user.getGroups().size());
		assertEquals("Nikola Tesla", user.getGroups().get(0));
	}

	@Test(expected = RuntimeException.class)
	public void getUserWithNullPasswordTest() throws NamingException {
		DAOFactory.getLdapDAO().getUser("admin", null);
	}

	@Test
	public void getNotExistingUserTest() throws NamingException {
		String username = "pippo1";
		String password = "password";
		User user = DAOFactory.getLdapDAO().getUser(username, password);
		assertNull(user);
	}

	@Test
	public void getExistingUserWrongPasswordTest() throws NamingException {
		String username = "tesla";
		String password = "pippo1";
		User user = DAOFactory.getLdapDAO().getUser(username, password);
		assertNull(user);
	}
}
