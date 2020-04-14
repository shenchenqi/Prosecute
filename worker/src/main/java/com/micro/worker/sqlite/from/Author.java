package com.micro.worker.sqlite.from;

import java.io.Serializable;

/**
 * @Author KiLin
 * @Time 2020/4/14 17:17
 */
public class Author implements Serializable, Cloneable {
    private String id;//用户ID
    private String kwaiId;//快手号
    private String name;//名称
    private String alias;//昵称
    private String text;//签名
    private String avatar;//头像
    private boolean verified;//是否有标签
    private String verifiedText;//标签
    private int fanCount;//粉丝
    private int photoCount;//作品
    private int followCount;//关注
    private int momentCount;//动态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKwaiId() {
        return kwaiId;
    }

    public void setKwaiId(String kwaiId) {
        this.kwaiId = kwaiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerifiedText() {
        return verifiedText;
    }

    public void setVerifiedText(String verifiedText) {
        this.verifiedText = verifiedText;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getMomentCount() {
        return momentCount;
    }

    public void setMomentCount(int momentCount) {
        this.momentCount = momentCount;
    }
}