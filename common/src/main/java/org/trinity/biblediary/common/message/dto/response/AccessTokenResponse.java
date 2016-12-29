package org.trinity.biblediary.common.message.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(final int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
