package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.dto.domain.PlanDto;
import org.trinity.biblediary.common.message.lookup.PlanName;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.Plan;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;

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

		copyLookup(source::getName, target::getName, target::setName, PlanName.class, copyPolicy);
		copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
	}

	@Override
	protected void convertInternal(final Plan source, final PlanDto target, final CopyPolicy copyPolicy) {
		copyObject(source::getId, target::getId, target::setId, copyPolicy);

		copyMessage(source::getName, target::getName, target::setName, copyPolicy);
		copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
	}

	@Override
	protected void convertRelationshipInternal(final Plan source, final PlanDto target,
			final RelationshipExpression relationshipExpression) {
		switch (relationshipExpression.getName(PlanRelationship.class)) {
			case NA:
			default:
				break;
		}
	}

	@Override
	protected Plan createFromInstance() {
		return new Plan();
	}

	@Override
	protected PlanDto createToInstance() {
		return new PlanDto();
	}
}
