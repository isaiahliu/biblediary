package org.trinity.biblediary.process.controller.base;

import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;

public interface IUserProcessController extends ICrudProcessController<UserDto, UserSearchingDto> {
	UserDto authenticateWechatUserByCode(String code, String session) throws IException;

	UserDto getMe(UserSearchingDto searchingDto);

	String getProgress() throws IException;

	UserDto getWechatUser(String openid) throws IException;

	UserDto getWechatUserBySession(String session) throws IException;

	void joinPlan(Long planId) throws IException;

	String signup() throws IException;

	void update(UserDto user) throws IException;
}
