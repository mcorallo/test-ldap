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

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

	private static final Client client;
	private static final String restServiceUrl;

	static {
		restServiceUrl = WebUtils.getConfigurationmanager().getProperty("rest.service.url");
		String restServiceUsername = WebUtils.getConfigurationmanager().getProperty("rest.service.username");
		String restServicePassword = WebUtils.getConfigurationmanager().getProperty("rest.service.password");
		client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials(restServiceUsername, restServicePassword).build();
		client.register(feature);
	}

	public static <T> T get(String resource, Map<String, Object> queryParams, List<String> pathParams, Class<?> resultClass) {
		logger.debug("Sending REST request GET: {}/{}?{}", resource, pathParams, queryParams);

		WebTarget target = client.target(restServiceUrl).path(resource);

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
