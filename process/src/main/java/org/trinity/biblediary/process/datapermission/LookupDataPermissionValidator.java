package org.trinity.biblediary.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.biblediary.repository.business.entity.Lookup;
import org.trinity.common.exception.IException;

@Component
public class LookupDataPermissionValidator extends AbstractDataPermissionValidator<Lookup> {
    @Override
    public void checkSpecialPermission() throws IException {
        super.checkSpecialPermission();
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
