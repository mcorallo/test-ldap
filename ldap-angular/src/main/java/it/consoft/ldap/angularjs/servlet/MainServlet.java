package it.consoft.ldap.angularjs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/main")
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restRequest = req.getParameter("request");
		if (restRequest == null || restRequest.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		WebTarget target = client.target("http://localhost:8080/ldap-rest/rest" + restRequest);
		String restResult = target.request().get(String.class);
		logger.debug("received rest response: " + restResult);
		resp.getWriter().write(restResult);
	}

}
