package it.consoft.ldap.web.manager.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.manager.AuthManager;
import it.consoft.ldap.web.utils.RestUtils;

@Service
public class AuthManagerRest implements AuthManager {

	@Override
	public Authentication authenticate(String name, String password) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("username", name);
		queryParams.put("password", password);

		User user = RestUtils.get("ldap", queryParams, null, User.class);

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		for (String s : user.getGroups()) {
			grantedAuths.add(new SimpleGrantedAuthority(getGrantedAuth(s)));
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		return auth;
	}

	private static String getGrantedAuth(String memberOf) {
		return "ROLE_" + memberOf.toUpperCase();
	}

}
