package org.trinity.biblediary.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.biblediary.common.message.dto.domain.SignOffDto;
import org.trinity.biblediary.common.message.dto.domain.SignOffSearchingDto;

public interface ISignOffProcessController extends ICrudProcessController<SignOffDto, SignOffSearchingDto> {
}

