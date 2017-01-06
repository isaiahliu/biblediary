package org.trinity.biblediary.common.message.dto.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class PlanProgressDto extends AbstractBusinessDto {
    private List<PlanProgressDetailDto> details;

    private Date date;

    public Date getDate() {
        return date;
    }

    public List<PlanProgressDetailDto> getDetails() {
        if (details == null) {
            details = new ArrayList<>();
        }
        return details;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setDetails(final List<PlanProgressDetailDto> details) {
        this.details = details;
    }
}
