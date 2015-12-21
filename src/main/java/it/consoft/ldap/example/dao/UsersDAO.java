package it.consoft.ldap.example.dao;

import it.consoft.ldap.example.bean.User;

public interface UsersDAO {

	public User getUser(String username);

}
