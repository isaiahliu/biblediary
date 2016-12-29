package org.trinity.biblediary.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.process.controller.base.ISystemAttributeProcessController;
import org.trinity.biblediary.repository.business.dataaccess.ISystemAttributeRepository;
import org.trinity.biblediary.repository.business.entity.SystemAttribute;
import org.trinity.common.exception.IException;

@Service
public class SystemAttributeProcessController implements ISystemAttributeProcessController {
    @Autowired
    private ISystemAttributeRepository systemAttributeRepository;

    @Override
    public String getValue(final SystemAttributeKey key) throws IException {
        SystemAttribute entity = systemAttributeRepository.findOne(key);
        if (entity == null) {
            entity = new SystemAttribute();
            entity.setId(key);
            entity.setValue(key.getDefaultValue());
            entity.setStatus(RecordStatus.ACTIVE);

            systemAttributeRepository.save(entity);
        }

        return entity.getValue();
    }

    @Override
    public void setValue(final SystemAttributeKey key, final String value) throws IException {
        SystemAttribute entity = systemAttributeRepository.findOne(key);

        if (entity == null) {
            entity = new SystemAttribute();
            entity.setId(key);
            entity.setStatus(RecordStatus.ACTIVE);
        }

        entity.setValue(value);
        systemAttributeRepository.save(entity);
    }
}
