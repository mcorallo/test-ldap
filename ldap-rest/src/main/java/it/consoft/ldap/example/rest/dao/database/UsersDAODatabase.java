package it.consoft.ldap.example.rest.dao.database;

import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAODatabase implements UsersDAO {

	@Override
	public List<User> getUsers(String username) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

	@Override
	public User getUser(Integer id) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

}
