package org.trinity.biblediary.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.biblediary.common.message.dto.domain.ChurchMemberDto;
import org.trinity.biblediary.common.message.dto.domain.ChurchMemberSearchingDto;

public interface IChurchMemberProcessController extends ICrudProcessController<ChurchMemberDto, ChurchMemberSearchingDto> {
}

