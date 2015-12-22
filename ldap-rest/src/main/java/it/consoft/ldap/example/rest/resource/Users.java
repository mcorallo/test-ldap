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
import it.consoft.ldap.example.rest.manager.UsersManager;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Users {

	@GET
	public List<User> getUsers(@QueryParam("username") String username) {
		return new UsersManager().getUsers(username);
	}

	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") Integer id) {
		UsersDAO dao = DAOFactory.getUsersDAO();
		return dao.getUser(id);
	}

}
