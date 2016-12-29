package org.trinity.biblediary.process.controller.base;

import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;

public interface IUserProcessController extends ICrudProcessController<UserDto, UserSearchingDto> {

    UserDto getWechatUser(String openid) throws IException;
}
