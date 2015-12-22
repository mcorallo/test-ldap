package it.consoft.ldap.example.rest.manager;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.fake.UsersDAOFake;
import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class UsersManagerTest extends LdapRestBaseTest {

	private User testUser;

	@Before
	public void setup() {
		String username = "test-username";
		testUser = new User();
		testUser.setId(-1);
		testUser.setUsername(username);
		UsersDAOFake.setUsersDatabase(Arrays.asList(testUser));
	}

	@Test
	public void getUserTest() {

		UsersManager m = new UsersManager();

		User user = m.getUser(testUser.getId());
		assertNotNull(user);
		assertEquals(testUser.getUsername(), user.getUsername());
	}

	@Test
	public void getUnexistingUserTest() {

		UsersManager m = new UsersManager();

		User user = m.getUser(-2);
		assertNull(user);
	}

	@Test
	public void getUsersTest() {

		UsersManager m = new UsersManager();

		List<User> users = m.getUsers(testUser.getUsername());
		assertNotNull(users);
		assertEquals(1, users.size());
		assertEquals(testUser.getUsername(), users.get(0).getUsername());
	}

	@Test
	public void getAllUsersTest() {

		UsersManager m = new UsersManager();

		List<User> users = m.getUsers(null);
		assertNotNull(users);
		assertEquals(1, users.size());
	}

	@Test
	public void getUnexistingUsersTest() {

		UsersManager m = new UsersManager();

		List<User> users = m.getUsers("NONE");
		assertNotNull(users);
		assertEquals(0, users.size());
	}

	@Test
	public void addUserTest() {
		String username = "test-username-2";
		User u = new User();
		u.setUsername(username);

		UsersManager m = new UsersManager();

		boolean added = m.addUser(u);

		assertTrue(added);
		assertNotNull(u.getId());
		assertEquals(username, u.getUsername());
	}

	@Test
	public void addExistingUserTest() {
		UsersManager m = new UsersManager();
		boolean added = m.addUser(testUser);
		assertTrue(!added);
	}

	@Test
	public void deleteUserTest() {
		UsersManager m = new UsersManager();
		boolean deleted = m.deleteUser(testUser.getId());
		assertTrue(deleted);
	}

	@Test
	public void deleteUnexistingUserTest() {
		UsersManager m = new UsersManager();
		boolean deleted = m.deleteUser(-2);
		assertTrue(!deleted);
	}

	@Test
	public void updateUserTest() {
		UsersManager m = new UsersManager();
		String modifiedUsername = testUser.getUsername() + "-MOD";
		testUser.setUsername(modifiedUsername);
		boolean updated = m.updateUser(testUser.getId(), testUser);
		assertTrue(updated);

		User user = m.getUser(testUser.getId());
		assertEquals(modifiedUsername, user.getUsername());
	}
}
