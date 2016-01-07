package it.consoft.ldap.example.rest.dao.fake;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.LdapDAO;

public class LdapDAOFake implements LdapDAO {

	@Override
	public User getUser(String username, String password) {
		throw new RuntimeException("Not implemented");
	}

}
