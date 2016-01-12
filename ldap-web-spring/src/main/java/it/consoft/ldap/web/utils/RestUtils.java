package it.consoft.ldap.web.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

	private static final String HTTP_LOCALHOST_8080_LDAP_REST_REST = "http://localhost:8080/ldap-rest/rest/";

	private static final Client client = ClientBuilder.newClient();

	public static <T> T get(String resource, Map<String, Object> queryParams, List<String> pathParams, Class<?> resultClass) {
		logger.debug("Sending REST request GET: {}/{}?{}", resource, pathParams, queryParams);
		
		WebTarget target = client.target(HTTP_LOCALHOST_8080_LDAP_REST_REST).path(resource);

		if (pathParams != null) {
			for (String s : pathParams) {
				target = target.path(s);
			}
		}

		if (queryParams != null) {
			for (Entry<String, Object> s : queryParams.entrySet()) {
				target = target.queryParam(s.getKey(), s.getValue());
			}
		}

		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String userJson = response.readEntity(String.class);

		T result = JsonUtils.deserialize(userJson, resultClass);

		response.close();
		return result;

	}

}
