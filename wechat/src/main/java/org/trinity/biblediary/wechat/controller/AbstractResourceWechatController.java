package org.trinity.biblediary.wechat.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.message.IMessageResolverChain;
import org.trinity.rest.controller.AbstractWebController;

public class AbstractResourceWechatController extends AbstractWebController {
    @Autowired
    protected IMessageResolverChain messageResolver;

    @Value("${args.dev-mode:false}")
    private boolean devMode;

    @Override
    protected ModelAndView createModelAndView(final String viewName) {
        final ModelAndView modelAndView = super.createModelAndView(viewName).addObject("messageResolver", messageResolver);

        if (devMode) {
            modelAndView.addObject("version", UUID.randomUUID().toString());
        }

        return modelAndView;
    }

    protected ModelAndView createModelAndView(final String viewName, final String errorMessage) {
        return this.createModelAndView(viewName).addObject("error", errorMessage);
    }
}
