package org.trinity.biblediary.process.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.biblediary.common.message.exception.ErrorMessage;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.biblediary.process.controller.base.ISystemAttributeProcessController;
import org.trinity.biblediary.process.controller.base.IWechatProcessController;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.exception.GeneralErrorMessage;

@Service
public class WechatProcessController implements IWechatProcessController {
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
        }

        return token;
    }

    @Override
    public String processMessage(final String xml) throws IException {
        return "<xml><ToUserName><![CDATA[op6zswDJ7S5WJx6198rdCo4MZwDI]]></ToUserName><FromUserName><![CDATA[gh_daf203faf100]]></FromUserName><CreateTime>1483000631</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[hello world]]></Content></xml>";
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
