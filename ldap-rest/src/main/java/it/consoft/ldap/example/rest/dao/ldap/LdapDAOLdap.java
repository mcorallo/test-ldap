package it.consoft.ldap.example.rest.dao.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.LdapDAO;

public class LdapDAOLdap implements LdapDAO {
	private static final Logger logger = LoggerFactory.getLogger(LdapDAOLdap.class);

	@Override
	public User getUser(String username, String password) throws NamingException {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + ",dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			new InitialDirContext(env);
		} catch (NamingException e) {
			logger.error("", e);
			return null;
		}

		User user = new User();
		user.setUsername(username);
		user.setMemberOf(new String[] {
				"admin"
		});
		return user;
	}

}
