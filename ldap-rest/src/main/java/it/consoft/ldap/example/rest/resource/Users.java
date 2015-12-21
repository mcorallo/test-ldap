package it.consoft.ldap.example.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.UsersDAO;

@Path("/users")
public class Users {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(@QueryParam("username") String username) {
		UsersDAO user = DAOFactory.getUsersDAO();
		List<User> usersList = user.getUsers(username);
		return usersList;

	}

	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") Integer id) {
		throw new RuntimeException("Not Implemented");

	}

}
