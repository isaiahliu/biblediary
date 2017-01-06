package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.dto.domain.ChurchDto;
import org.trinity.biblediary.repository.business.entity.Church;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;

@Component
public class ChurchConverter extends AbstractLookupSupportObjectConverter<Church, ChurchDto> {
    private static enum ChurchRelationship {
        NA
    }

    @Autowired
    public ChurchConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ChurchDto source, final Church target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
    }

    @Override
    protected void convertInternal(final Church source, final ChurchDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final Church source, final ChurchDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ChurchRelationship.class)) {
        case NA:
        default:
            break;
        }
    }

    @Override
    protected Church createFromInstance() {
        return new Church();
    }

    @Override
    protected ChurchDto createToInstance() {
        return new ChurchDto();
    }
}
