package it.consoft.ldap.example.rest.dao.ldap;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOLdap implements UsersDAO {

	@Override
	public User getUser(String username) {
		User user = new User();
		user.setUsername("pippo");
		user.setPassword("pluto");
		return user;
	}

}
