package org.trinity.biblediary.common.message.exception;

import org.trinity.message.exception.IErrorMessage;

public enum ErrorMessage implements IErrorMessage {
    UNABLE_TO_FIND_USER,
    WRONG_PASSWORD,
    TOKEN_IS_NOT_AUTHENTICATED,
    NEW_PASSWORD_SHOULD_BE_DIFFERENT,
    UNABLE_TO_ACCESS_USER,
    INCORRECT_SIGNATURE,
    LOGGED_BY_OTHERS,
    PASSWORD_CHANGED,
    TOKEN_IS_EXPIRED,
    USER_IS_DISABLED,
    NOT_JOINED_THIS_PLAN,
    PLAN_HAS_FINISHED,
    YOUR_NEXT_PROGRESS_WILL_START_ON;
    @Override
    public String getMessageCode() {
        return name();
    }
}
