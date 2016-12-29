package org.trinity.biblediary.common.message.dto.request;

public class VerificationRequest {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
    private String openid;

    public String generateSignature(final String token) {
        return "";
    }

    public String getEchostr() {
        return echostr;
    }

    public String getNonce() {
        return nonce;
    }

    public String getOpenid() {
        return openid;
    }

    public String getSignature() {
        return signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setEchostr(final String echostr) {
        this.echostr = echostr;
    }

    public void setNonce(final String nonce) {
        this.nonce = nonce;
    }

    public void setOpenid(final String openid) {
        this.openid = openid;
    }

    public void setSignature(final String signature) {
        this.signature = signature;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
