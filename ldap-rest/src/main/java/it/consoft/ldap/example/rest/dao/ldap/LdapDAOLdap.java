package it.consoft.ldap.example.rest.dao.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.LdapDAO;

public class LdapDAOLdap implements LdapDAO {

	@Override
	public User getUser(String username, String password) throws NamingException {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + ",dc=example,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			new InitialDirContext(env);
		} catch (NamingException e) {
			// TODO add log
			e.printStackTrace();
			return null;
		}

		User user = new User();
		user.setUsername(username);
		return user;
	}

}
