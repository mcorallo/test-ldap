package it.consoft.ldap.example.dao.database;

import it.consoft.ldap.example.bean.User;
import it.consoft.ldap.example.dao.UsersDAO;

public class UsersDAODatabase implements UsersDAO {

	@Override
	public User getUser(String username) {
		throw new RuntimeException("JDBC layer not implemented.");
	}

}
