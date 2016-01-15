package it.consoft.ldap.example.rest.bean;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class Group implements Serializable {

	private String ldapGroupName;
	private String localGroupName;

	public String getLdapGroupName() {
		return ldapGroupName;
	}

	public void setLdapGroupName(String ldapGroupName) {
		this.ldapGroupName = ldapGroupName;
	}

	public String getLocalGroupName() {
		return localGroupName;
	}

	public void setLocalGroupName(String localGroupName) {
		this.localGroupName = localGroupName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Group [ldapGroupName=");
		builder.append(ldapGroupName);
		builder.append(", localGroupName=");
		builder.append(localGroupName);
		builder.append("]");
		return builder.toString();
	}

	public static Group getInstance(Map<String, Object> userData) {
		Group g = new Group();
		g.setLdapGroupName((String) userData.get("LDAP_GROUP_NAME"));
		g.setLocalGroupName((String) userData.get("LOCAL_GROUP_NAME"));
		return g;
	}
}
