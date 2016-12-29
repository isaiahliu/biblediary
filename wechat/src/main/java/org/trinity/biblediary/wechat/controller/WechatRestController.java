package org.trinity.biblediary.wechat.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.biblediary.common.message.dto.request.VerificationRequest;
import org.trinity.biblediary.common.message.dto.request.WechatMessageRequest;
import org.trinity.biblediary.common.message.dto.response.WechatMessageResponse;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping
public class WechatRestController {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(WechatRestController.class);

	@Autowired
	private IWechatProcessController wechatProcessController;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody WechatMessageResponse processMessage(@RequestBody final WechatMessageRequest request) throws IException {
		return wechatProcessController.processMessage(request);
	}

	@RequestMapping(value = "/menu/refresh", method = RequestMethod.POST)
	public void refreshMenu() throws IException {
		wechatProcessController.refreshMenu();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody String verify(final VerificationRequest request) throws IException {
		return request.getEchostr();
	}
}
