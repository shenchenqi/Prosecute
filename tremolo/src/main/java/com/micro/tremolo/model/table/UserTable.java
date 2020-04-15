package com.micro.tremolo.model.table;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 10:57
 */
public class UserTable extends DataTable {
    private String userId;
    private String sceUserId;
    private String nickname;
    private String tremoloId;
    private String tremoloNumberId;
    private String birthday;
    private String city;
    private String country;
    private String district;
    private String schoolName;
    private String signature;
    private String customVerify;
    private String enterpriseVerify;
    private String requestId;
    private int followingCount;
    private int favoritingCount;
    private int awemeCount;
    private int fansCount;
    private int movingCount;
    private String avatarList;
    private String avatarMediumList;
    private String avatarThumbList;
    private String uri;
    private String urlKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSceUserId() {
        return sceUserId;
    }

    public void setSceUserId(String sceUserId) {
        this.sceUserId = sceUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTremoloId() {
        return tremoloId;
    }

    public void setTremoloId(String tremoloId) {
        this.tremoloId = tremoloId;
    }

    public String getTremoloNumberId() {
        return tremoloNumberId;
    }

    public void setTremoloNumberId(String tremoloNumberId) {
        this.tremoloNumberId = tremoloNumberId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCustomVerify() {
        return customVerify;
    }

    public void setCustomVerify(String customVerify) {
        this.customVerify = customVerify;
    }

    public String getEnterpriseVerify() {
        return enterpriseVerify;
    }

    public void setEnterpriseVerify(String enterpriseVerify) {
        this.enterpriseVerify = enterpriseVerify;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFavoritingCount() {
        return favoritingCount;
    }

    public void setFavoritingCount(int favoritingCount) {
        this.favoritingCount = favoritingCount;
    }

    public int getAwemeCount() {
        return awemeCount;
    }

    public void setAwemeCount(int awemeCount) {
        this.awemeCount = awemeCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getMovingCount() {
        return movingCount;
    }

    public void setMovingCount(int movingCount) {
        this.movingCount = movingCount;
    }

    public String getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(List<String> avatarList) {
        this.avatarList = JSON.toJSONString(avatarList);
    }

    public String getAvatarMediumList() {
        return avatarMediumList;
    }

    public void setAvatarMediumList(List<String> avatarMediumList) {
        this.avatarMediumList = JSON.toJSONString(avatarMediumList);
    }

    public String getAvatarThumbList() {
        return avatarThumbList;
    }

    public void setAvatarThumbList(List<String> avatarThumbList) {
        this.avatarThumbList = JSON.toJSONString(avatarThumbList);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    /**
     * 已准备 = 0; 未上传 = 1; 上传 = 2;
     */
    private int update;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}