package it.consoft.ldap.example.rest.dao.database;

import it.consoft.ldap.example.rest.dao.ProfilesDAO;

public class ProfilesDAODatabase implements ProfilesDAO {

	@Override
	public Object getUserProfile(Integer userId) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

}
