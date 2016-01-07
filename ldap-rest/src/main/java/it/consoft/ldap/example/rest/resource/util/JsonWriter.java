package it.consoft.ldap.example.rest.resource.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class JsonWriter implements ContextResolver<ObjectMapper>, ContainerResponseFilter {

	final ObjectMapper defaultObjectMapper = new ObjectMapper();

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Cache-Control", "private, no-store, no-cache, must-revalidate");
		responseContext.getHeaders().add("Pragma", "no-cache");
	}

}
