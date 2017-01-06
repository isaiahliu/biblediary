package org.trinity.biblediary.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trinity.biblediary.common.message.dto.domain.PlanProgressSearchingDto;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.Plan;
import org.trinity.biblediary.repository.business.entity.PlanProgress;
import org.trinity.biblediary.repository.business.entity.PlanProgress_;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;

public interface IPlanProgressRepository extends IJpaRepository<PlanProgress, PlanProgressSearchingDto> {
    @Query("select pp from PlanProgress pp where plan=:plan and date > :lastForDate and (:status is null or status=:status) order by date")
    Page<PlanProgress> getNextPlanProgress(@Param("plan") Plan plan, @Param("lastForDate") Date lastForDate,
            @Param("status") RecordStatus status, Pageable pagable);

    @Query("select min(date) from PlanProgress pp where :user member of plan.users and date > :lastForDate and (:status is null or status=:status)")
    Date getNextForDate(@Param("user") User user, @Param("lastForDate") Date lastForDate, @Param("status") RecordStatus status);

    @Override
    default Page<PlanProgress> query(final PlanProgressSearchingDto searchingDto, final Pageable pagable) {
        final Specification<PlanProgress> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }

            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(PlanProgress_.id), searchingDto.getId()));
            }

            if (searchingDto.getStatus().isEmpty()) {
                if (!searchingDto.isSearchAllStatus()) {
                    predicates.add(cb.equal(root.get(PlanProgress_.status), RecordStatus.ACTIVE));
                }
            } else {
                final In<RecordStatus> in = cb.in(root.get(PlanProgress_.status));
                searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item)).forEach(item -> in.value(item));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
