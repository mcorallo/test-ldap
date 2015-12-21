package it.consoft.ldap.example.adapter.ejb;

import it.consoft.ldap.example.adapter.UsersAdapter;
import it.consoft.ldap.example.rest.bean.User;

public class UsersAdapterEjb implements UsersAdapter {

	@Override
	public User getUser(String username) {
		throw new RuntimeException("EJB layer not implemented.");
	}

}
