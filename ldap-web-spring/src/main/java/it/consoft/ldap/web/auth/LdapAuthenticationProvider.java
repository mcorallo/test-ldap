package it.consoft.ldap.web.auth;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.web.utils.JsonUtils;

@Component
public class LdapAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			URIBuilder builder = new URIBuilder("http://localhost:8080/ldap-rest/rest/ldap");
			builder.setParameter("username", name).setParameter("password", password);

			HttpGet httpGet = new HttpGet(builder.build());
			try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
				if (response1.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity1 = response1.getEntity();
					String userJson = EntityUtils.toString(entity1);
					User user = JsonUtils.deserialize(userJson, User.class);
					EntityUtils.consume(entity1);
					List<GrantedAuthority> grantedAuths = new ArrayList<>();
					//prendere i gruppi restituiti da LDAP
					//prendere il relativo mapping con i profili locali da DB
					//aggiungere i profili locali alle grantedAuths
					grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
					Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
					return auth;
				} else {
					//TODO log error code
				}
			}
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}