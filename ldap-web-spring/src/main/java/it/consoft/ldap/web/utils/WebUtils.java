package it.consoft.ldap.web.utils;

import it.consoft.common.configuration.ConfigurationManager;

public class WebUtils {

	private static final String SYSTEM_PROPERTIES = "/system.properties";

	private static final ConfigurationManager configurationManager = new ConfigurationManager(SYSTEM_PROPERTIES);

	public static ConfigurationManager getConfigurationmanager() {
		return configurationManager;
	}
}
