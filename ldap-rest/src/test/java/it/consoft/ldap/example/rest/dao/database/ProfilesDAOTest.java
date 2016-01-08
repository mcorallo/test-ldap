/**
 * 
 */
package it.consoft.ldap.example.rest.dao.database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.sharedutils.jdbc.QueryBuilder;
import it.consoft.sharedutils.jdbc.QueryHelper;
import it.consoft.sharedutils.jdbc.Record;

/**
 * @author csmi871
 *
 */
public class ProfilesDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link it.consoft.ldap.example.rest.dao.database.ProfilesDAODatabase#init()}.
	 * @throws Exception 
	 */
	@Test
	public void testInit() throws Exception {
		ProfilesDAODatabase dao = new ProfilesDAODatabase();

		dao.init();

		QueryHelper queryHelper = DbUtils.getQueryHelper();

		QueryBuilder builder = new QueryBuilder();
		builder.addToken("SELECT * FROM users");
		List<Record> select = queryHelper.select(builder.getQuery());
		assertEquals(0, select.size());

		builder.clear();

		builder.addToken("SELECT * FROM profiles");
		select = queryHelper.select(builder.getQuery());
		assertEquals(0, select.size());

		builder.clear();

		builder.addToken("SELECT * FROM user_profiles");
		select = queryHelper.select(builder.getQuery());
		assertEquals(0, select.size());
	}
	
	@Test(expected=SQLException.class)
	public void insertUserNoUsernameTest() throws SQLException {
		ProfilesDAODatabase dao = new ProfilesDAODatabase();
		dao.init();
		dao.createLocalUser(new User());
	}
	
	@Test
	public void insertUserTest() throws SQLException {
		ProfilesDAODatabase dao = new ProfilesDAODatabase();
		dao.init();
		User user = new User();
		user.setUsername("test");
		int createLocalUser = dao.createLocalUser(user);
		assertEquals(1, createLocalUser);
	}

}
