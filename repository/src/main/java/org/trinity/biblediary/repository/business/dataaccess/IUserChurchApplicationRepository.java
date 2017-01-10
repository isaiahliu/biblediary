package org.trinity.biblediary.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.biblediary.common.message.dto.domain.UserChurchApplicationSearchingDto;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.repository.business.entity.UserChurchApplication;
import org.trinity.biblediary.repository.business.entity.UserChurchApplication_;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;

public interface IUserChurchApplicationRepository extends IJpaRepository<UserChurchApplication, UserChurchApplicationSearchingDto> {
    @Override
    default Page<UserChurchApplication> query(final UserChurchApplicationSearchingDto searchingDto, final Pageable pagable) {
        final Specification<UserChurchApplication> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }

            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(UserChurchApplication_.id), searchingDto.getId()));
            }

            if (searchingDto.getStatus().isEmpty()) {
                if (!searchingDto.isSearchAllStatus()) {
                    predicates.add(cb.equal(root.get(UserChurchApplication_.status), RecordStatus.ACTIVE));
                }
            } else {
                final In<RecordStatus> in = cb.in(root.get(UserChurchApplication_.status));
                searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item)).forEach(item -> in.value(item));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
