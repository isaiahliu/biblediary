package org.trinity.biblediary.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailDto;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailSearchingDto;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IPlanProgressDetailProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IPlanProgressDetailRepository;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;

@Service
public class PlanProgressDetailProcessController
      extends AbstractAutowiredCrudProcessController<PlanProgressDetail, PlanProgressDetailDto, PlanProgressDetailSearchingDto, IPlanProgressDetailRepository>
      implements IPlanProgressDetailProcessController {
}

