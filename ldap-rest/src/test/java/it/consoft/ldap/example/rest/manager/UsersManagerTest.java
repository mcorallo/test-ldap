package it.consoft.ldap.example.rest.manager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.fake.UsersDAOFake;
import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class UsersManagerTest extends LdapRestBaseTest {

	@Test
	public void getUsersTest() {
		String username = "test-username";
		User u = new User();
		u.setUsername(username);
		UsersDAOFake.setUsersDatabase(Arrays.asList(u));

		UsersManager m = new UsersManager();

		List<User> users = m.getUsers(username);
		assertNotNull(users);
		assertEquals(1, users.size());
		assertEquals(username, users.get(0).getUsername());
	}
}
