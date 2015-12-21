package it.consoft.ldap.example.rest.dao.fake;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOFake implements UsersDAO {

	@Override
	public User getUser(String username) {
		User user = new User();
		user.setUsername("pippo");
		user.setPassword("pluto");
		return user;
	}

}
