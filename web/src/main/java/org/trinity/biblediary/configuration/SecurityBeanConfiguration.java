package org.trinity.biblediary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.trinity.biblediary.web.util.SessionFilter;

@Configuration
public class SecurityBeanConfiguration {
    @Bean
    public SessionFilter getSessionFilter() {
        return new SessionFilter();
    }
}
