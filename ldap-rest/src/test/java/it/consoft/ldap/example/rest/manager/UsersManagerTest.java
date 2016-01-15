package it.consoft.ldap.example.rest.manager;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.test.LdapRestBaseTest;

public class UsersManagerTest extends LdapRestBaseTest {

	private User testUser;

	@Before
	public void setup() throws SQLException {
		String username = "test-username";
		testUser = new User();
		testUser.setUsername(username);
		testUser.setEmail("test@test.it");
		DAOFactory.getUsersDAO().addUser(testUser);
	}

	@Test
	public void getUserTest() throws SQLException {

		UsersManager m = new UsersManager();

		User user = m.getUser(testUser.getUsername());
		assertNotNull(user);

		User user2 = m.getUser(user.getId());
		assertNotNull(user2);
		assertEquals(user.getUsername(), user2.getUsername());
	}

	@Test
	public void getUnexistingUserTest() throws SQLException {

		UsersManager m = new UsersManager();

		User user = m.getUser(-2);
		assertNull(user);
	}

	@Test
	public void getUsersTest() throws SQLException {

		UsersManager m = new UsersManager();

		User user = m.getUser(testUser.getUsername());
		assertNotNull(user);
		assertEquals(testUser.getUsername(), user.getUsername());
	}

	@Test
	public void getAllUsersTest() throws SQLException {

		UsersManager m = new UsersManager();

		List<User> users = m.searchUsers(null);
		assertNotNull(users);
		assertTrue(users.size() >= 1);
	}

	@Test
	public void getUnexistingUsersTest() throws SQLException {

		UsersManager m = new UsersManager();

		List<User> users = m.searchUsers("NONE");
		assertNotNull(users);
		assertEquals(0, users.size());
	}

	@Test
	public void addUserTest() throws SQLException {
		String username = "test-username-2";
		User u = new User();
		u.setUsername(username);
		u.setEmail("test2@test.it");

		UsersManager m = new UsersManager();

		boolean added = m.addUser(u);

		assertTrue(added);
	}

	@Test
	public void addExistingUserTest() throws SQLException {
		UsersManager m = new UsersManager();
		boolean added = m.addUser(testUser);
		assertTrue(!added);
	}

	@Test
	public void deleteUserTest() throws SQLException {
		UsersManager m = new UsersManager();
		User u = m.getUser(testUser.getUsername());
		boolean deleted = m.deleteUser(u.getId());
		assertTrue(deleted);
	}

	@Test
	public void deleteUnexistingUserTest() throws SQLException {
		UsersManager m = new UsersManager();
		boolean deleted = m.deleteUser(-2);
		assertTrue(!deleted);
	}

	@Test
	public void updateUserTest() throws SQLException {
		UsersManager m = new UsersManager();

		User u = m.getUser(testUser.getUsername());

		String modifiedEmail = u.getEmail() + "-MOD";
		u.setEmail(modifiedEmail);

		boolean updated = m.updateUser(u.getId(), u);
		assertTrue(updated);

		User user = m.getUser(u.getId());
		assertEquals(modifiedEmail, user.getEmail());
	}
}
