package it.consoft.ldap.web.auth;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.utils.JsonUtils;

public class LdapAuthenticationProviderTest extends GenericRestTest {

	@Autowired
	private LdapAuthenticationProvider authenticationProvider;

	@Test
	public void testAuthenticate() {

		User user = new User();
		Map<String, List<Object>> attrs = new HashMap<>();
		String group = "admin";
		attrs.put(it.consoft.shared.ldap.User.GROUPS, Arrays.asList((Object) group));
		user.setAttrs(attrs);
		user.setGroups(Arrays.asList(group));

		stubFor(get(urlMatching(".*/ldap-rest/rest/ldap.*")).willReturn(aResponse().withBody(JsonUtils.serialize(user))));

		Authentication authentication = new UsernamePasswordAuthenticationToken("tesla", "password");
		Authentication authenticate = authenticationProvider.authenticate(authentication);
		assertNotNull(authenticate);

	}

}
