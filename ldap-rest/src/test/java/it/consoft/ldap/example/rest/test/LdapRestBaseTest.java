package it.consoft.ldap.example.rest.test;

import it.consoft.common.configuration.ConfigurationManager;
import it.consoft.test.BaseTest;

public abstract class LdapRestBaseTest extends BaseTest {

	static {
		ConfigurationManager.setUnitTestEnv();
	}

}