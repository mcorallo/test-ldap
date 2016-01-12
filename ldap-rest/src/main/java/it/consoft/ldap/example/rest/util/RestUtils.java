package it.consoft.ldap.example.rest.util;

import it.consoft.common.configuration.ConfigurationManager;

public class RestUtils {

	private static final String SYSTEM_PROPERTIES = "/system.properties";

	private static final ConfigurationManager configurationManager = new ConfigurationManager(SYSTEM_PROPERTIES);

	public static ConfigurationManager getConfigurationmanager() {
		return configurationManager;
	}

}
