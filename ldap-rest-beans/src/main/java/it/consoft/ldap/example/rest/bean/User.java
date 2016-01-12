package it.consoft.ldap.example.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class User implements Serializable {

	public static final String GROUPS = "groups";
	private Integer id;
	private String username;
	private String password;
	private Map<String, List<Object>> attrs = new HashMap<>();
	private List<String> groups;

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
		this.groups = new ArrayList<>();
		if (attrs != null) {
			for (Object o : attrs.get(GROUPS)) {
				this.groups.add((String) o);
			}
		}
	}

}
