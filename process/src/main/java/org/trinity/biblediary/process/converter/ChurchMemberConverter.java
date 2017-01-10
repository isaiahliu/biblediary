package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.dto.domain.ChurchMemberDto;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.repository.business.entity.ChurchMember;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;

@Component
public class ChurchMemberConverter extends AbstractLookupSupportObjectConverter<ChurchMember, ChurchMemberDto> {
    private static enum ChurchMemberRelationship {
        NA
    }

    @Autowired
    public ChurchMemberConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ChurchMemberDto source, final ChurchMember target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getAdmin, target::getAdmin, target::setAdmin, FlagStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ChurchMember source, final ChurchMemberDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getAdmin, target::getAdmin, target::setAdmin, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ChurchMember source, final ChurchMemberDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ChurchMemberRelationship.class)) {
        case NA:
        default:
            break;
        }
    }

    @Override
    protected ChurchMember createFromInstance() {
        return new ChurchMember();
    }

    @Override
    protected ChurchMemberDto createToInstance() {
        return new ChurchMemberDto();
    }
}
