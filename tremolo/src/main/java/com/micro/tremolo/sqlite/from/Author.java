package com.micro.tremolo.sqlite.from;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.root.utils.Lang;

import java.io.Serializable;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:00
 */
public class Author implements Serializable, Cloneable {
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

    public Author(String authorInfo) {
        JSONObject authorObject = JSON.parseObject(authorInfo);
        userId = authorObject.getString("uid");
        sceUserId = authorObject.getString("secUid");
        nickname = authorObject.getString("nickname");
        tremoloId = authorObject.getString("uniqueId");
        tremoloNumberId = authorObject.getString("shortId");
        birthday = authorObject.getString("birthday");
        city = authorObject.getString("city");
        country = authorObject.getString("country");
        district = authorObject.getString("district");
        schoolName = authorObject.getString("schoolName");
        signature = authorObject.getString("signature");
        customVerify = authorObject.getString("customVerify");
        enterpriseVerify = authorObject.getString("enterpriseVerifyReason");
        requestId = authorObject.getString("requestId");
        followingCount = authorObject.getString("followingCount");
        favoritingCount = authorObject.getString("favoritingCount");
        awemeCount = authorObject.getString("awemeCount");
        fansCount = authorObject.getString("fansCount");
        movingCount = authorObject.getString("movingCount");
        String avatarLarger = authorObject.getString("avatarLarger");
        if (Lang.isNotEmpty(avatarLarger)) {
            JSONObject avatarLargerObject = JSON.parseObject(avatarLarger);
            avatarList = (List<String>) avatarLargerObject.get("urlList");
            uri = avatarLargerObject.getString("uri");
            urlKey = avatarLargerObject.getString("urlKey");
        }
        String avatarMedium = authorObject.getString("avatarMedium");
        if (Lang.isNotEmpty(avatarMedium)) {
            JSONObject avatarMediumObject = JSON.parseObject(avatarMedium);
            avatarMediumList = (List<String>) avatarMediumObject.get("urlList");
        }
        String avatarThumb = authorObject.getString("avatarThumb");
        if (Lang.isNotEmpty(avatarThumb)) {
            JSONObject avatarThumbObject = JSON.parseObject(avatarThumb);
            avatarThumbList = (List<String>) avatarThumbObject.get("urlList");
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getSceUserId() {
        return sceUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTremoloId() {
        return tremoloId;
    }

    public String getTremoloNumberId() {
        return tremoloNumberId;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSignature() {
        return signature;
    }

    public String getCustomVerify() {
        return customVerify;
    }

    public String getEnterpriseVerify() {
        return enterpriseVerify;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public String getFavoritingCount() {
        return favoritingCount;
    }

    public String getAwemeCount() {
        return awemeCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public String getMovingCount() {
        return movingCount;
    }

    public List<String> getAvatarList() {
        return avatarList;
    }

    public List<String> getAvatarMediumList() {
        return avatarMediumList;
    }

    public List<String> getAvatarThumbList() {
        return avatarThumbList;
    }

    public String getUri() {
        return uri;
    }

    public String getUrlKey() {
        return urlKey;
    }

    @Override
    public String toString() {
        return "Author{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", tremoloId='" + tremoloId + '\'' +
                ", tremoloNumberId='" + tremoloNumberId + '\'' +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", signature='" + signature + '\'' +
                ", customVerify='" + customVerify + '\'' +
                ", enterpriseVerify='" + enterpriseVerify + '\'' +
                ", requestId='" + requestId + '\'' +
                ", followingCount='" + followingCount + '\'' +
                ", favoritingCount='" + favoritingCount + '\'' +
                ", awemeCount='" + awemeCount + '\'' +
                ", fansCount='" + fansCount + '\'' +
                ", movingCount='" + movingCount + '\'' +
                ", avatarList=" + avatarList +
                ", avatarMediumList=" + avatarMediumList +
                ", avatarThumbList=" + avatarThumbList +
                ", uri='" + uri + '\'' +
                ", urlKey='" + urlKey + '\'' +
                '}';
    }
}