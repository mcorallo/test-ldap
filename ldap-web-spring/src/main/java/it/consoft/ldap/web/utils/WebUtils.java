package it.consoft.ldap.web.utils;

import javax.servlet.http.HttpServletRequest;

import it.consoft.ldap.web.filter.LocalizationFilter;
import it.consoft.shared.common.configuration.ConfigurationManager;
import it.consoft.shared.rest.RestUtils;

public class WebUtils {

	private static final String SYSTEM_PROPERTIES = "/system.properties";

	private static final ConfigurationManager configurationManager = new ConfigurationManager(SYSTEM_PROPERTIES);

	private static final RestUtils restUtils = new RestUtils(//
			configurationManager.getProperty("rest.service.url")//
			, configurationManager.getProperty("rest.service.username")//
			, configurationManager.getProperty("rest.service.password"));

	public static ConfigurationManager getConfigurationmanager() {
		return configurationManager;
	}

	public static RestUtils getRestutils() {
		return restUtils;
	}

	public static LocalizationManager getLabels(HttpServletRequest req) {
		return (LocalizationManager) req.getAttribute(LocalizationFilter.LABELS);
	}
}
