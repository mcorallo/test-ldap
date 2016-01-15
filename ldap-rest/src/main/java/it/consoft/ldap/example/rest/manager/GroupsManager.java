package it.consoft.ldap.example.rest.manager;

import java.sql.SQLException;
import java.util.List;

import it.consoft.ldap.example.rest.bean.Group;
import it.consoft.ldap.example.rest.dao.DAOFactory;

public class GroupsManager {

	public List<Group> searchGroups(String ldapGroupName, String localGroupName) throws SQLException {
		return DAOFactory.getGroupsDAO().searchGroups(ldapGroupName, localGroupName);
	}

	public Boolean deleteGroup(String ldapGroupName, String localGroupName) throws SQLException {
		if (localGroupName == null || localGroupName.isEmpty()) {
			//delete all
			return DAOFactory.getGroupsDAO().deleteGroups(ldapGroupName);
		} else {
			//delete one
			return DAOFactory.getGroupsDAO().deleteGroup(ldapGroupName, localGroupName);
		}
	}

	public Boolean addGroup(String ldapGroupName, String localGroupName) throws SQLException {
		return DAOFactory.getGroupsDAO().addGroup(ldapGroupName, localGroupName);
	}

}
