package it.consoft.ldap.example.adapter.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.jackson.JacksonFeature;

import it.consoft.ldap.example.adapter.UsersAdapter;
import it.consoft.ldap.example.rest.bean.User;

public class UsersAdapterRest implements UsersAdapter {

	@Override
	public User getUser(String username) {

		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		WebTarget target = client.target("http://localhost:8081/ldap-rest/rest/users");
		target = target.queryParam("username", username);
		List<User> users = target.request().get(new GenericType<List<User>>() {
		});
		System.out.println(users);
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

}
