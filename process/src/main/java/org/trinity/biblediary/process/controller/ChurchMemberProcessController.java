package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.ChurchMemberDto;
import org.trinity.biblediary.common.message.dto.domain.ChurchMemberSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IChurchMemberProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IChurchMemberRepository;
import org.trinity.biblediary.repository.business.entity.ChurchMember;

@Service
public class ChurchMemberProcessController
      extends AbstractAutowiredCrudProcessController<ChurchMember, ChurchMemberDto, ChurchMemberSearchingDto, IChurchMemberRepository>
      implements IChurchMemberProcessController {
}

