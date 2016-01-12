package it.consoft.ldap.example.rest.dao.fake;

import java.util.ArrayList;
import java.util.Iterator;
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
		usersDatabase.add(new User(idGenerator.incrementAndGet(), "admin", "admin"));
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

	@Override
	public boolean deleteUser(Integer id) {

		Iterator<User> iterator = usersDatabase.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				logger.info("User deleted: {}", user);

				return true;
			}
		}

		logger.error("User not found: {}", id);

		return false;
	}

	@Override
	public boolean updateUser(Integer id, User user) {
		for (User oldUser : usersDatabase) {
			if (user.getId() == id) {
				oldUser.setUsername(user.getUsername());
				oldUser.setPassword(user.getPassword());
				oldUser.setGroups(user.getGroups());
				logger.info("User updated: {}", user);
				return true;
			}
		}

		logger.error("User not found: {}", id);
		return false;
	}

	public static List<User> getUsersDatabase() {
		return usersDatabase;
	}

	public static void setUsersDatabase(List<User> usersDatabase) {
		UsersDAOFake.usersDatabase.clear();
		UsersDAOFake.usersDatabase.addAll(usersDatabase);
	}

}
