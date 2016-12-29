package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.ILookupMessage;
import org.trinity.message.ILookupType;
import org.trinity.message.IMessageType;

public enum LookupType implements ILookupType, ILookupMessage<LookupType> {
    NA("NA", null),
    LOOKUP("LOOKUP", LookupType.class),
    USER_STATUS("URSTAT", UserStatus.class),
    ACCESS_RIGHT("ACCESS", AccessRight.class),
    TOKEN_STATUS("TKSTAT", TokenStatus.class),
    SYSTEM_ATTRIBUTE_KEY("SYSKEY", SystemAttributeKey.class),
    RECORD_STATUS("RCSTAT", RecordStatus.class),
    LANGUAGE("LANGUAGE", Language.class),
    FLAG_STATUS("FLAG", FlagStatus.class);

    private String typeName;

    private Class<? extends ILookupMessage<LookupType>> targetType;

    private LookupType(final String typeName, final Class<? extends ILookupMessage<LookupType>> targetType) {
        this.typeName = typeName;
        this.targetType = targetType;
    }

    @Override
    public String getMessageCode() {
        return typeName;
    }

    @Override
    public LookupType getMessageType() {
        return LOOKUP;
    }

    @Override
    public String getMessageTypeName() {
        return typeName;
    }

    @Override
    public IMessageType getParentType() {
        return GeneralMessageType.LOOKUP;
    }

    @Override
    public Class<? extends ILookupMessage<LookupType>> getTargetType() {
        return targetType;
    }
}
