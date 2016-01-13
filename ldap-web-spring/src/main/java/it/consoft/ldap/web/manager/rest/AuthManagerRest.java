package it.consoft.ldap.web.manager.rest;

import org.springframework.stereotype.Service;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.manager.AuthManager;
import it.consoft.ldap.web.utils.WebUtils;
import it.consoft.shared.rest.RestRequest.RestRequestBuilder;

@Service
public class AuthManagerRest extends AuthManager {

	@Override
	public User loadUser(String name, String password) {
		RestRequestBuilder rrb = new RestRequestBuilder("ldap")//
				.queryParameter("username", name)//
				.queryParameter("password", password)//
				.responseClass(User.class);

		User user = WebUtils.getRestutils().get(rrb.build());
		return user;
	}

}
