package org.trinity.biblediary.common.message.dto.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "xml")
public class WechatMessageResponse {

    @XmlElement(name = "ToUserName")
    private String toUserName;

    @XmlElement(name = "FromUserName")
    private String fromUserName;

    @XmlElement(name = "CreateTime")
    private Date createTime;

    @XmlElement(name = "MsgType")
    private String messageType;

    @XmlElement(name = "Content")
    private String content;

    public WechatMessageResponse(final String fromUsername, final String toUsername) {
        this.fromUserName = fromUsername;
        this.toUserName = toUsername;
        this.createTime = new Date();
    }

    protected WechatMessageResponse() {
    }

    @XmlTransient
    public String getContent() {
        return content;
    }

    @XmlTransient
    public Date getCreateTime() {
        return createTime;
    }

    @XmlTransient
    public String getFromUserName() {
        return fromUserName;
    }

    @XmlTransient
    public String getMessageType() {
        return messageType;
    }

    @XmlTransient
    public String getToUserName() {
        return toUserName;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setFromUserName(final String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    public void setToUserName(final String toUserName) {
        this.toUserName = toUserName;
    }

}
