package com.micro.tremolo.model.params;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 10:26
 */
public class VideoArrayParam {
    private List<VideoParam> data;

    public List<VideoParam> getData() {
        return data;
    }

    public void setData(List<VideoParam> data) {
        this.data = data;
    }

    /*private String userId;
    private String nickname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }*/
}
