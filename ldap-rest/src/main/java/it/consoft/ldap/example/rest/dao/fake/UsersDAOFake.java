package it.consoft.ldap.example.rest.dao.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.UsersDAO;

public class UsersDAOFake implements UsersDAO {

	private static final AtomicInteger idGenerator = new AtomicInteger();
	private static List<User> usersDatabase = new ArrayList<>();

	private static final Logger logger = LoggerFactory.getLogger(UsersDAOFake.class);

	static {
		usersDatabase.add(new User(idGenerator.incrementAndGet(), "pippo", "pluto"));
		usersDatabase.add(new User(idGenerator.incrementAndGet(), "paperino", "minnie"));
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

	@Override
	public boolean addUser(User user) {

		for (User user1 : usersDatabase) {

			if (user1.getUsername().equals(user.getUsername())) {
				logger.error("User already exists: {}", user.getUsername());
				return false;
			}
		}

		user.setId(idGenerator.incrementAndGet());
		usersDatabase.add(user);
		logger.debug("User added: {}", user);
		return true;
	}

}
