package org.trinity.biblediary.process.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IUserRepository;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.common.exception.IException;

@Service
public class UserProcessController extends AbstractAutowiredCrudProcessController<User, UserDto, UserSearchingDto, IUserRepository>
        implements IUserProcessController {

    @Override
    @Transactional(rollbackOn = IException.class)
    public UserDto getWechatUser(final String openid) throws IException {
        User user = getDomainEntityRepository().findOneByWechat(openid);
        if (user == null) {
            user = new User();
            user.setAdmin(FlagStatus.NO);
            user.setCellphone(null);
            user.setChurch(null);
            user.setNickName(null);
            user.setPlan(null);
            user.setWechat(openid);
            user.setStatus(UserStatus.GUEST);

            getDomainEntityRepository().save(user);
        }
        return getDomainObjectConverter().convert(user);
    }
}
