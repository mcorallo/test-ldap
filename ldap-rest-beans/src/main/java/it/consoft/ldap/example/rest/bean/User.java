package it.consoft.ldap.example.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class User implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private Map<String, List<Object>> attrs = new HashMap<>();
	private List<String> groups = new ArrayList<>();

	public User() {
	}

	public User(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public Map<String, List<Object>> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, List<Object>> attrs) {
		this.attrs = attrs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static User getInstance(Map<String, Object> userData) {
		User u = new User();
		u.setId((Integer) userData.get("ID"));
		u.setUsername((String) userData.get("USERNAME"));
		u.setEmail((String) userData.get("EMAIL"));
		return u;
	}

}
