package com.micro.tremolo.model.params;

/**
 * @Author KiLin
 * @Time 2020/4/15 10:24
 */
public class UserIdParam extends BaseParam {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String sceUserId;

    public String getSceUserId() {
        return sceUserId;
    }

    public void setSceUserId(String sceUserId) {
        this.sceUserId = sceUserId;
    }
}
