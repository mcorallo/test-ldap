package it.consoft.ldap.example.rest.dao.database;

import it.consoft.sharedutils.jdbc.DBConfiguration;
import it.consoft.sharedutils.jdbc.QueryHelper;
import it.consoft.sharedutils.jdbc.QueryHelperJdbc;

public class DbUtils {

	public static DBConfiguration getDbConfig() {
		DBConfiguration dbc = new DBConfiguration();
		dbc.setDriverClassName("org.h2.Driver");
		dbc.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		return dbc;
	}

	public static QueryHelper getQueryHelper() {
		QueryHelper queryHelper = new QueryHelperJdbc(getDbConfig());
		return queryHelper;
	}
}