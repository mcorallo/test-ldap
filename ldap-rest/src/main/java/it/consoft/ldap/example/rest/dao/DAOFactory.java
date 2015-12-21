package it.consoft.ldap.example.rest.dao;

import it.consoft.ldap.example.rest.dao.ldap.UsersDAOLdap;

public class DAOFactory {

	public static UsersDAO getUsersDAO() {
		return new UsersDAOLdap();
	}

}
