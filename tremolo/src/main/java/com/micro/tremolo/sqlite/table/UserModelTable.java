package com.micro.tremolo.sqlite.table;

import com.micro.root.utils.Lang;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModelTable implements Serializable /*extends DataTable*/ {
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
    private String followingCount;
    private String favoritingCount;
    private String awemeCount;
    private String fansCount;
    private String movingCount;
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

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getFavoritingCount() {
        return favoritingCount;
    }

    public void setFavoritingCount(String favoritingCount) {
        this.favoritingCount = favoritingCount;
    }

    public String getAwemeCount() {
        return awemeCount;
    }

    public void setAwemeCount(String awemeCount) {
        this.awemeCount = awemeCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getMovingCount() {
        return movingCount;
    }

    public void setMovingCount(String movingCount) {
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

    /**
     * 已准备 = 0; 未上传 = 1; 上传 = 2;
     */
    /*private int update;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }*/

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

    /*public Map<String, Object> getUserMap() {
        Map<String, Object> map = new HashMap<>();
        if (Lang.isNotEmpty(getUserId())) {
            map.put("userId", getUserId());
        }
        if (Lang.isNotEmpty(getNickname())) {
            map.put("nickname", getNickname());
        }
        if (Lang.isNotEmpty(getTremoloId())) {
            map.put("tremoloId", getTremoloId());
        }
        if (Lang.isNotEmpty(getTremoloNumberId())) {
            map.put("tremoloNumberId", getTremoloNumberId());
        }
        if (Lang.isNotEmpty(getBirthday())) {
            map.put("birthday", getBirthday());
        }
        if (Lang.isNotEmpty(getCity())) {
            map.put("city", getCity());
        }
        if (Lang.isNotEmpty(getCountry())) {
            map.put("country", getCountry());
        }
        if (Lang.isNotEmpty(getDistrict())) {
            map.put("district", getDistrict());
        }
        if (Lang.isNotEmpty(getSchoolName())) {
            map.put("schoolName", getSchoolName());
        }
        if (Lang.isNotEmpty(getSignature())) {
            map.put("signature", getSignature());
        }
        if (Lang.isNotEmpty(getCustomVerify())) {
            map.put("customVerify", getCustomVerify());
        }
        if (Lang.isNotEmpty(getEnterpriseVerify())) {
            map.put("enterpriseVerify", getEnterpriseVerify());
        }
        if (Lang.isNotEmpty(getRequestId())) {
            map.put("requestId", getRequestId());
        }
        map.put("followingCount", getFollowingCount());
        map.put("favoritingCount", getFavoritingCount());
        map.put("awemeCount", getAwemeCount());
        map.put("fansCount", getFansCount());
        map.put("movingCount", getMovingCount());
        if (Lang.isNotEmpty(getAvatarList())) {
            map.put("avatarList", getAvatarList());
        }
        if (Lang.isNotEmpty(getAvatarMediumList())) {
            map.put("avatarMediumList", getAvatarMediumList());
        }
        if (Lang.isNotEmpty(getAvatarThumbList())) {
            map.put("avatarThumbList", getAvatarThumbList());
        }
        if (Lang.isNotEmpty(getUri())) {
            map.put("uri", getUri());
        }
        if (Lang.isNotEmpty(getUrlKey())) {
            map.put("urlKey", getUrlKey());
        }
        return map;
    }*/
}