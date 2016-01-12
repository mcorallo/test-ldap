package it.consoft.ldap.example.rest.test;

import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.test.BaseTest;

public abstract class LdapRestBaseTest extends BaseTest {

	static {
		RestUtils.getConfigurationmanager().setUnitTestEnv();
	}

}