package org.trinity.biblediary.common.message.dto.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "xml")
public class WechatMessageRequest {

    @XmlElement(name = "ToUserName")
    private String toUserName;

    @XmlElement(name = "FromUserName")
    private String fromUserName;

    @XmlElement(name = "CreateTime")
    private Date createTime;

    @XmlElement(name = "MsgType")
    private String messageType;

    @XmlElement(name = "Event")
    private String event;

    @XmlElement(name = "EventKey")
    private String eventKey;

    @XmlElement(name = "Content")
    private String content;

    @XmlElement(name = "MsgId")
    private String messageId;

    @XmlTransient
    public String getContent() {
        return content;
    }

    @XmlTransient
    public Date getCreateTime() {
        return createTime;
    }

    @XmlTransient
    public String getEvent() {
        return event;
    }

    @XmlTransient
    public String getEventKey() {
        return eventKey;
    }

    @XmlTransient
    public String getFromUserName() {
        return fromUserName;
    }

    @XmlTransient
    public String getMessageId() {
        return messageId;
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

    public void setEvent(final String event) {
        this.event = event;
    }

    public void setEventKey(final String eventKey) {
        this.eventKey = eventKey;
    }

    public void setFromUserName(final String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    public void setToUserName(final String toUserName) {
        this.toUserName = toUserName;
    }

}
