package it.consoft.ldap.example.rest.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigurationManager {
	private static PropertiesConfiguration configuration;
	private static Configuration envConfiguration;
	private static String environment;

	static {
		try {
			configuration = new PropertiesConfiguration(ConfigurationManager.class.getResource("/system.properties"));

			environment = configuration.getString("environment");
			System.out.println("***************************************************");
			if (environment.startsWith("${")) {
				System.out.println("UNRESOLVED CURRENT ENVIRONMENT: " + environment);
				environment = "test";
			}
			System.out.println("CURRENT ENVIRONMENT: " + environment);
			System.out.println("***************************************************");

			envConfiguration = configuration.subset(environment);

			String test = ConfigurationManager.getSystemProperty("test");
			System.out.println(test);

		} catch (ConfigurationException e) {
			e.printStackTrace();//ok log on system out
		}
	}

	public static String getSystemProperty(String key) {
		String property = envConfiguration.getString(key);
		if (property == null) {
			property = configuration.getString(key);
		}
		return property;
	}

	public static boolean isTestEnv() {
		return "test".equals(environment);
	}
	
	public static void setUnitTestEnv() {
		environment = "unit-test";
		
		System.out.println("***************************************************");
		System.out.println("FORCED CURRENT ENVIRONMENT: " + environment);
		System.out.println("***************************************************");
	}
}
