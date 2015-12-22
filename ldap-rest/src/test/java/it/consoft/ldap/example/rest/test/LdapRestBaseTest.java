package it.consoft.ldap.example.rest.test;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LdapRestBaseTest {

	protected static final Logger logger = LoggerFactory.getLogger(LdapRestBaseTest.class);

	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(org.junit.runner.Description description) {
			logger.debug("test " + description.getClassName() + " starting...");
		};

		protected void succeeded(org.junit.runner.Description description) {
			logger.debug("test " + description.getClassName() + " succeded.");
		};

		protected void failed(Throwable e, org.junit.runner.Description description) {
			logger.error("test " + description.getClassName() + " failed.");
		};
	};

}