package it.consoft.ldap.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
public class LoginController {

	@Autowired
	SecurityContextRepository scr;

	@Autowired
	LdapAuthenticationProvider ldap;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/login2", method = RequestMethod.POST)
	public ResponseEntity<String> loginPost( //
			@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password //
			, HttpServletRequest request, HttpServletResponse response //
	) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		try {
			Authentication authentication = ldap.authenticate(token);

			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(authentication);

			scr.saveContext(context, request, response);

		} catch (Exception e) {

			String message = e.getMessage();
			if (message != null && message.contains("HTTP error code")) {
				return new ResponseEntity<>(HttpStatus.valueOf(Integer.parseInt(message.split("\\:")[2].trim())));
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
