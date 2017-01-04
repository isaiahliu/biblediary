package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.util.Tuple2;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.biblediary.common.message.dto.domain.SignOffDto;
import org.trinity.biblediary.repository.business.entity.SignOff;

@Component
public class SignOffConverter extends AbstractLookupSupportObjectConverter<SignOff, SignOffDto> {
private static enum SignOffRelationship {
NA
}

@Autowired
public SignOffConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
super(lookupConverter);
}

@Override
protected void convertBackInternal(final SignOffDto source, final SignOff target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected void convertInternal(final SignOff source, final SignOffDto target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected SignOff createFromInstance() {
return new SignOff();
}

@Override
protected SignOffDto createToInstance() {
return new SignOffDto();
}
@Override
protected void convertRelationshipInternal(final SignOff source, final SignOffDto target, final RelationshipExpression relationshipExpression) {
switch (relationshipExpression.getName(SignOffRelationship.class)) {
case NA:
default:
break;
}
}
}
