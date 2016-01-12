package it.consoft.ldap.example.rest.dao.ldap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
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
			if (e.getMessage() != null && e.getMessage().matches("^\\[LDAP\\:.* 49 .*$")) {
				logger.error(e.getMessage());
			} else {
				logger.error("", e);
			}
			return null;
		}

		User user = new User();
		user.setUsername(username);
		user.setAttrs(getAttributes(username));
		return user;
	}

	private Map<String, List<Object>> getAttributes(String name) throws NamingException {
		String rootPrincipal = "uid=tesla,dc=example,dc=com";
		String rootPassword = "password";
		String[] userAttrs = new String[] {
				"cn"
		};
		String attrName = "cn";

		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, rootPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, rootPassword);

		InitialDirContext context = new InitialDirContext(env);

		Attributes attributes = context.getAttributes(rootPrincipal, userAttrs);
		NamingEnumeration<? extends Attribute> results = attributes.getAll();

		Map<String, List<Object>> attrs = new HashMap<>();

		while (results.hasMoreElements()) {
			Attribute attribute = (Attribute) results.nextElement();
			ArrayList<Object> value = new ArrayList<>();
			attrs.put(attribute.getID(), value);
			NamingEnumeration<?> all = attribute.getAll();
			while (all.hasMoreElements()) {
				Object object = (Object) all.nextElement();
				value.add(object);
			}
		}

		attrs.put(User.GROUPS, attrs.get(attrName));

		return attrs;
	}

}
