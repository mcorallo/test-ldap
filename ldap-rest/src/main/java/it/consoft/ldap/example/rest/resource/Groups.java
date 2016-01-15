package it.consoft.ldap.example.rest.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.consoft.ldap.example.rest.bean.Group;
import it.consoft.ldap.example.rest.manager.GroupsManager;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Groups {

	@GET
	public List<Group> searchGroups(@QueryParam("ldapGroupName") String ldapGroupName, @QueryParam("localGroupName") String localGroupName) throws SQLException {
		return new GroupsManager().searchGroups(ldapGroupName, localGroupName);
	}

	@DELETE
	public Boolean deleteGroup(@QueryParam("ldapGroupName") String ldapGroupName, @QueryParam("localGroupName") String localGroupName) throws SQLException {
		return new GroupsManager().deleteGroup(ldapGroupName, localGroupName);
	}

	@POST
	public Boolean addGroup(@QueryParam("ldapGroupName") String ldapGroupName, @QueryParam("localGroupName") String localGroupName) throws SQLException {
		return new GroupsManager().addGroup(ldapGroupName, localGroupName);
	}

}
