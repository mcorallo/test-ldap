package it.consoft.ldap.example.rest.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.GroupsDAO;
import it.consoft.ldap.example.rest.dao.UsersDAO;
import it.consoft.ldap.example.rest.util.RestUtils;

public class LdapManager {

	public User getUser(String username, String password) throws Exception {

		User ldapUser = DAOFactory.getLdapDAO().getUser(username, password);
		if (ldapUser == null) {
			return null;
		}

		GroupsDAO groupsDAO = DAOFactory.getGroupsDAO();
		UsersDAO usersDAO = DAOFactory.getUsersDAO();

		User localUser = usersDAO.getUser(username);
		if (localUser != null) {
			ldapUser.setId(localUser.getId());
			ldapUser.setEmail(localUser.getEmail());
		}

		List<Object> ldapGroups = ldapUser.getAttrs().get(RestUtils.getConfigurationManager().getProperty("local.ldap.1.groups.attribute.name"));
		Set<String> localGroupsAll = new HashSet<>();
		for (Object lg : ldapGroups) {
			List<String> localGroups = groupsDAO.getLocalGroups((String) lg);
			localGroups.addAll(localGroups);
			ldapUser.getGroups().addAll(localGroups);
		}

		usersDAO.addUserGroups(ldapUser.getId(), localGroupsAll);

		return ldapUser;

	}
}
