package it.consoft.ldap.example.adapter.rest;

import it.consoft.ldap.example.adapter.UsersAdapter;
import it.consoft.ldap.example.rest.bean.User;

public class UsersAdapterRest implements UsersAdapter {

	@Override
	public User getUser(String username) {
		throw new RuntimeException("REST layer not implemented.");
	}

}
