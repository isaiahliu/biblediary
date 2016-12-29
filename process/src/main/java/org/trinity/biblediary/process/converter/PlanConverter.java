package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.util.Tuple2;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.biblediary.common.message.dto.domain.PlanDto;
import org.trinity.biblediary.repository.business.entity.Plan;

@Component
public class PlanConverter extends AbstractLookupSupportObjectConverter<Plan, PlanDto> {
private static enum PlanRelationship {
NA
}

@Autowired
public PlanConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
super(lookupConverter);
}

@Override
protected void convertBackInternal(final PlanDto source, final Plan target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected void convertInternal(final Plan source, final PlanDto target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected Plan createFromInstance() {
return new Plan();
}

@Override
protected PlanDto createToInstance() {
return new PlanDto();
}
@Override
protected void convertRelationshipInternal(final Plan source, final PlanDto target, final RelationshipExpression relationshipExpression) {
switch (relationshipExpression.getName(PlanRelationship.class)) {
case NA:
default:
break;
}
}
}
