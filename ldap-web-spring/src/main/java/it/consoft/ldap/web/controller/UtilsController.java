package it.consoft.ldap.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.consoft.ldap.web.filter.LocalizationFilter;

@Controller
public class UtilsController {

	@RequestMapping(value = "/locale", method = RequestMethod.POST)
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
		String lang = request.getParameter("lang");
		if (lang != null && !lang.isEmpty()) {
			request.getSession().setAttribute(LocalizationFilter.SESSION_LANG, lang);
		}
	}

}
