package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum PlanName implements ILookupMessage<LookupType> {
    DAILY_BIBLE_2017("DAILY_17");

    private final String messageCode;

    private PlanName(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.PLAN_NAME;
    }

}
