package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum JoinMethod implements ILookupMessage<LookupType> {
    ANYONE("A"),
    VERIFICATION_REQUIRED("V");

    private final String messageCode;

    private JoinMethod(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.JOIN_METHOD;
    }

}
