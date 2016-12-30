package org.trinity.biblediary.wechat.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.message.IMessageResolverChain;
import org.trinity.rest.controller.AbstractWebController;

public class AbstractResourceWechatController extends AbstractWebController {
    @Autowired
    protected IMessageResolverChain messageResolver;

    @Override
    protected ModelAndView createModelAndView(final String viewName) {
        return super.createModelAndView(viewName).addObject("messageResolver", messageResolver).addObject("version",
                UUID.randomUUID().toString());
    }

    protected ModelAndView createModelAndView(final String viewName, final String errorMessage) {
        return this.createModelAndView(viewName).addObject("error", errorMessage);
    }
}
