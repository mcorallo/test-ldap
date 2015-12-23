package it.consoft.ldap.example.rest.resource;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/phones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Phones {
	private static final Logger logger = LoggerFactory.getLogger(Phones.class);

	@GET
	@Path("/{phoneId}")
	public Response getUser(@PathParam("phoneId") String phoneId) throws IOException {
		logger.debug("requested phone: {}", phoneId);
		InputStream stream = this.getClass().getResourceAsStream("/phones/" + phoneId);
		String jsonString = IOUtils.toString(stream);
//		logger.debug("returning phone json: {}", jsonString);

		return Response.status(Status.OK).entity(jsonString).build();
	}
}
