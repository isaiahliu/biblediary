package org.trinity.biblediary.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailDto;
import org.trinity.biblediary.common.message.lookup.BibleVolume;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;

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
        copyObject(source::getFromChapter, target::getFromChapter, target::setFromChapter, copyPolicy);
        copyObject(source::getFromVerse, target::getFromVerse, target::setFromVerse, copyPolicy);
        copyObject(source::getPart, target::getPart, target::setPart, copyPolicy);
        copyObject(source::getToChapter, target::getToChapter, target::setToChapter, copyPolicy);
        copyObject(source::getToVerse, target::getToVerse, target::setToVerse, copyPolicy);

        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
        copyLookup(source::getFromVolume, target::getFromVolume, target::setFromVolume, BibleVolume.class, copyPolicy);
        copyLookup(source::getToVolume, target::getToVolume, target::setToVolume, BibleVolume.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final PlanProgressDetail source, final PlanProgressDetailDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getFromChapter, target::getFromChapter, target::setFromChapter, copyPolicy);
        copyObject(source::getFromVerse, target::getFromVerse, target::setFromVerse, copyPolicy);
        copyObject(source::getPart, target::getPart, target::setPart, copyPolicy);
        copyObject(source::getToChapter, target::getToChapter, target::setToChapter, copyPolicy);
        copyObject(source::getToVerse, target::getToVerse, target::setToVerse, copyPolicy);

        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getFromVolume, target::getFromVolume, target::setFromVolume, copyPolicy);
        copyMessage(source::getToVolume, target::getToVolume, target::setToVolume, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final PlanProgressDetail source, final PlanProgressDetailDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(PlanProgressDetailRelationship.class)) {
        case NA:
        default:
            break;
        }
    }

    @Override
    protected PlanProgressDetail createFromInstance() {
        return new PlanProgressDetail();
    }

    @Override
    protected PlanProgressDetailDto createToInstance() {
        return new PlanProgressDetailDto();
    }
}
