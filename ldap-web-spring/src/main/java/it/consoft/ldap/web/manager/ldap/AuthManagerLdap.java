package it.consoft.ldap.web.manager.ldap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.manager.AuthManager;

@Service
public class AuthManagerLdap implements AuthManager {
	private static final Logger logger = LoggerFactory.getLogger(AuthManagerLdap.class);

	private boolean checkCredentials(String name, String password) {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + name + ",dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			new InitialDirContext(env);
		} catch (NamingException e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	private String[] getMemberOf(String name) throws NamingException {
		String rootUsername = null;
		String rootPassword = null;

		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + rootUsername + ",dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, rootPassword);

		InitialDirContext initialDirContext = new InitialDirContext(env);

		return null;
	}

	@Override
	public Authentication authenticate(String name, String password) {

		boolean correctCredentials = this.checkCredentials(name, password);

		if (!correctCredentials) {
			return null;
		}

		User user = new User();
		user.setUsername(name);
		user.setMemberOf(new String[] {
				"admin"
		});

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		for (String s : user.getMemberOf()) {
			grantedAuths.add(new SimpleGrantedAuthority(getGrantedAuth(s)));
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), password, grantedAuths);
		return auth;
	}

	private static String getGrantedAuth(String memberOf) {
		return "ROLE_" + memberOf.toUpperCase();
	}

	public void getUser(InitialDirContext context) throws NamingException {
		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
		sc.setReturningAttributes(null);
		NamingEnumeration<SearchResult> results = context.search("", "", sc);
	}

}
