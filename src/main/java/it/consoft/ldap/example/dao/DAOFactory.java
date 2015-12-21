package it.consoft.ldap.example.dao;

import it.consoft.ldap.example.dao.ldap.UsersDAOLdap;

public class DAOFactory {

	public static UsersDAO getUsersDAO() {
		return new UsersDAOLdap();
	}

}
