package org.trinity.biblediary.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.repository.business.entity.SystemAttribute;

public interface ISystemAttributeRepository extends CrudRepository<SystemAttribute, SystemAttributeKey> {
}
