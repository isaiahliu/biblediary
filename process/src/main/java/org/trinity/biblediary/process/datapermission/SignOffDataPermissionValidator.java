package org.trinity.biblediary.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.biblediary.repository.business.entity.SignOff;

@Component
public class SignOffDataPermissionValidator
        extends AbstractDataPermissionValidator<SignOff> {
    @Override
    public void checkSpecialPermission() throws IException {
    super.checkSpecialPermission();
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

