package org.trinity.biblediary.process.controller.base;

import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.common.exception.IException;
import org.trinity.process.controller.IProcessController;

public interface ISystemAttributeProcessController extends IProcessController {
    String getValue(SystemAttributeKey key) throws IException;

    void setValue(SystemAttributeKey key, String value) throws IException;
}
