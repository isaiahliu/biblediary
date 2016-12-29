package org.trinity.biblediary.web.aspect;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.trinity.biblediary.common.message.lookup.Language;
import org.trinity.common.locale.AbstractLocaleInterceptor;

public class LocaleInterceptor extends AbstractLocaleInterceptor {
	public LocaleInterceptor(final LocaleResolver localeResolver) {
		super(localeResolver);
	}

	@Override
	protected boolean supportLocale(final Locale locale) {
		for (final Language language : Language.values()) {
			if (language.getMessageCode().equals(locale.toLanguageTag())) {
				return true;
			}
		}
		return false;
	}
}
