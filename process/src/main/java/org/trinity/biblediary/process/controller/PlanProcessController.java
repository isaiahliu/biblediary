package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.PlanDto;
import org.trinity.biblediary.common.message.dto.domain.PlanSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IPlanProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IPlanRepository;
import org.trinity.biblediary.repository.business.entity.Plan;

@Service
public class PlanProcessController
      extends AbstractAutowiredCrudProcessController<Plan, PlanDto, PlanSearchingDto, IPlanRepository>
      implements IPlanProcessController {
}

