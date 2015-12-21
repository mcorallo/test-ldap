package it.consoft.ldap.example.rest.dao.ldap;

import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOLdap implements UsersDAO {

	@Override
	public List<User> getUsers(String username) {
		throw new RuntimeException("LDAP layer not implemented.");
	}

}
