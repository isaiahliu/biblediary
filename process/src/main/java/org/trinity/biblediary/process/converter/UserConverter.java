package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.dto.domain.ChurchDto;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.TimeZone;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.biblediary.repository.business.entity.Church;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;

@Component
public class UserConverter extends AbstractLookupSupportObjectConverter<User, UserDto> {
    private static enum UserRelationship {
        CHURCH,
        NA
    }

    @Autowired
    private IObjectConverter<Church, ChurchDto> churchConverter;

    @Autowired
    public UserConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final UserDto source, final User target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getNickName, target::getNickName, target::setNickName, copyPolicy);
        copyObject(source::getWechat, target::getWechat, target::setWechat, copyPolicy);

        copyLookup(source::getStatus, target::getStatus, target::setStatus, UserStatus.class, copyPolicy);
        copyLookup(source::getAdmin, target::getAdmin, target::setAdmin, FlagStatus.class, copyPolicy);
        copyLookup(source::getTimeZone, target::getTimeZone, target::setTimeZone, TimeZone.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final User source, final UserDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getNickName, target::getNickName, target::setNickName, copyPolicy);
        copyObject(source::getWechat, target::getWechat, target::setWechat, copyPolicy);

        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getAdmin, target::getAdmin, target::setAdmin, copyPolicy);
        copyMessage(source::getTimeZone, target::getTimeZone, target::setTimeZone, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final User source, final UserDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(UserRelationship.class)) {
        case CHURCH:
            copyRelationship(source::getChurch, target::setChurch, churchConverter, relationshipExpression);
            break;
        case NA:
        default:
            break;
        }
    }

    @Override
    protected User createFromInstance() {
        return new User();
    }

    @Override
    protected UserDto createToInstance() {
        return new UserDto();
    }
}
