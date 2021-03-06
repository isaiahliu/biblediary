package org.trinity.biblediary.common.message.lookup;

import org.trinity.common.accessright.IAccessRight;

public enum AccessRight implements IAccessRight<LookupType> {
    ;
    private final String messageCode;
    private final AccessRight parentAccessRight;

    private AccessRight(final String messageCode, final AccessRight parentAccessRight) {
        this.messageCode = messageCode;
        this.parentAccessRight = parentAccessRight;
    }

    @Override
    public String getAuthority() {
        return messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.ACCESS_RIGHT;
    }

    @Override
    public AccessRight getParentAccessRight() {
        return parentAccessRight;
    }
}
