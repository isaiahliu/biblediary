package org.trinity.biblediary.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.biblediary.common.message.dto.domain.PlanDto;
import org.trinity.biblediary.common.message.dto.domain.PlanSearchingDto;

public interface IPlanProcessController extends ICrudProcessController<PlanDto, PlanSearchingDto> {
}

