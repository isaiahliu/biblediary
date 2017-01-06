package org.trinity.biblediary.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class PlanDto extends AbstractBusinessDto {
    private LookupDto name;

    public LookupDto getName() {
        return name;
    }

    public void setName(final LookupDto name) {
        this.name = name;
    }
}
