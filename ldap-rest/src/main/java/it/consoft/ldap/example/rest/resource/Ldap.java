package it.consoft.ldap.example.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import it.consoft.ldap.example.rest.bean.User;
import it.consoft.ldap.example.rest.dao.DAOFactory;
import it.consoft.ldap.example.rest.manager.LdapManager;
import it.consoft.ldap.example.rest.util.RestUtils;

@Path("/ldap")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Ldap extends BaseResource {

	private LdapManager ldapManager = new LdapManager();

	@GET
	public Response getUsers(@QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
		User user = ldapManager.getUser(username, password);
		ResponseBuilder respBuilder = null;
		if (user == null) {
			respBuilder = Response.status(Response.Status.NOT_FOUND);
		} else {

			List<Object> ldapGroups = user.getAttrs().get(RestUtils.getConfigurationManager().getProperty("local.ldap.1.groups.attribute.name"));
			for (Object lg : ldapGroups) {
				List<String> localGroups = DAOFactory.getProfilesDAO().getLocalGroups((String) lg);
				user.getGroups().addAll(localGroups);
			}

			respBuilder = Response.status(Response.Status.OK).entity(user);
		}
		return respBuilder.build();
	}
}
