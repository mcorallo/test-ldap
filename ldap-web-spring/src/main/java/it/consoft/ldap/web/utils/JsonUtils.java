package it.consoft.ldap.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String jsonData, Class<?> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			T t = (T) mapper.readValue(jsonData, clazz);
			return t;
		} catch (Throwable e) {
			throw new JsonException(e);
		}
	}

	public static String serialize(Object o) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(o);
		} catch (Throwable e) {
			throw new JsonException(e);
		}
	}

}

@SuppressWarnings("serial")
class JsonException extends RuntimeException {

	public JsonException(Throwable e) {
		super(e);
	}
}