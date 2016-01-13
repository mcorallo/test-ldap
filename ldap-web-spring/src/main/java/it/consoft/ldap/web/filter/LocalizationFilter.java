package it.consoft.ldap.web.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import it.consoft.ldap.web.utils.LocalizationManager;

public class LocalizationFilter implements Filter {

	public static final String LABELS = "labels";

	public static final String SESSION_LANG = "session_lang";

	private static LocalizationManager localizationManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		req.setAttribute(LABELS, localizationManager);
		String lang = req.getParameter("lang");
		if (lang == null) {
			lang = (String) req.getSession().getAttribute(SESSION_LANG);
		}
		if (lang != null && !lang.isEmpty()) {
			Locale locale = new Locale(lang);
			localizationManager.setLocale(locale);
		}
		chain.doFilter(request, response);
	}

	public static LocalizationManager getLocalizationManager() {
		return localizationManager;
	}

	public static void setLocalizationManager(LocalizationManager localizationManager) {
		LocalizationFilter.localizationManager = localizationManager;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
