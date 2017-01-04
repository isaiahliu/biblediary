package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.util.Tuple2;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailDto;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;

@Component
public class PlanProgressDetailConverter extends AbstractLookupSupportObjectConverter<PlanProgressDetail, PlanProgressDetailDto> {
private static enum PlanProgressDetailRelationship {
NA
}

@Autowired
public PlanProgressDetailConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
super(lookupConverter);
}

@Override
protected void convertBackInternal(final PlanProgressDetailDto source, final PlanProgressDetail target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected void convertInternal(final PlanProgressDetail source, final PlanProgressDetailDto target, final CopyPolicy copyPolicy) {
copyObject(source::getId, target::getId, target::setId, copyPolicy);
}

@Override
protected PlanProgressDetail createFromInstance() {
return new PlanProgressDetail();
}

@Override
protected PlanProgressDetailDto createToInstance() {
return new PlanProgressDetailDto();
}
@Override
protected void convertRelationshipInternal(final PlanProgressDetail source, final PlanProgressDetailDto target, final RelationshipExpression relationshipExpression) {
switch (relationshipExpression.getName(PlanProgressDetailRelationship.class)) {
case NA:
default:
break;
}
}
}
