package org.trinity.biblediary.process.controller.base;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.IProcessController;

public interface IWechatProcessController extends IProcessController {
    String getToken() throws IException;

    String processMessage(String xml) throws IException;

    void verify(String signature, String timestamp, String nonce) throws IException;
}
