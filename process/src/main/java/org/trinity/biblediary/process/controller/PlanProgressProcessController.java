package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDto;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IPlanProgressProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IPlanProgressRepository;
import org.trinity.biblediary.repository.business.entity.PlanProgress;

@Service
public class PlanProgressProcessController
      extends AbstractAutowiredCrudProcessController<PlanProgress, PlanProgressDto, PlanProgressSearchingDto, IPlanProgressRepository>
      implements IPlanProgressProcessController {
}

