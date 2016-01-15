package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.dao.database.GroupsDAODatabase;
import it.consoft.ldap.example.rest.dao.database.UsersDAODatabase;
import it.consoft.ldap.example.rest.dao.ldap.LdapDAOLdap;

public class DAOFactory {

	//	private static boolean testEnvironment = RestUtils.getConfigurationManager().isTestEnv();

	public static UsersDAO getUsersDAO() {
		return new UsersDAODatabase();
	}

	public static GroupsDAO getGroupsDAO() {
		return new GroupsDAODatabase();
	}

	public static LdapDAO getLdapDAO() {
		return new LdapDAOLdap();
	}
}
