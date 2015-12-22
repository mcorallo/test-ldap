package it.consoft.ldap.example.rest.dao.fake;

import it.consoft.ldap.example.rest.dao.ProfilesDAO;

public class ProfilesDAOFake implements ProfilesDAO {

	@Override
	public Object getUserProfile(Integer userId) {
		return "PROFILE";
	}

}
