package it.consoft.ldap.example.rest.dao;

import java.util.List;

import it.consoft.ldap.example.rest.bean.User;

public interface UsersDAO {

	public List<User> getUsers(String username);

	public User getUser(Integer id);

	public boolean addUser(User user);

}
