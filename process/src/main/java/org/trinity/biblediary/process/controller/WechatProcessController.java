package org.trinity.biblediary.process.controller;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trinity.biblediary.common.message.dto.request.WechatMessageRequest;
import org.trinity.biblediary.common.message.dto.response.AccessTokenResponse;
import org.trinity.biblediary.common.message.dto.response.WechatMessageResponse;
import org.trinity.biblediary.common.message.exception.ErrorMessage;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.process.controller.base.ISystemAttributeProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.exception.GeneralErrorMessage;

@Service
public class WechatProcessController implements IWechatProcessController {
	private static Logger logger = LogManager.getLogger(WechatProcessController.class);

	@Autowired
	private IExceptionFactory exceptionFactory;

	@Autowired
	private ISystemAttributeProcessController systemAttributeProcessController;

	@Override
	public String getToken() throws IException {
		final Date now = new Date();

		final String expireTimeStr = systemAttributeProcessController.getValue(SystemAttributeKey.ACCESS_TOKEN_EXPIRE_TIME);
		String token = null;
		final DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			final Date expireTime = format.parse(expireTimeStr);

			if (now.before(expireTime)) {
				token = systemAttributeProcessController.getValue(SystemAttributeKey.ACCESS_TOKEN);
			}
		} catch (final ParseException e) {
		}

		if (token == null) {
			final String appId = systemAttributeProcessController.getValue(SystemAttributeKey.APP_ID);
			final String appSec = systemAttributeProcessController.getValue(SystemAttributeKey.APP_SEC);

			final RestTemplate restTemplate = new RestTemplate();
			final AccessTokenResponse response = restTemplate.getForEntity(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSec,
					AccessTokenResponse.class).getBody();

			token = response.getAccessToken();

			systemAttributeProcessController.setValue(SystemAttributeKey.ACCESS_TOKEN, token);

			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.SECOND, response.getExpiresIn());

			systemAttributeProcessController.setValue(SystemAttributeKey.ACCESS_TOKEN_EXPIRE_TIME, format.format(calendar.getTime()));
		}

		return token;
	}

	@Override
	public WechatMessageResponse processMessage(final WechatMessageRequest request) throws IException {
		final WechatMessageResponse response = new WechatMessageResponse(request.getToUserName(), request.getFromUserName());
		switch (request.getMessageType()) {
			case "event":
				switch (request.getEvent()) {
					case "CLICK":
						switch (request.getEventKey()) {
							case "KEY_PROGRESS":
								response.setMessageType("text");
								response.setContent("你应该重头读/:B-)");
								break;
							case "KEY_SIGNUP":
								response.setMessageType("text");
								response.setContent("真的?/:<@");
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
				break;
			case "text":
				logger.debug(request.getContent());
				response.setMessageType("text");
				response.setContent("你说的啥?/::D");

				break;
			default:

				break;
		}
		return response;
	}

	@Override
	public void refreshMenu() throws IException {
		final String token = getToken();

		final String server = systemAttributeProcessController.getValue(SystemAttributeKey.SERVER);
		final String buttonConfig = systemAttributeProcessController.getValue(SystemAttributeKey.BUTTON_CONFIG);

		final HttpMessageConverter<String> converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		final List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(converter);

		final RestTemplate restTemplate = new RestTemplate(converters);

		final String buttons = buttonConfig.replaceAll("@SERVER@", server);

		restTemplate.postForLocation("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token, buttons);
	}

	@Override
	public void verify(final String signature, final String timestamp, final String nonce) throws IException {
		final String[] strs = { timestamp, nonce, systemAttributeProcessController.getValue(SystemAttributeKey.WECHAT_TOKEN) };

		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA1");

			final String str = String.join("", Arrays.stream(strs).sorted().toArray(String[]::new));

			final byte[] results = digest.digest(str.getBytes());

			final StringBuilder sig = new StringBuilder();

			for (final byte result : results) {
				final int temp = Byte.toUnsignedInt(result);
				sig.append(Integer.toString(temp >> 4, 16));
				sig.append(Integer.toString(temp % 16, 16));
			}

			if (!sig.toString().equals(signature)) {
				throw exceptionFactory.createException(ErrorMessage.INCORRECT_SIGNATURE);
			}
		} catch (final NoSuchAlgorithmException e) {
			throw exceptionFactory.createException(GeneralErrorMessage.UNKNOWN_EXCEPTION, e.getMessage());
		}
	}
}
