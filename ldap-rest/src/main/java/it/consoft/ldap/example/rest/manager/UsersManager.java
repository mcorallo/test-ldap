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
			user.setMemberOf(new String[] {
					(String) pd.getUserProfile(user.getId())
			});
		}

		return usersList;
	}

	public boolean addUser(User user) {
		UsersDAO dao = DAOFactory.getUsersDAO();

		return dao.addUser(user);
	}

	public boolean updateUser(Integer id, User user) {
		UsersDAO dao = DAOFactory.getUsersDAO();
		return dao.updateUser(id, user);
	}

	public boolean deleteUser(Integer id) {
		UsersDAO dao = DAOFactory.getUsersDAO();
		return dao.deleteUser(id);
	}

	public User getUser(Integer id) {
		return DAOFactory.getUsersDAO().getUser(id);
	}

}
