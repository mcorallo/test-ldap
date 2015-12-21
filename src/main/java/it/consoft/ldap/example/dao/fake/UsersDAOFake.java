package it.consoft.ldap.example.dao.fake;

import it.consoft.ldap.example.bean.User;
import it.consoft.ldap.example.dao.UsersDAO;

public class UsersDAOFake implements UsersDAO {

	@Override
	public User getUser(String username) {
		User user = new User();
		user.setUsername("pippo");
		user.setPassword("pluto");
		return user;
	}

}
