package it.consoft.ldap.angularjs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.jackson.JacksonFeature;

import it.consoft.ldap.example.rest.bean.User;

@WebServlet("/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logout = req.getParameter("logout");
		if (logout != null) {
			logout(req);
		} else {
			login(req, resp);
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");

		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		WebTarget target = client.target("http://localhost:8080/ldap-rest/rest/users");
		target = target.queryParam("username", username);
		List<User> users = target.request().get(new GenericType<List<User>>() {
		});
		System.out.println(users);
		if (users == null || users.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String password = req.getParameter("password");
		User user = users.get(0);
		String pwd = user.getPassword();
		boolean ok = password.equals(pwd);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		req.getSession().setAttribute("username", username);
		resp.getWriter().write("ok");
	}

	private void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}

}
