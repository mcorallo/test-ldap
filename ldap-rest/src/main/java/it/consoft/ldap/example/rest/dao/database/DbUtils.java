package it.consoft.ldap.example.rest.dao.database;

import java.sql.SQLException;

import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;
import it.consoft.shared.jdbc.DBConfiguration;
import it.consoft.shared.jdbc.DBUtils;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.QueryHelperJdbc;
import it.consoft.shared.jdbc.TransactionBatch;

public class DbUtils {

	static {
		if (RestUtils.isUnitTestEnv() || RestUtils.isLocalEnv()) {
			QueryHelper.setLogQueries(true);
		}
	}

	public static DBConfiguration getDbConfig() {
		ConfigurationManager cm = RestUtils.getConfigurationManager();
		String dbDriver = cm.getProperty("db.driver");
		String url = cm.getProperty("db.url");

		DBConfiguration dbc = new DBConfiguration();
		dbc.setDriverClassName(dbDriver);
		dbc.setUrl(url);
		//TODO altre proprieta' di connesione (es. username, pwd, pool,...)

		return dbc;
	}

	public static QueryHelper getQueryHelper() throws SQLException {
		QueryHelper queryHelper = new QueryHelperJdbc(getDbConfig(), false);
		return queryHelper;
	}

	public static boolean executeTransaction(TransactionBatch transactionBatch) throws SQLException {
		return DBUtils.executeTransaction(transactionBatch, getDbConfig());
	}
}
