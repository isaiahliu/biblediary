package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.util.Tuple2;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.biblediary.common.message.dto.domain.UserChurchApplicationDto;
import org.trinity.biblediary.repository.business.entity.UserChurchApplication;

@Component
public class UserChurchApplicationConverter extends AbstractLookupSupportObjectConverter<UserChurchApplication, UserChurchApplicationDto> {
private static enum UserChurchApplicationRelationship {
NA
}

@Autowired
public UserChurchApplicationConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
super(lookupConverter);
}

@Override
protected void convertBackInternal(final UserChurchApplicationDto source, final UserChurchApplication target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected void convertInternal(final UserChurchApplication source, final UserChurchApplicationDto target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected UserChurchApplication createFromInstance() {
return new UserChurchApplication();
}

@Override
protected UserChurchApplicationDto createToInstance() {
return new UserChurchApplicationDto();
}
@Override
protected void convertRelationshipInternal(final UserChurchApplication source, final UserChurchApplicationDto target, final RelationshipExpression relationshipExpression) {
switch (relationshipExpression.getName(UserChurchApplicationRelationship.class)) {
case NA:
default:
break;
}
}
}
