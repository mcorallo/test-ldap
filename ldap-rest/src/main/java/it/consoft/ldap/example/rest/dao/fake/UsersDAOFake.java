package it.consoft.ldap.example.rest.dao.fake;

import java.util.ArrayList;
import java.util.List;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOFake implements UsersDAO {

	private static List<User> usersDatabase = new ArrayList<>();

	static {
		usersDatabase.add(new User(0, "pippo", "pluto"));
		usersDatabase.add(new User(1, "paperino", "minnie"));
	}

	@Override
	public List<User> getUsers(String username) {
		if (username == null) {
			return usersDatabase;
		} else {
			List<User> result = new ArrayList<>();
			for (User user : usersDatabase) {
				if (user.getUsername().equals(username)) {
					result.add(user);
				}
			}
			return result;
		}
		// return null;
	}

	@Override
	public User getUser(Integer id) {
		for (User user : usersDatabase) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

}
