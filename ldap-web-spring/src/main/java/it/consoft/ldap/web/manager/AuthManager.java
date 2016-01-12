package it.consoft.ldap.web.manager;

import org.springframework.security.core.Authentication;

public interface AuthManager {

	Authentication authenticate(String name, String password);

}
