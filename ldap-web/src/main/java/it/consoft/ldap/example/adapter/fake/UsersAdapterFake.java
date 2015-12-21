package it.consoft.ldap.example.adapter.fake;

import it.consoft.ldap.example.adapter.UsersAdapter;
import it.consoft.ldap.example.bean.User;
import it.consoft.ldap.example.dao.DAOFactory;
import it.consoft.ldap.example.dao.UsersDAO;

public class UsersAdapterFake implements UsersAdapter {

	@Override
	public User getUser(String username) {

		UsersDAO usersDAO = DAOFactory.getUsersDAO();
		User user = usersDAO.getUser(username);
		return user;
	}

}
