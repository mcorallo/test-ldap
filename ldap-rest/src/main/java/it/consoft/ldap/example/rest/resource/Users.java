package it.consoft.ldap.example.rest.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.UsersDAO;
import it.consoft.ldap.example.rest.manager.UsersManager;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Users {

	@GET
	public List<User> getUsers(@QueryParam("username") String username) {
		List<User> users = new UsersManager().getUsers(username);
		return users;
	}

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") Integer id) {
		UsersDAO dao = DAOFactory.getUsersDAO();
		User user = dao.getUser(id);

		if (user == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.OK).entity(user).build();
	}

	@POST
	public Response addUser(String newUserJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(newUserJson, User.class);
		UsersDAO dao = DAOFactory.getUsersDAO();

		boolean result = dao.addUser(user);
		if (!result) {
			return Response.status(Status.CONFLICT).entity("User already exists: " + user.getUsername()).build();
		}
		return Response.status(Status.OK).entity("User added: " + user.getId()).build();

	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Integer id) {
		UsersDAO dao = DAOFactory.getUsersDAO();
		boolean result = dao.deleteUser(id);

		if (!result) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).build();

	}

}
