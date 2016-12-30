package org.trinity.biblediary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.trinity.biblediary.wechat.util.SignatureFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SignatureFilter signatureFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterAfter(signatureFilter, SecurityContextPersistenceFilter.class).authorizeRequests()
                .antMatchers("/menu", "/user", "/static/**").permitAll().anyRequest().authenticated();
    }
}
