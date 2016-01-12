package it.consoft.ldap.example.rest.dao.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.common.configuration.ConfigurationManager;
import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.LdapDAO;
import it.consoft.ldap.example.rest.util.RestUtils;
import it.consoft.shared.ldap.Ldap;
import it.consoft.shared.ldap.LdapConfiguration;

public class LdapDAOLdap implements LdapDAO {
	private static final Logger logger = LoggerFactory.getLogger(LdapDAOLdap.class);
	private static List<Ldap> ldapServers = new ArrayList<>();

	static {
		ConfigurationManager cm = RestUtils.getConfigurationmanager();
		String[] ldapProviders = cm.getStringArray("ldap.providers");
		for (String provider : ldapProviders) {
			Map<String, String> subset = cm.getSubset("ldap." + provider);
			Ldap ldap = new Ldap(new LdapConfiguration(subset));
			ldapServers.add(ldap);
		}
	}

	@Override
	public User getUser(String username, String password) {

		try {
			logger.info("login request for user {}", username);
			it.consoft.shared.ldap.User user = null;
			for (Ldap ldap : ldapServers) {
				user = ldap.getUser(username, password);
				String url = ldap.getConfiguration().getLdapProviderUrl();
				if (user != null) {
					logger.debug("user {} found on ldap server {}", username, url);
					break;
				}
				logger.debug("user {} not found on ldap server {}, trying another server", username, url);
			}
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
