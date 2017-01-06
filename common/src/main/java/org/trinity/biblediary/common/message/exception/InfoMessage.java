package org.trinity.biblediary.common.message.exception;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.IMessage;

public enum InfoMessage implements IMessage<GeneralMessageType> {
    YOUR_PROGRESS_IS,
    YOU_HAVE_NO_PLANS,
    SIGN_UP_DONE,
    PLAN_PROGRESS,
    BACKDATED_SIGN_UP_DONE,
    PROGRESS_DATE;
    @Override
    public String getMessageCode() {
        return name();
    }

    @Override
    public GeneralMessageType getMessageType() {
        return GeneralMessageType.INFO;
    }
}
