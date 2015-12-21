package it.consoft.ldap.example.manager;

import it.consoft.ldap.example.bean.User;
import it.consoft.ldap.example.dao.DAOFactory;
import it.consoft.ldap.example.dao.UsersDAO;

public class LoginManager {

	public boolean login(String username, String password) {

		UsersDAO usersDAO = DAOFactory.getUsersDAO();
		User user = usersDAO.getUser(username);
		if (user == null) {
			System.out.println("user " + username + " does not exists");
			return false;
		}

		String pwd = user.getPassword();
		boolean ok = password.equals(pwd);
		if (!ok) {
			System.out.println("invalid password for user " + username);
			return false;
		}
		return true;
	}

}
