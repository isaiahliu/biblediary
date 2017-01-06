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
import org.trinity.biblediary.common.message.dto.domain.SignOffSearchingDto;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.SignOff;
import org.trinity.biblediary.repository.business.entity.SignOff_;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;

public interface ISignOffRepository extends IJpaRepository<SignOff, SignOffSearchingDto> {
    @Query("select max(forDate) from SignOff so where user=:user")
    Date getMaxLastForDate(@Param("user") User user);

    @Override
    default Page<SignOff> query(final SignOffSearchingDto searchingDto, final Pageable pagable) {
        final Specification<SignOff> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }

            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(SignOff_.id), searchingDto.getId()));
            }

            if (searchingDto.getStatus().isEmpty()) {
                if (!searchingDto.isSearchAllStatus()) {
                    predicates.add(cb.equal(root.get(SignOff_.status), RecordStatus.ACTIVE));
                }
            } else {
                final In<RecordStatus> in = cb.in(root.get(SignOff_.status));
                searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item)).forEach(item -> in.value(item));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
