package it.consoft.ldap.example.rest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import it.consoft.ldap.example.rest.bean.User;

public interface UsersDAO {

	public List<User> searchUsers(String username) throws SQLException;

	public User getUser(Integer id) throws SQLException;

	public boolean addUser(User user) throws SQLException;

	public boolean deleteUser(Integer id) throws SQLException;

	public boolean updateUser(Integer id, User user) throws SQLException;

	public void addUserGroups(int userId, Set<String> localGroupsAll) throws SQLException;

	public User getUser(String username) throws SQLException;

}
