package it.consoft.ldap.example.rest.dao.fake;

import java.util.ArrayList;
import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOFake implements UsersDAO {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User("pippo", "pluto"));
		users.add(new User("paperino", "minnie"));
	}

	@Override
	public List<User> getUsers(String username) {
		if (username == null) {
			return users;
		} else {
			List<User> result = new ArrayList<>();
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					result.add(user);
				}
			}
			return result;
		}
		// return null;
	}

}
