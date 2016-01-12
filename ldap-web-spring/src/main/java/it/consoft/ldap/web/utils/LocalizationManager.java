package it.consoft.ldap.web.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

import it.consoft.ldap.web.filter.LocalizationFilter;

public class LocalizationManager implements MessageSourceAware {
	private static final Logger logger = LoggerFactory.getLogger(LocalizationManager.class);

	private MessageSource messageSource;
	private ThreadLocal<Locale> locale = new ThreadLocal<>();

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		LocalizationFilter.setLocalizationManager(this);
	}

	public String get(String key) {
		Locale l = locale.get();
		if (l == null) {
			l = Locale.US;
		}
		String message = null;
		try {
			message = messageSource.getMessage(key, new Object[] {}, l);
		} catch (NoSuchMessageException e) {
			logger.error(e.getMessage());
		}
		if (message == null) {
			message = "[not found: " + l + "." + key + "]";
		}
		return message;
	}

	public void setLocale(Locale locale) {
		this.locale.set(locale);
	}

}