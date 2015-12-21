package it.consoft.ldap.example.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.dao.UsersDAO;

@Path("/users")
public class UsersService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUsers() {
		UsersDAO user = DAOFactory.getUsersDAO();
		User user2 = user.getUser("");
		return user2;
		
	}
}
