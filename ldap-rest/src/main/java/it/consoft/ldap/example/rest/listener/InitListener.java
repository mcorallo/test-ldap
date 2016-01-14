package it.consoft.ldap.example.rest.listener;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.dao.database.DbUtils;
import it.consoft.ldap.example.rest.dao.database.ProfilesDAODatabase;
import it.consoft.ldap.example.rest.jobs.GenerateDDLScripts;
import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;
import it.consoft.shared.jdbc.QueryHelper;
import it.consoft.shared.jdbc.Record;

public class InitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (RestUtils.getConfigurationManager().isLocalEnv() || RestUtils.getConfigurationManager().isUnitTestEnv()) {
			initDatabase();
		}

	}

	public static void initDatabase() {
		try {
			QueryHelper queryHelper = DbUtils.getQueryHelper();

			ConfigurationManager ddl = RestUtils.getDdlConfigurationManager();

			Collection<String> orderedDllQueries = GenerateDDLScripts.getOrderedDllQueries(ddl);

			for (String s : orderedDllQueries) {
				queryHelper.executeUpdate(s);
			}

			ConfigurationManager queries = RestUtils.getQueries();
			List<Record> rs = queryHelper.select(queries.getProperty("ldap_local_group_rel.count.1"));
			Long count = rs.get(0).getValue("TOT");
			if (count == 0) {
				queryHelper.executeUpdate(queries.getProperty("ldap_local_group_rel.insert.1"));
			}
			logger.debug("local in memory database initialized");

			new ProfilesDAODatabase().init();
			logger.info("ProfilesDAODatabase initialized");
		} catch (SQLException e) {
			logger.error("", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
