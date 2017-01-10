package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.UserChurchApplicationDto;
import org.trinity.biblediary.common.message.dto.domain.UserChurchApplicationSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IUserChurchApplicationProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IUserChurchApplicationRepository;
import org.trinity.biblediary.repository.business.entity.UserChurchApplication;

@Service
public class UserChurchApplicationProcessController extends
        AbstractAutowiredCrudProcessController<UserChurchApplication, UserChurchApplicationDto, UserChurchApplicationSearchingDto, IUserChurchApplicationRepository>
        implements IUserChurchApplicationProcessController {
}
