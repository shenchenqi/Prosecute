package com.micro.tremolo.model.from;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.model.params.UserParam;
import com.micro.tremolo.model.table.UserTable;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:00
 */
public class Author extends BaseFrom {
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
        followingCount = authorObject.getIntValue("followingCount");
        favoritingCount = authorObject.getIntValue("favoritingCount");
        awemeCount = authorObject.getIntValue("awemeCount");
        fansCount = authorObject.getIntValue("fansCount");
        movingCount = authorObject.getIntValue("movingCount");
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

    public Author(User user) {
        userId = user.getUid();
        sceUserId = user.getSecUid();
        nickname = user.getNickname();
        tremoloId = user.getUniqueId();
        tremoloNumberId = user.getShortId();
        birthday = user.getBirthday();
        city = user.getCity();
        country = user.getCountry();
        district = user.getDistrict();
        schoolName = user.getSchoolName();
        signature = user.getSignature();
        customVerify = user.getCustomVerify();
        enterpriseVerify = user.getEnterpriseVerifyReason();
        requestId = user.getRequestId();
        followingCount = user.getFollowingCount();
        favoritingCount = user.getFavoritingCount();
        awemeCount = user.getAwemeCount();
        fansCount = user.getFansCount();
        movingCount = user.getDongtai_count();
        UrlModel avatarLarger = user.getAvatarLarger();
        if (Lang.isNotNull(avatarLarger)) {
            avatarList = avatarLarger.getUrlList();
            uri = avatarLarger.getUri();
            urlKey = avatarLarger.getUrlKey();
        }
        UrlModel avatarMedium = user.getAvatarMedium();
        if (Lang.isNotNull(avatarMedium)) {
            avatarMediumList = avatarMedium.getUrlList();
        }
        UrlModel avatarThumb = user.getAvatarThumb();
        if (Lang.isNotNull(avatarThumb)) {
            avatarThumbList = avatarThumb.getUrlList();
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

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFavoritingCount() {
        return favoritingCount;
    }

    public int getAwemeCount() {
        return awemeCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public int getMovingCount() {
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

    public UserParam getUserParam(){
        UserParam userParam = new UserParam();
        userParam.setUserId(getUserId());
        userParam.setSceUserId(getSceUserId());
        userParam.setNickname(getNickname());
        userParam.setTremoloId(getTremoloId());
        userParam.setTremoloNumberId(getTremoloNumberId());
        userParam.setBirthday(getBirthday());
        userParam.setCity(getCity());
        userParam.setCountry(getCountry());
        userParam.setDistrict(getDistrict());
        userParam.setSchoolName(getSchoolName());
        userParam.setSignature(getSignature());
        userParam.setCustomVerify(getCustomVerify());
        userParam.setEnterpriseVerify(getEnterpriseVerify());
        userParam.setRequestId(getRequestId());
        userParam.setFollowingCount(String.valueOf(getFollowingCount()));
        userParam.setFavoritingCount(String.valueOf(getFavoritingCount()));
        userParam.setAwemeCount(String.valueOf(getAwemeCount()));
        userParam.setFansCount(String.valueOf(getFansCount()));
        userParam.setMovingCount(String.valueOf(getMovingCount()));
        userParam.setAvatarList(getAvatarList());
        userParam.setAvatarMediumList(getAvatarMediumList());
        userParam.setAvatarThumbList(getAvatarThumbList());
        userParam.setUri(getUri());
        userParam.setUrlKey(getUrlKey());
        return userParam;
    }

    public UserTable getUserTable(){
        UserTable userTable = new UserTable();
        userTable.setUserId(getUserId());
        userTable.setSceUserId(getSceUserId());
        userTable.setNickname(getNickname());
        userTable.setTremoloId(getTremoloId());
        userTable.setTremoloNumberId(getTremoloNumberId());
        userTable.setBirthday(getBirthday());
        userTable.setCity(getCity());
        userTable.setCountry(getCountry());
        userTable.setDistrict(getDistrict());
        userTable.setSchoolName(getSchoolName());
        userTable.setSignature(getSignature());
        userTable.setCustomVerify(getCustomVerify());
        userTable.setEnterpriseVerify(getEnterpriseVerify());
        userTable.setRequestId(getRequestId());
        userTable.setFollowingCount(getFollowingCount());
        userTable.setFavoritingCount(getFavoritingCount());
        userTable.setAwemeCount(getAwemeCount());
        userTable.setFansCount(getFansCount());
        userTable.setMovingCount(getMovingCount());
        userTable.setAvatarList(getAvatarList());
        userTable.setAvatarMediumList(getAvatarMediumList());
        userTable.setAvatarThumbList(getAvatarThumbList());
        userTable.setUri(getUri());
        userTable.setUrlKey(getUrlKey());
        userTable.setUpdate(0);
        return userTable;
    }
}