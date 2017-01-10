package org.trinity.biblediary.wechat.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.biblediary.common.message.dto.domain.ChurchSearchingDto;
import org.trinity.biblediary.common.message.dto.domain.PlanSearchingDto;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.domain.UserSearchingDto;
import org.trinity.biblediary.common.message.dto.request.VerificationRequest;
import org.trinity.biblediary.common.message.dto.request.WechatMessageRequest;
import org.trinity.biblediary.common.message.dto.response.ChurchResponse;
import org.trinity.biblediary.common.message.dto.response.PlanResponse;
import org.trinity.biblediary.common.message.dto.response.WechatMessageResponse;
import org.trinity.biblediary.process.controller.base.IChurchProcessController;
import org.trinity.biblediary.process.controller.base.ILookupProcessController;
import org.trinity.biblediary.process.controller.base.IPlanProcessController;
import org.trinity.biblediary.process.controller.base.IUserProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.dto.object.LookupResponse;
import org.trinity.common.dto.response.DefaultResponse;
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

    @Autowired
    private ILookupProcessController lookupProcessController;

    @Autowired
    private IChurchProcessController churchProcessController;

    @Autowired
    private IPlanProcessController planProcessController;

    @RequestMapping(value = "ajax/church", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ChurchResponse> ajaxChurches(final ChurchSearchingDto searchingDto) throws IException {
        final ChurchResponse response = new ChurchResponse();

        response.addData(churchProcessController.getAll(searchingDto).getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "ajax/plan/join/{entityId}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<DefaultResponse> ajaxJoinPlan(@PathVariable("entityId") final Long entityId) throws IException {

        userProcessController.joinPlan(entityId);

        return new ResponseEntity<>(new DefaultResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "ajax/user", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<UserDto> ajaxMe(final UserSearchingDto searchingDto) throws IException {
        final UserDto result = userProcessController.getMe(searchingDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "ajax/plan", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<PlanResponse> ajaxPlans(final PlanSearchingDto searchingDto) throws IException {
        final PlanResponse response = new PlanResponse();

        response.addData(planProcessController.getAll(searchingDto).getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
        dto.setTimeZone(request.getTimeZone());
        userProcessController.update(dto);

    }

    @RequestMapping(value = "ajax/lookup/{type}", method = RequestMethod.GET)
    public ResponseEntity<LookupResponse> getLookupsByType(@PathVariable("type") final String lookupType) throws IException {
        final LookupResponse response = new LookupResponse();

        response.getData().addAll(lookupProcessController.getLookupsByType(lookupType));

        return new ResponseEntity<>(response, HttpStatus.OK);
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
