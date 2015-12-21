package it.consoft.ldap.example.rest.dao.database;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAODatabase implements UsersDAO {

	@Override
	public User getUser(String username) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

}
