package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.dao.fake.ProfilesDAOFake;
import it.consoft.ldap.example.rest.dao.fake.UsersDAOFake;

public class DAOFactory {

	public static UsersDAO getUsersDAO() {
		return new UsersDAOFake();
	}

	public static ProfilesDAO getProfilesDAO() {
		return new ProfilesDAOFake();
	}

}
