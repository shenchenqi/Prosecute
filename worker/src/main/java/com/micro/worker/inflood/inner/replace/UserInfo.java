package com.micro.worker.inflood.inner.replace;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

import java.util.Arrays;

/**
 * @Author KiLin
 * @Time 2020/4/15 15:06
 */
public class UserInfo {
    private boolean isVerified;
    private Object[] mAvatarPendants;//CDNUrl
    private boolean mBanDisallowAppeal;
    private String mBanReason;
    private String mBanText;
    private int mBanType;
    private Object[] mBigHeadUrls;//CDNUrl
    private boolean mDefaultHead;
    private Object mExtraInfo;//UserExtraInfo
    private boolean mHasAnonymous;
    private String mHeadUrl;
    private Object[] mHeadUrls;//CDNUrl
    private String mId;
    private String mKwaiId;
    private String mName;
    private Object mOpenFriendName;//OpenFriendName
    private int mPendantType;
    private String mProfileBgUrl;
    private Object[] mProfileBgUrls;//CDNUrl
    private Object mProfilePageInfo;//ProfilePageInfo
    private String mSex;
    private String mText;
    private boolean mUserBanned;
    private UserVerifiedDetail mVerifiedDetail;
    private String mVerifiedUrl;

    public UserInfo(Hook hook, Object userInfo) {
        loadUserInfo(hook, userInfo);
    }

    public void loadUserInfo(Hook hook, Object userInfo) {
        if (Lang.isNull(userInfo)) {
            return;
        }
        this.isVerified = isVerified(hook, userInfo);
        this.mAvatarPendants = getAvatarPendants(hook, userInfo);
        this.mBanDisallowAppeal = isBanDisallowAppeal(hook, userInfo);
        this.mBanReason = getBanReason(hook, userInfo);
        this.mBanText = getBanText(hook, userInfo);
        this.mBanType = getBanType(hook, userInfo);
        this.mBigHeadUrls = getBigHeadUrls(hook, userInfo);
        this.mDefaultHead = isDefaultHead(hook, userInfo);
        this.mExtraInfo = getExtraInfo(hook, userInfo);
        this.mHasAnonymous = isHasAnonymous(hook, userInfo);
        this.mHeadUrl = getHeadUrl(hook, userInfo);
        this.mHeadUrls = getHeadUrls(hook, userInfo);
        this.mId = getId(hook, userInfo);
        this.mKwaiId = getKwaiId(hook, userInfo);
        this.mName = getName(hook, userInfo);
        this.mOpenFriendName = getOpenFriendName(hook, userInfo);
        this.mPendantType = getPendantType(hook, userInfo);
        this.mProfileBgUrl = getProfileBgUrl(hook, userInfo);
        this.mProfileBgUrls = getProfileBgUrls(hook, userInfo);
        this.mProfilePageInfo = getProfilePageInfo(hook, userInfo);
        this.mSex = getSex(hook, userInfo);
        this.mText = getText(hook, userInfo);
        this.mUserBanned = isUserBanned(hook, userInfo);
        this.mVerifiedDetail = getVerifiedDetail(hook, userInfo);
        this.mVerifiedUrl = getVerifiedUrl(hook, userInfo);
    }

    public boolean isVerified() {
        return isVerified;
    }

    public Object[] getmAvatarPendants() {
        return mAvatarPendants;
    }

    public boolean ismBanDisallowAppeal() {
        return mBanDisallowAppeal;
    }

    public String getmBanReason() {
        return mBanReason;
    }

    public String getmBanText() {
        return mBanText;
    }

    public int getmBanType() {
        return mBanType;
    }

    public Object[] getmBigHeadUrls() {
        return mBigHeadUrls;
    }

    public boolean ismDefaultHead() {
        return mDefaultHead;
    }

    public Object getmExtraInfo() {
        return mExtraInfo;
    }

    public boolean ismHasAnonymous() {
        return mHasAnonymous;
    }

    public String getmHeadUrl() {
        return mHeadUrl;
    }

    public Object[] getmHeadUrls() {
        return mHeadUrls;
    }

    public String getmId() {
        return mId;
    }

    public String getmKwaiId() {
        return mKwaiId;
    }

    public String getmName() {
        return mName;
    }

    public Object getmOpenFriendName() {
        return mOpenFriendName;
    }

    public int getmPendantType() {
        return mPendantType;
    }

    public String getmProfileBgUrl() {
        return mProfileBgUrl;
    }

    public Object[] getmProfileBgUrls() {
        return mProfileBgUrls;
    }

    public Object getmProfilePageInfo() {
        return mProfilePageInfo;
    }

    public String getmSex() {
        return mSex;
    }

    public String getmText() {
        return mText;
    }

    public boolean ismUserBanned() {
        return mUserBanned;
    }

    public UserVerifiedDetail getmVerifiedDetail() {
        return mVerifiedDetail;
    }

    public String getmVerifiedUrl() {
        return mVerifiedUrl;
    }

    private boolean isVerified(Hook hook, Object userInfo) {
        return hook.getBooleanField(userInfo, "isVerified");
    }

    private Object[] getAvatarPendants(Hook hook, Object userInfo) {
        return (Object[]) hook.getField(userInfo, "mAvatarPendants");
    }

    private boolean isBanDisallowAppeal(Hook hook, Object userInfo) {
        return hook.getBooleanField(userInfo, "mBanDisallowAppeal");
    }

    private String getBanReason(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mBanReason");
    }

    private String getBanText(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mBanText");
    }

    private int getBanType(Hook hook, Object userInfo) {
        return hook.getIntegerField(userInfo, "mBanType");
    }

    private Object[] getBigHeadUrls(Hook hook, Object userInfo) {
        return (Object[]) hook.getField(userInfo, "mBigHeadUrls");
    }

    private boolean isDefaultHead(Hook hook, Object userInfo) {
        return hook.getBooleanField(userInfo, "mDefaultHead");
    }

    private Object getExtraInfo(Hook hook, Object userInfo) {
        return hook.getField(userInfo, "mExtraInfo");
    }

    private boolean isHasAnonymous(Hook hook, Object userInfo) {
        return hook.getBooleanField(userInfo, "mHasAnonymous");
    }

    private String getHeadUrl(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mHeadUrl");
    }

    private Object[] getHeadUrls(Hook hook, Object userInfo) {
        return (Object[]) hook.getField(userInfo, "mHeadUrls");
    }

    private String getId(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mId");
    }

    private String getKwaiId(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mKwaiId");
    }

    private String getName(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mName");
    }

    private Object getOpenFriendName(Hook hook, Object userInfo) {
        return hook.getField(userInfo, "mOpenFriendName");
    }

    private int getPendantType(Hook hook, Object userInfo) {
        return hook.getIntegerField(userInfo, "mPendantType");
    }

    private String getProfileBgUrl(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mProfileBgUrl");
    }

    private Object[] getProfileBgUrls(Hook hook, Object userInfo) {
        return (Object[]) hook.getField(userInfo, "mProfileBgUrls");
    }

    private Object getProfilePageInfo(Hook hook, Object userInfo) {
        return hook.getField(userInfo, "mProfilePageInfo");
    }

    private String getSex(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mSex");
    }

    private String getText(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mText");
    }

    private boolean isUserBanned(Hook hook, Object userInfo) {
        return hook.getBooleanField(userInfo, "mUserBanned");
    }

    private UserVerifiedDetail getVerifiedDetail(Hook hook, Object userInfo) {
        return new UserVerifiedDetail(hook, hook.getField(userInfo, "mVerifiedDetail"));
    }

    private String getVerifiedUrl(Hook hook, Object userInfo) {
        return (String) hook.getField(userInfo, "mVerifiedUrl");
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "\nisVerified=" + isVerified +
                ", \nmAvatarPendants=" + Arrays.toString(mAvatarPendants) +
                ", \nmBanDisallowAppeal=" + mBanDisallowAppeal +
                ", \nmBanReason='" + mBanReason + '\'' +
                ", \nmBanText='" + mBanText + '\'' +
                ", \nmBanType=" + mBanType +
                ", \nmBigHeadUrls=" + Arrays.toString(mBigHeadUrls) +
                ", \nmDefaultHead=" + mDefaultHead +
                ", \nmExtraInfo=" + JSON.toJSONString(mExtraInfo) +
                ", \nmHasAnonymous=" + mHasAnonymous +
                ", \nmHeadUrl='" + mHeadUrl + '\'' +
                ", \nmHeadUrls=" + Arrays.toString(mHeadUrls) +
                ", \nmId='" + mId + '\'' +
                ", \nmKwaiId='" + mKwaiId + '\'' +
                ", \nmName='" + mName + '\'' +
                ", \nmOpenFriendName=" + JSON.toJSONString(mOpenFriendName) +
                ", \nmPendantType=" + mPendantType +
                ", \nmProfileBgUrl='" + mProfileBgUrl + '\'' +
                ", \nmProfileBgUrls=" + Arrays.toString(mProfileBgUrls) +
                ", \nmProfilePageInfo=" + JSON.toJSONString(mProfilePageInfo) +
                ", \nmSex='" + mSex + '\'' +
                ", \nmText='" + mText + '\'' +
                ", \nmUserBanned=" + mUserBanned +
                ", \nmVerifiedDetail=" + mVerifiedDetail.toString() +
                ", \nmVerifiedUrl='" + mVerifiedUrl + '\'' +
                '}';
    }
}
