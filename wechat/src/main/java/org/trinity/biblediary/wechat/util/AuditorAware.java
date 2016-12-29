package org.trinity.biblediary.wechat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.lookup.AccessRight;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.repository.auditing.AbstractEntityAuditorAware;

@Component
@EnableJpaAuditing
public class AuditorAware extends AbstractEntityAuditorAware {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Override
    protected String getAuditorName() {
        try {
            return securityUtil.getCurrentToken().getUsername();
        } catch (final IException e) {
            return "Anonymous";
        }
    }
}
