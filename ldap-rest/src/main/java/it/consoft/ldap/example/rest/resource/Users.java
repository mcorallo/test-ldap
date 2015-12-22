package it.consoft.ldap.example.rest.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.manager.UsersManager;
import it.consoft.ldap.example.rest.util.JsonUtils;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Users {

	private UsersManager usersManager = new UsersManager();

	@GET
	public List<User> getUsers(@QueryParam("username") String username) {
		List<User> users = usersManager.getUsers(username);
		return users;
	}

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") Integer id) {
		User user = usersManager.getUser(id);

		if (user == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.OK).entity(user).build();
	}

	@POST
	public Response addUser(String newUserJson) {
		User user = JsonUtils.deserialize(newUserJson, User.class);

		boolean result = usersManager.addUser(user);
		if (!result) {
			return Response.status(Status.CONFLICT).entity("User already exists: " + user.getUsername()).build();
		}
		return Response.status(Status.OK).entity("User added: " + user.getId()).build();

	}

	@PUT
	@Path("/{id}")
	public Response updateUser(@PathParam("id") Integer id, String newUserJson) {
		User user = JsonUtils.deserialize(newUserJson, User.class);
		boolean result = usersManager.updateUser(id, user);

		if (!result) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).build();

	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		boolean result = usersManager.deleteUser(id);

		if (!result) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).build();

	}

}
