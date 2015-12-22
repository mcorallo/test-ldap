package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.dao.database.ProfilesDAODatabase;
import it.consoft.ldap.example.rest.dao.fake.ProfilesDAOFake;
import it.consoft.ldap.example.rest.dao.fake.UsersDAOFake;
import it.consoft.ldap.example.rest.dao.ldap.UsersDAOLdap;
import it.consoft.ldap.example.rest.util.ConfigurationManager;

public class DAOFactory {

	private static boolean testEnvironment = ConfigurationManager.isTestEnv();

	public static UsersDAO getUsersDAO() {
		return testEnvironment ? new UsersDAOFake() : new UsersDAOLdap();
	}

	public static ProfilesDAO getProfilesDAO() {
		return testEnvironment ? new ProfilesDAOFake() : new ProfilesDAODatabase();
	}

}
