package it.consoft.ldap.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.consoft.ldap.web.auth.LdapAuthenticationProvider;
import it.consoft.ldap.web.utils.LocalizationManager;
import it.consoft.shared.rest.exception.RestRequestException;

@Controller
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	SecurityContextRepository securityContextRepository;

	@Autowired
	LdapAuthenticationProvider ldap;

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			String msg;
			LocalizationManager localizationManager = getLocalizationManager(request);
			switch (error) {
			case "404": {
				msg = localizationManager.get("invalid.credentials");
				break;
			}
			case "500": {
				msg = localizationManager.get("internal.server.error");
				break;
			}
			default:
				msg = localizationManager.get("internal.server.error");
				break;
			}
			model.addObject("error", msg);
		}

		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> authenticate( //
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password //
			, HttpServletRequest request, HttpServletResponse response //
	) {

		try {
			Authentication authentication = ldap.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(authentication);
			securityContextRepository.saveContext(context, request, response);
		} catch (RestRequestException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatusCode()));
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
