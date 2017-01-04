package org.trinity.biblediary.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressDetailSearchingDto;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail;
import org.trinity.biblediary.repository.business.entity.PlanProgressDetail_;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;

public interface IPlanProgressDetailRepository extends IJpaRepository<PlanProgressDetail, PlanProgressDetailSearchingDto> {
	@Override
	default Page<PlanProgressDetail> query(final PlanProgressDetailSearchingDto searchingDto, final Pageable pagable) {
		final Specification<PlanProgressDetail> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();
			if (!searchingDto.isSearchAll()) {
			}

			if (searchingDto.getId() != null) {
				predicates.add(cb.equal(root.get(PlanProgressDetail_.id), searchingDto.getId()));
			}

			if (searchingDto.getStatus().isEmpty()) {
				if (!searchingDto.isSearchAllStatus()) {
					predicates.add(cb.equal(root.get(PlanProgressDetail_.status), RecordStatus.ACTIVE));
				}
			} else {
				final In<RecordStatus> in = cb.in(root.get(PlanProgressDetail_.status));
				searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item)).forEach(item -> in.value(item));
				predicates.add(in);
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
		return findAll(specification, pagable);
	}
}
