package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.dao.database.ProfilesDAODatabase;
import it.consoft.ldap.example.rest.dao.fake.LdapDAOFake;
import it.consoft.ldap.example.rest.dao.fake.ProfilesDAOFake;
import it.consoft.ldap.example.rest.dao.fake.UsersDAOFake;
import it.consoft.ldap.example.rest.dao.ldap.LdapDAOLdap;
import it.consoft.ldap.example.rest.util.ConfigurationManager;

public class DAOFactory {

	private static boolean testEnvironment = ConfigurationManager.isTestEnv();

	public static UsersDAO getUsersDAO() {
		return new UsersDAOFake();
	}

	public static ProfilesDAO getProfilesDAO() {
		return testEnvironment ? new ProfilesDAOFake() : new ProfilesDAODatabase();
	}

	public static LdapDAO getLdapDAO() {
		return testEnvironment ? new LdapDAOFake() : new LdapDAOLdap();
	}
}
