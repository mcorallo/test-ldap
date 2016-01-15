package it.consoft.ldap.example.rest.dao.database;

import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;

public abstract class GenericDAODatabase {

	private ConfigurationManager queries = RestUtils.getQueries();

	protected String getQuery(String key) {
		return queries.getProperty(key);
	}
}

interface Queries {
	interface Users {
		public static final String GET_ALL = "users.search";
		public static final String LIKE_USERNAME = "users.search.condition.username";
		public static final String GET_BY_ID = "users.get.by_id";
		public static final String GET_BY_USERNAME = "users.get.by_username";
		public static final String ADD = "users.add";
		public static final String DELETE_BY_ID = "users.delete.by_id";
		public static final String EDIT = "users.edit";
	}

	interface GroupsRel {
		public static final String GET_BY_LDAP = "groups.rel.by_ldap";
		public static final String SEARCH = "groups.rel.search";
		public static final String BY_LDAP = "groups.rel.search.condition.ldap";
		public static final String BY_LOCAL = "groups.rel.search.condition.local";
		public static final String DELETE = "groups.rel.delete";
		public static final String DELETE_BY_LDAP = "groups.rel.delete.by_ldap";
		public static final String COUNT_BY_LOCAL = "groups.rel.count.by_local";
		public static final String ADD = "groups.rel.add";
	}

	interface UserGroups {
		public static final String DELETE_BY_USER = "user_groups.delete.by_user";
		public static final String DELETE_BY_LOCAL = "user_groups.delete.by_local";
		public static final String GET_BY_USER = "user_groups.get.by_user";
		public static final String ADD_BY_USER = "user_groups.add.by_user";
	}
}
