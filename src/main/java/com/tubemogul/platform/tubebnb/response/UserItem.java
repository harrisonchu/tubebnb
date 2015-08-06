package com.tubemogul.platform.tubebnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kamel.dabwan on 8/6/15.
 */
public class UserItem {

    @JsonProperty("user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
