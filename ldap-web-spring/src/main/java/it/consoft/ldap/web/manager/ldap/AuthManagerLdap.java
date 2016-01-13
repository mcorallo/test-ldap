package it.consoft.ldap.web.manager.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.manager.AuthManager;
import it.consoft.ldap.web.utils.WebUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;
import it.consoft.shared.ldap.Ldap;
import it.consoft.shared.ldap.LdapConfiguration;

//@Service
public class AuthManagerLdap implements AuthManager {
	private static final Logger logger = LoggerFactory.getLogger(AuthManagerLdap.class);

	private static List<Ldap> ldapServers = new ArrayList<>();

	static {
		ConfigurationManager cm = WebUtils.getConfigurationmanager();
		String[] ldapProviders = cm.getStringArray("ldap.providers");
		for (String provider : ldapProviders) {
			Map<String, String> subset = cm.getSubset("ldap." + provider);
			Ldap ldap = new Ldap(new LdapConfiguration(subset));
			ldapServers.add(ldap);
		}
	}

	@Override
	public Authentication authenticate(String username, String password) {

		User user = getUser(username, password);
		if (user == null) {
			return null;
		}

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		for (String s : user.getGroups()) {
			grantedAuths.add(new SimpleGrantedAuthority(getGrantedAuth(s)));
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), password, grantedAuths);
		return auth;
	}

	private static String getGrantedAuth(String memberOf) {
		return "ROLE_" + memberOf.toUpperCase();
	}

	User getUser(String username, String password) {

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
