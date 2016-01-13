package it.consoft.ldap.web.controller;

import javax.servlet.http.HttpServletRequest;

import it.consoft.ldap.web.utils.LocalizationManager;
import it.consoft.ldap.web.utils.WebUtils;

public class BaseController {

	protected LocalizationManager getLocalizationManager(HttpServletRequest request) {
		return WebUtils.getLabels(request);
	}

}