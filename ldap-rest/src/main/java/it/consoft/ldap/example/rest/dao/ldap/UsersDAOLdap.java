package it.consoft.ldap.example.rest.dao.ldap;

import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOLdap implements UsersDAO {

	@Override
	public List<User> getUsers(String username) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

	@Override
	public User getUser(Integer id) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

	@Override
	public boolean addUser(User user) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

	@Override
	public boolean deleteUser(Integer id) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

	@Override
	public boolean updateUser(Integer id, User user) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

}
