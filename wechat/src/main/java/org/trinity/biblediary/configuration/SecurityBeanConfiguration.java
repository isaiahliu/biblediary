package org.trinity.biblediary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.trinity.biblediary.wechat.util.SignatureFilter;

@Configuration
public class SecurityBeanConfiguration {
    @Bean
    public SignatureFilter getSignatureFilter() {
        return new SignatureFilter();
    }
}
