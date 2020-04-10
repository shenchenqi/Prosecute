package com.micro.tremolo.sqlite.table;

import java.io.Serializable;

public class UserIdModelTable implements Serializable /*extends DataTable*/ {
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