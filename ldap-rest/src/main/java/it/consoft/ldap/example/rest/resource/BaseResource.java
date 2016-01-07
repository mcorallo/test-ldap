package it.consoft.ldap.example.rest.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

public abstract class BaseResource {

	@GET
	@Path("/echo")
	public Map<String,String> echo(@QueryParam("message") String message) {
		String responseContent = "Resource " + this.getClass().getSimpleName() + " running: " + message;
		Map<String, String> map = new HashMap<>();
		map.put("echo-esp", responseContent);
		return map;
	}

}
