package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.SignOffDto;
import org.trinity.biblediary.common.message.dto.domain.SignOffSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.ISignOffProcessController;
import org.trinity.biblediary.repository.business.dataaccess.ISignOffRepository;
import org.trinity.biblediary.repository.business.entity.SignOff;

@Service
public class SignOffProcessController
      extends AbstractAutowiredCrudProcessController<SignOff, SignOffDto, SignOffSearchingDto, ISignOffRepository>
      implements ISignOffProcessController {
}

