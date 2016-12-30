package org.trinity.biblediary.wechat.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.request.VerificationRequest;
import org.trinity.biblediary.common.message.dto.request.WechatMessageRequest;
import org.trinity.biblediary.common.message.dto.response.WechatMessageResponse;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;

@RestController
@RequestMapping
public class WechatRestController extends AbstractResourceWechatController {
    private static Logger logger = LogManager.getLogger(WechatRestController.class);

    @Autowired
    private IExceptionFactory exceptionFactory;

    @Autowired
    private IWechatProcessController wechatProcessController;
    @Autowired
    private IUserProcessController userProcessController;

    @RequestMapping(value = "ajax/user", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<UserDto> ajaxMe() throws IException {
        return new ResponseEntity<>((UserDto) (SecurityContextHolder.getContext().getAuthentication().getPrincipal()), HttpStatus.OK);
    }

    @RequestMapping(value = "ajax/user", method = RequestMethod.PUT)
    public void ajaxUpdateMe(@RequestBody final UserDto request) throws IException {
        final UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (StringUtils.isEmpty(request.getNickName()) || StringUtils.isEmpty(request.getCellphone())) {
            throw exceptionFactory.createException("Invalid Input");
        }

        final UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNickName(request.getNickName());
        dto.setCellphone(request.getCellphone());

        userProcessController.update(dto);

    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getMenu() throws IException {
        final String menu = wechatProcessController.getMenu();

        logger.debug(menu);

        return menu;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
    public @ResponseBody WechatMessageResponse processMessage(@RequestBody final WechatMessageRequest request) throws IException {
        return wechatProcessController.processMessage(request);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public void refreshMenu() throws IException {
        wechatProcessController.createMenu();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView userPage() {
        return createModelAndView("home");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody String verify(final VerificationRequest request) throws IException {
        return request.getEchostr();
    }
}
