package it.consoft.ldap.web.auth;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"classpath:**/spring-*.xml"
})
public abstract class GenericSpringTest {
	private static final Logger logger = LoggerFactory.getLogger(GenericSpringTest.class);

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			logger.info("Running test {}.{}", description.getClassName(), description.getMethodName());
		}
		
		@Override
		protected void succeeded(Description description) {
			logger.info("Succeded test {}.{}", description.getClassName(), description.getMethodName());

		};
		
		@Override
		protected void failed(Throwable e, Description description) {
			logger.info("Failed test {}.{}", description.getClassName(), description.getMethodName());

		};
	};

}