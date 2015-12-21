package it.consoft.ldap.example.manager;

import it.consoft.ldap.example.adapter.AdapterFactory;
import it.consoft.ldap.example.rest.bean.User;

public class LoginManager {

	public boolean login(String username, String password) {

		User user = AdapterFactory.getUsersAdapter().getUser(username);
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
