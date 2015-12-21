package it.consoft.ldap.example.rest.resource.util;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class JsonWriter implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper = new ObjectMapper();

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

}
