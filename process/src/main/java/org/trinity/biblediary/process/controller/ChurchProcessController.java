package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.ChurchDto;
import org.trinity.biblediary.common.message.dto.domain.ChurchSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IChurchProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IChurchRepository;
import org.trinity.biblediary.repository.business.entity.Church;

@Service
public class ChurchProcessController
      extends AbstractAutowiredCrudProcessController<Church, ChurchDto, ChurchSearchingDto, IChurchRepository>
      implements IChurchProcessController {
}

