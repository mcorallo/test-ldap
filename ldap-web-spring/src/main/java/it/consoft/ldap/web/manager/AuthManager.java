package it.consoft.ldap.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.utils.WebUtils;
import it.consoft.shared.common.configuration.ConfigurationManager;

public abstract class AuthManager {
	private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

	private static String techUserUsername;
	private static String techUserPwd;
	private static String adminGroupName;

	static {
		ConfigurationManager cm = WebUtils.getConfigurationManager();
		techUserUsername = cm.getProperty("local.technical.admin.user.username");
		techUserPwd = cm.getProperty("local.technical.admin.user.password");
		adminGroupName = cm.getProperty("admin.group.name");
	}

	public abstract User loadUser(String name, String password);

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
	};

	private static String getGrantedAuth(String memberOf) {
		return "ROLE_" + memberOf.toUpperCase();
	}

	protected User getUser(String username, String password) {
		if (techUserUsername.equals(username)) {
			return getTechnicalUser(username, password);
		}
		return loadUser(username, password);
	}

	protected User getTechnicalUser(String username, String password) {
		String encodedPwd = DigestUtils.sha256Hex(password);
		if (encodedPwd.equals(techUserPwd)) {
			User user = new User();
			user.setUsername(techUserUsername);
			user.getGroups().add(adminGroupName);
			logger.info("login ok for technical user {}", username);
			return user;
		} else {
			logger.info("login failed for technical user {}", username);
			return null;
		}
	}

}
