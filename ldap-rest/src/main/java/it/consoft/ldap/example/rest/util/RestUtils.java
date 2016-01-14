package it.consoft.ldap.example.rest.util;

import it.consoft.shared.common.configuration.ConfigurationManager;

public class RestUtils {

	private static final String SYSTEM_PROPERTIES = "/system.properties";
	private static final String DDL_PROPERTIES = "/ddl.properties";
	private static final String QUERIES_PROPERTIES = "/queries.properties";

	private static ConfigurationManager configurationManager = new ConfigurationManager(SYSTEM_PROPERTIES);
	private static ConfigurationManager ddlConfigurationManager = new ConfigurationManager(DDL_PROPERTIES);
	private static ConfigurationManager queriesConfigurationManager = new ConfigurationManager(QUERIES_PROPERTIES);

	public static ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	public static void setSystemPropertiesFile(String fileName) {
		configurationManager = new ConfigurationManager(fileName);
	}

	public static ConfigurationManager getQueries() {
		return queriesConfigurationManager;
	}

	public static ConfigurationManager getDdlConfigurationManager() {
		return ddlConfigurationManager;
	}
}
