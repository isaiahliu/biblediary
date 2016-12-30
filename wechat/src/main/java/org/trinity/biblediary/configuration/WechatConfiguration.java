package org.trinity.biblediary.configuration;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import org.trinity.biblediary.wechat.aspect.LocaleInterceptor;
import org.trinity.common.locale.AbstractLocaleInterceptor;
import org.trinity.rest.util.SimplifiedChineseLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class WechatConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(getLocaleInterceptor(getLocaleResolver())).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(3600);
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        stringConverter.setWriteAcceptCharset(false);

        converters.add(stringConverter);
        converters.add(new Jaxb2RootElementHttpMessageConverter());

        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(this.applicationContext).build();
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));

        super.configureMessageConverters(converters);
    }

    @Bean
    public AbstractLocaleInterceptor getLocaleInterceptor(final LocaleResolver localeResolver) {
        return new LocaleInterceptor(localeResolver);
    }

    @Bean
    public LocaleResolver getLocaleResolver() {
        return new SimplifiedChineseLocaleResolver();
    }

    @Bean(name = "velocityViewResolver")
    @ConditionalOnProperty(name = "spring.velocity.enabled", matchIfMissing = true)
    public VelocityViewResolver getVelocityViewResolver(final VelocityProperties properties) {
        final VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();
        resolver.setExposeSpringMacroHelpers(true);
        properties.applyToViewResolver(resolver);
        final String layoutUrl = properties.getProperties().getOrDefault("layoutUrl", null);
        if (layoutUrl != null) {
            resolver.setLayoutUrl(layoutUrl);
        }
        return resolver;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
