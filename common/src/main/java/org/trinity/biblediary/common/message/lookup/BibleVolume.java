package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum BibleVolume implements ILookupMessage<LookupType> {
    ;

    private final String messageCode;

    private BibleVolume(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.BIBLE_VOLUME;
    }

}
