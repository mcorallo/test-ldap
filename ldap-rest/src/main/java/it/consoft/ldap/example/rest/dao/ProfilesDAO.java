package it.consoft.ldap.example.rest.dao;

import java.sql.SQLException;

import it.consoft.ldap.example.rest.bean.User;

public interface ProfilesDAO {

	Object getUserProfile(Integer userId);

	void init() throws SQLException;

	int createLocalUser(User user) throws SQLException;
}
