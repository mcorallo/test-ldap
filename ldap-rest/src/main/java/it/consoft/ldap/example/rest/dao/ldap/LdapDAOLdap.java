package it.consoft.ldap.example.rest.dao.ldap;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.LdapDAO;
import it.consoft.shared.ldap.Ldap;
import it.consoft.shared.ldap.LdapConfiguration;

public class LdapDAOLdap implements LdapDAO {
	private static final Logger logger = LoggerFactory.getLogger(LdapDAOLdap.class);

	@Override
	public User getUser(String username, String password) {

		try {
			logger.info("login request for user {}", username);
			LdapConfiguration conf = new LdapConfiguration();
			conf.setLdapProviderUrl("ldap://ldap.forumsys.com");
			conf.setLdapPrincipalTemplate("uid=%s,dc=example,dc=com");
			conf.setTechnicalUserPassword("password");
			conf.setTechnicalUserUsername("tesla");
			conf.setGroupsAttributeName("cn");
			conf.setUserAttributes("cn".split(","));

			Ldap ldap = new Ldap(conf);
			it.consoft.shared.ldap.User user = ldap.getUser(username, password);
			if (user == null) {
				return null;
			}

			User restUser = new User();
			BeanUtils.copyProperties(restUser, user);
			logger.info("login ok for user {}", username);
			return restUser;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
