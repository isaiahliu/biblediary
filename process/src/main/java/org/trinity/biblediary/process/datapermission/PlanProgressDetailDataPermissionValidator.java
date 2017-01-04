package org.trinity.biblediary.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;

@Component
public class PlanProgressDetailDataPermissionValidator
        extends AbstractDataPermissionValidator<PlanProgressDetail> {
    @Override
    public void checkSpecialPermission() throws IException {
    super.checkSpecialPermission();
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

