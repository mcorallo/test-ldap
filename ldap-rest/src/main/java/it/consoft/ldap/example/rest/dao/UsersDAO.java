package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.bean.User;

public interface UsersDAO {

	public User getUser(String username);

}
