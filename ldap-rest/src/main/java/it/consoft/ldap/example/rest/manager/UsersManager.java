package it.consoft.ldap.example.rest.manager;

import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.ProfilesDAO;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersManager {

	public List<User> getUsers(String username) {
		UsersDAO daoLdap = DAOFactory.getUsersDAO();
		List<User> usersList = daoLdap.getUsers(username);

		ProfilesDAO pd = DAOFactory.getProfilesDAO();

		for (User user : usersList) {
			user.setProfile((String) pd.getUserProfile(user.getId()));
		}

		return usersList;
	}

}
