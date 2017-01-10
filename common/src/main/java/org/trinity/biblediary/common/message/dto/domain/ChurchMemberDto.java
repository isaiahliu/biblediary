package org.trinity.biblediary.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ChurchMemberDto extends AbstractBusinessDto {
    private LookupDto admin;

    public LookupDto getAdmin() {
        return admin;
    }

    public void setAdmin(final LookupDto admin) {
        this.admin = admin;
    }

}
