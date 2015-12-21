package it.consoft.ldap.example.adapter.fake;

import it.consoft.ldap.example.adapter.UsersAdapter;
import it.consoft.ldap.example.rest.bean.User;

public class UsersAdapterFake implements UsersAdapter {

	@Override
	public User getUser(String username) {
		User user = new User();
		user.setUsername("FAKE");
		user.setPassword("FAKE");
		return user;
	}

}
