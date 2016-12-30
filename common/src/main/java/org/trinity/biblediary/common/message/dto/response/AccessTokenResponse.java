package org.trinity.biblediary.common.message.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("openid")
    private String openid;

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(final int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setOpenid(final String openid) {
        this.openid = openid;
    }
}
