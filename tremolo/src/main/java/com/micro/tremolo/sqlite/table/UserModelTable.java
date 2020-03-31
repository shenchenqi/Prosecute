package com.micro.tremolo.sqlite.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModelTable {
    private String userId;
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
    private List<String> avatarList;
    private List<String> avatarMediumList;
    private List<String> avatarThumbList;
    private String uri;
    private String urlKey;

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

    public List<String> getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(List<String> avatarList) {
        this.avatarList = avatarList;
    }

    public List<String> getAvatarMediumList() {
        return avatarMediumList;
    }

    public void setAvatarMediumList(List<String> avatarMediumList) {
        this.avatarMediumList = avatarMediumList;
    }

    public List<String> getAvatarThumbList() {
        return avatarThumbList;
    }

    public void setAvatarThumbList(List<String> avatarThumbList) {
        this.avatarThumbList = avatarThumbList;
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

    @Override
    public String toString() {
        return "UserModelTable{" +
                "\nuserId='" + userId + '\'' +
                ", \nnickname='" + nickname + '\'' +
                ", \ntremoloId='" + tremoloId + '\'' +
                ", \ntremoloNumberId='" + tremoloNumberId + '\'' +
                ", \nbirthday='" + birthday + '\'' +
                ", \ncity='" + city + '\'' +
                ", \ncountry='" + country + '\'' +
                ", \ndistrict='" + district + '\'' +
                ", \nschoolName='" + schoolName + '\'' +
                ", \nsignature='" + signature + '\'' +
                ", \ncustomVerify='" + customVerify + '\'' +
                ", \nenterpriseVerify='" + enterpriseVerify + '\'' +
                ", \nrequestId='" + requestId + '\'' +
                ", \nfollowingCount=" + followingCount +
                ", \nfavoritingCount=" + favoritingCount +
                ", \nawemeCount=" + awemeCount +
                ", \nfansCount=" + fansCount +
                ", \nmovingCount=" + movingCount +
                ", \navatarList=" + avatarList +
                ", \navatarMediumList=" + avatarMediumList +
                ", \navatarThumbList=" + avatarThumbList +
                ", \nuri='" + uri + '\'' +
                ", \nurlKey='" + urlKey + '\'' +
                "\n}";
    }

    public Map<String, Object> getUserMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", getUserId() + "");
        map.put("nickname", getNickname() + "");
        map.put("tremoloId", getTremoloId() + "");
        map.put("tremoloNumberId", getTremoloNumberId() + "");
        map.put("birthday", getBirthday() + "");
        map.put("city", getCity() + "");
        map.put("country", getCountry() + "");
        map.put("district", getDistrict() + "");
        map.put("schoolName", getSchoolName() + "");
        map.put("signature", getSignature() + "");
        map.put("customVerify", getCustomVerify() + "");
        map.put("enterpriseVerify", getEnterpriseVerify() + "");
        map.put("requestId", getRequestId() + "");
        map.put("followingCount", getFollowingCount() + "");
        map.put("favoritingCount", getFavoritingCount() + "");
        map.put("awemeCount", getAwemeCount() + "");
        map.put("fansCount", getFansCount() + "");
        map.put("movingCount", getMovingCount() + "");
        map.put("avatarList", getAvatarList() + "");
        map.put("avatarMediumList", getAvatarMediumList() + "");
        map.put("avatarThumbList", getAvatarThumbList() + "");
        map.put("uri", getUri() + "");
        map.put("urlKey", getUrlKey() + "");
        return map;
    }
}