package it.consoft.ldap.example;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.consoft.ldap.example.manager.LoginManager;

@SuppressWarnings("serial")
public class Login extends ExampleSupport {

	private static final Logger logger = LoggerFactory.getLogger(Login.class);

	private String username;
	private String password;

	public String execute() throws Exception {
		logger.debug("login procedure for: {} started", username);

		boolean ok = new LoginManager().login(getUsername(), getPassword());

		logger.info("login procedure for: " + username + " result " + ok);

		if (ok) {
			ServletActionContext.getRequest().getSession().setAttribute("username", username);
			return "saasddas";
		} else {
			return INPUT;
		}
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

}