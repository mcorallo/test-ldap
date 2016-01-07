package it.consoft.ldap.example.rest.manager;

import javax.naming.NamingException;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;

public class LdapManager {

	public User getUser(String username, String password) throws NamingException {
		return DAOFactory.getLdapDAO().getUser(username, password);
	}
}
