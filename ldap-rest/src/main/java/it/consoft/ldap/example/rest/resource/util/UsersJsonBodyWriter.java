package it.consoft.ldap.example.rest.resource.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

@Produces(MediaType.APPLICATION_JSON)
@Provider
public class UsersJsonBodyWriter implements MessageBodyWriter {

	@Override
	public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return mediaType.equals(MediaType.APPLICATION_JSON);
	}

	@Override
	public long getSize(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeTo(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		Gson gson = new Gson();
		String json = gson.toJson(t);
		entityStream.write(json.getBytes());
	}

	// @Override
	// public boolean isWriteable(Class<?> type, Type genericType, Annotation[]
	// annotations, MediaType mediaType) {
	//
	// return mediaType.equals(MediaType.APPLICATION_JSON);
	// }
	//
	// @Override
	// public long getSize(T t, Class<?> type, Type genericType, Annotation[]
	// annotations, MediaType mediaType) {
	// return 0;
	// }
	//
	// @Override
	// public void writeTo(T t, Class<?> type, Type genericType, Annotation[]
	// annotations, MediaType mediaType,
	// MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
	// throws IOException, WebApplicationException {
	// Gson gson = new Gson();
	// String json = gson.toJson(t);
	// entityStream.write(json.getBytes());
	// }

}
