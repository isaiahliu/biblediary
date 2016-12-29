package org.trinity.biblediary.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.biblediary.repository.business.entity.User_;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;

public interface IUserRepository extends IJpaRepository<User, UserSearchingDto> {
    User findOneByWechat(String wechat);

    @Override
    default Page<User> query(final UserSearchingDto searchingDto, final Pageable pagable) {
        final Specification<User> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }

            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(User_.id), searchingDto.getId()));
            }

            if (searchingDto.getStatus().isEmpty()) {
                if (!searchingDto.isSearchAllStatus()) {
                    predicates.add(cb.equal(root.get(User_.status), UserStatus.MEMBER));
                }
            } else {
                final In<UserStatus> in = cb.in(root.get(User_.status));
                searchingDto.getStatus().stream().map(item -> LookupParser.parse(UserStatus.class, item)).forEach(item -> in.value(item));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
