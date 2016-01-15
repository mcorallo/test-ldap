package it.consoft.ldap.example.rest.manager;

import java.sql.SQLException;
import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.GroupsDAO;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersManager {

	public List<User> searchUsers(String username) throws SQLException {
		UsersDAO dao = DAOFactory.getUsersDAO();
		List<User> usersList = dao.searchUsers(username);

		GroupsDAO gd = DAOFactory.getGroupsDAO();

		for (User user : usersList) {
			user.setGroups(gd.getUserGroups(user.getId()));
		}

		return usersList;
	}

	public User getUser(Integer id) throws SQLException {
		return DAOFactory.getUsersDAO().getUser(id);
	}

	public User getUser(String username) throws SQLException {
		return DAOFactory.getUsersDAO().getUser(username);
	}

	public boolean addUser(User user) throws SQLException {
		return DAOFactory.getUsersDAO().addUser(user);
	}

	public boolean updateUser(Integer id, User user) throws SQLException {
		return DAOFactory.getUsersDAO().updateUser(id, user);
	}

	public boolean deleteUser(Integer id) throws SQLException {
		return DAOFactory.getUsersDAO().deleteUser(id);
	}

}
