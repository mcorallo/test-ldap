package it.consoft.ldap.example.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	private static final Pattern excludedPatterns = Pattern.compile("("//
			+ "/errors/.*"//
			+ "|/example/Login\\.action"//
			+ "|/js/.*"//
			+ "|/css/.*"//
			+ "|/fonts/.*"//
			+ ")");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpServletResponse res = ((HttpServletResponse) response);

		String servletPath = req.getServletPath();
		logger.debug("-----------------> requested page: {}", servletPath);
		Matcher matcher = excludedPatterns.matcher(servletPath);
		if (matcher.matches()) {
			logger.trace("excluded session check for servletPath {}", servletPath);
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);
		if (session == null) {
			logger.debug("session expired or unexisting");
			res.sendRedirect(req.getContextPath() + "/errors/401.html");
			return;
		}

		String username = (String) session.getAttribute("username");
		if (username == null || username.isEmpty()) {
			logger.debug("session expired or unexisting");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//do nothing
	}

	@Override
	public void destroy() {
		//do nothing
	}

}
