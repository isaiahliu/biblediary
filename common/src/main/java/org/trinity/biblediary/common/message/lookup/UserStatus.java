package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum UserStatus implements ILookupMessage<LookupType> {
    GUEST("G"),
    MEMBER("M"),
    DESTROYED("D");

    private final String messageCode;

    private UserStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.USER_STATUS;
    }

}
