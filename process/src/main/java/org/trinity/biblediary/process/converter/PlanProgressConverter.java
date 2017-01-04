package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.util.Tuple2;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDto;
import org.trinity.biblediary.repository.business.entity.PlanProgress;

@Component
public class PlanProgressConverter extends AbstractLookupSupportObjectConverter<PlanProgress, PlanProgressDto> {
private static enum PlanProgressRelationship {
NA
}

@Autowired
public PlanProgressConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
super(lookupConverter);
}

@Override
protected void convertBackInternal(final PlanProgressDto source, final PlanProgress target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected void convertInternal(final PlanProgress source, final PlanProgressDto target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected PlanProgress createFromInstance() {
return new PlanProgress();
}

@Override
protected PlanProgressDto createToInstance() {
return new PlanProgressDto();
}
@Override
protected void convertRelationshipInternal(final PlanProgress source, final PlanProgressDto target, final RelationshipExpression relationshipExpression) {
switch (relationshipExpression.getName(PlanProgressRelationship.class)) {
case NA:
default:
break;
}
}
}
