package it.consoft.ldap.example.rest.resource;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/keepalive")
public class KeepaliveService {

	private static long startupTimestamp = System.currentTimeMillis();

	@GET
	public String isAlive() {
		long now = System.currentTimeMillis();
		long diff = now - startupTimestamp;

		String timeString = String.format("%02d:%02d:%02d", //
				TimeUnit.MILLISECONDS.toHours(diff), //
				TimeUnit.MILLISECONDS.toMinutes(diff), //
				TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)//
		));

		return "The REST system is up and running since " + timeString;
	}
}