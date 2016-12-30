package org.trinity.biblediary.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.biblediary.common.message.dto.response.AccessTokenResponse;
import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.biblediary.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.biblediary.process.controller.base.ISystemAttributeProcessController;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.repository.business.dataaccess.IUserRepository;
import org.trinity.biblediary.repository.business.entity.User;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;

@Service
public class UserProcessController extends AbstractAutowiredCrudProcessController<User, UserDto, UserSearchingDto, IUserRepository>
        implements IUserProcessController {
    @Autowired
    private ISystemAttributeProcessController systemAttributeProcessController;

    @Override
    public UserDto authenticateWechatUserByCode(final String code, final String session) throws IException {
        final User oldUser = getDomainEntityRepository().findOneBySession(session);

        final String appId = systemAttributeProcessController.getValue(SystemAttributeKey.APP_ID);
        final String appSec = systemAttributeProcessController.getValue(SystemAttributeKey.APP_SEC);

        final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        final List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);

        converter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverters.add(converter);

        final RestTemplate restTemplate = new RestTemplate(messageConverters);

        final String url = String.format(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, appSec,
                code);
        final AccessTokenResponse response = restTemplate.getForEntity(url, AccessTokenResponse.class).getBody();

        User newUser = getDomainEntityRepository().findOneByWechat(response.getOpenid());
        if (newUser == null) {
            if (oldUser != null) {
                oldUser.setSession(null);
                getDomainEntityRepository().save(oldUser);
            }

            newUser = new User();
            newUser.setAdmin(FlagStatus.NO);
            newUser.setCellphone(null);
            newUser.setChurch(null);
            newUser.setNickName(null);
            newUser.setPlan(null);
            newUser.setWechat(response.getOpenid());
            newUser.setStatus(UserStatus.GUEST);
            newUser.setSession(session);

            getDomainEntityRepository().save(newUser);
        } else {
            if (oldUser != null) {
                if (!newUser.getId().equals(oldUser.getId())) {
                    oldUser.setSession(null);
                    getDomainEntityRepository().save(oldUser);
                }

                newUser.setSession(session);
                getDomainEntityRepository().save(newUser);
            }
        }
        return getDomainObjectConverter().convert(newUser);
    }

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

    @Override
    public UserDto getWechatUserBySession(final String session) throws IException {
        final User user = getDomainEntityRepository().findOneBySession(session);
        if (user == null) {
            return null;
        }

        return getDomainObjectConverter().convert(user);
    }

    @Override
    @Transactional(rollbackOn = IException.class)
    public void update(final UserDto userDto) throws IException {
        final User user = getDomainEntityRepository().findOne(userDto.getId());

        getDomainObjectConverter().convertBack(userDto, user, CopyPolicy.SOURCE_IS_NOT_NULL);

        getDomainEntityRepository().save(user);
    }
}
