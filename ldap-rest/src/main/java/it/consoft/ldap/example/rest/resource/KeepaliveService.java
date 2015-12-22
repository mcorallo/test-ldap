package it.consoft.ldap.example.rest.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.consoft.ldap.example.rest.util.JsonUtils;

@Path("/keepalive")
public class KeepaliveService {

	private static long startupTimestamp = System.currentTimeMillis();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String isAlive() {
		String timeString = getKeepaliveResponse();

		return "The REST system is up and running since " + timeString;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isAliveJson() {
		String timeString = getKeepaliveResponse();

		Map<String, String> jsonResult = new HashMap<>();
		jsonResult.put("result", "The REST system is up and running since " + timeString);
		return JsonUtils.serialize(jsonResult);
	}

	private String getKeepaliveResponse() {
		long now = System.currentTimeMillis();
		long diff = now - startupTimestamp;

		String timeString = String.format("%02d:%02d:%02d", //
				TimeUnit.MILLISECONDS.toHours(diff), //
				TimeUnit.MILLISECONDS.toMinutes(diff), //
				TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)//
		));
		return timeString;
	}
}