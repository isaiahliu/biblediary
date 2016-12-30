package org.trinity.biblediary.process.controller.base;

import org.trinity.biblediary.common.message.dto.request.WechatMessageRequest;
import org.trinity.biblediary.common.message.dto.response.WechatMessageResponse;
import org.trinity.common.exception.IException;
import org.trinity.process.controller.IProcessController;

public interface IWechatProcessController extends IProcessController {
    void createMenu() throws IException;

    String getMenu() throws IException;

    String getToken() throws IException;

    WechatMessageResponse processMessage(WechatMessageRequest request) throws IException;

    void verify(String signature, String timestamp, String nonce) throws IException;
}
