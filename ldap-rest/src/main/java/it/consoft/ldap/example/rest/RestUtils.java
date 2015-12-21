package it.consoft.ldap.example.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtils {

	public static String serialize(Object o) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
