package it.consoft.ldap.example.rest.dao;

import java.sql.SQLException;
import java.util.List;

import it.consoft.ldap.example.rest.bean.Group;

public interface GroupsDAO {

	List<String> getLocalGroups(String externalGroup) throws SQLException;

	List<Group> searchGroups(String ldapGroupName, String localGroupName) throws SQLException;

	Boolean deleteGroups(String ldapGroupName) throws SQLException;

	Boolean deleteGroup(String ldapGroupName, String localGroupName) throws SQLException;

	List<String> getUserGroups(Integer id) throws SQLException;

	Boolean addGroup(String ldapGroupName, String localGroupName) throws SQLException;
}
