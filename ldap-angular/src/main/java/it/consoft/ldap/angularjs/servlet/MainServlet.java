package it.consoft.ldap.angularjs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("command");
//		switch (cmd) {
//		case "loadPhones": {
//			InputStream stream = this.getClass().getResourceAsStream("/phones.json");
//			try (PrintWriter writer = resp.getWriter()) {
//				writer.write(IOUtils.toString(stream));
//			}
//			break;
//		}
//		case "phoneDetail": {
//			String phoneId = req.getParameter("phoneId");
//			InputStream stream = this.getClass().getResourceAsStream("/phones/" + phoneId);
//			try (PrintWriter writer = resp.getWriter()) {
//				writer.write(IOUtils.toString(stream));
//			}
//			break;
//		}
//		default:
//			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "command " + cmd + " not found");
//			break;
//		}
	}

}
