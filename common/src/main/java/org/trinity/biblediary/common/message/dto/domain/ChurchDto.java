package org.trinity.biblediary.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ChurchDto extends AbstractBusinessDto {

    private String description;

    private String name;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
