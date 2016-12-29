package org.trinity.biblediary.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.trinity.biblediary.common.message.dto.domain.UserDto;
import org.trinity.biblediary.common.message.dto.request.VerificationRequest;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping
public class WechatRestController {
    private static Logger logger = LogManager.getLogger(WechatRestController.class);

    @Autowired
    private IWechatProcessController wechatProcessController;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processMessage(final HttpServletRequest httpRequest, final VerificationRequest request, @RequestBody final String xml)
            throws IException {
        final UserDto user = (UserDto) ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
                .getPrincipal();

        logger.debug(user.getWechat() + " process message");

        return wechatProcessController.processMessage(xml);
    }

    @RequestMapping(value = "/menu/refresh", method = RequestMethod.POST)
    public void refreshMenu() {
        final RestTemplate restTemplate = new RestTemplate();

        final Object result = restTemplate.postForLocation(
                "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=wuVFdROl-qjhkBUKa5Ew45iFG2Otxrb9tOJU-5CmSEFB4-oyPwyPUG-T1CeA89Ls-WiHBtpJObl8NdMUX5gPATPIsGTgw9OBWP25pMwmfoKGldS_c1O6pUxvd7VNdj1eQRVjACAFGK",
                "{\"button\":[{\"type\":\"click\",\"name\":\"TEST1\",\"key\":\"V1001_TEST\"}]}");

        logger.debug(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody String verify(final VerificationRequest request) throws IException {
        return request.getEchostr();
    }
}
