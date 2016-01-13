package it.consoft.ldap.example.rest.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.dao.database.ProfilesDAODatabase;

public class InitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
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
