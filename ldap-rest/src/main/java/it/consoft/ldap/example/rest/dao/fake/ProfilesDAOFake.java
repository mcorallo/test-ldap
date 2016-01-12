package it.consoft.ldap.example.rest.dao.fake;

import java.sql.SQLException;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.ProfilesDAO;

public class ProfilesDAOFake implements ProfilesDAO {

	@Override
	public Object getUserProfile(Integer userId) {
		return "PROFILE";
	}

	@Override
	public void init() throws SQLException {
		// TODO do nothing
	}

	@Override
	public int createLocalUser(User user) {
		// TODO do nothing
		return 0;
	}

	@Override
	public String getLocalGroup(String externalGroup) {
		return null;
	}

}
