package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum SystemAttributeKey implements ILookupMessage<LookupType> {
    WECHAT_TOKEN("WE_TKN", "TRINITY"),
    ACCESS_TOKEN_EXPIRE_TIME("ATK_EXPT", "19000101000000"),
    ACCESS_TOKEN("ATK", ""),
    APP_ID("APP_ID", ""),
    APP_SEC("APP_SEC", ""),
    SERVER("SERVER", "http://server"),
    BUTTON_CONFIG("BTN_CFG", "{}");

    private final String messageCode;
    private String defaultValue;

    private SystemAttributeKey(final String messageCode, final String defaultValue) {
        this.messageCode = messageCode;
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.SYSTEM_ATTRIBUTE_KEY;
    }
}
