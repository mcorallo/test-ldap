package it.consoft.ldap.example.adapter;

import it.consoft.ldap.example.rest.bean.User;

public interface UsersAdapter {

	public User getUser(String username);

}
