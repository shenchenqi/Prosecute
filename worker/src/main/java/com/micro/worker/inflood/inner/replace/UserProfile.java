package com.micro.worker.inflood.inner.replace;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 9:30
 */
public class UserProfile {
    private boolean canSendMessage;
    private boolean isBlocked;
    private boolean isFans;
    private boolean isFollowRequesting;
    private boolean isFollowing;
    private boolean isFriend;
    private String mAge;
    private String mAgePrivacy;
    private String mBirthday;
    private Object mBirthdayConfig;//BirthdayConfig
    private String mCityCode;
    private String mCityName;
    private List<String> mCollectTabs;
    private String mConstellation;
    private Object mCourse;//CourseInfo
    private boolean mDisplayProfileIntegrityDynamicEffect;
    private List<Integer> mEnableBatchShareTab;
    private boolean mEnableMomentTab;
    private String mFollowReason;
    private Object mFriendFollow;//FriendFollow
    private boolean mFrozen;
    private String mFrozenMessage;
    private boolean mIsBlockedByOwner;
    private boolean mIsDefaultBackground;
    private boolean mIsDefaultHead;
    private boolean mIsDefaultName;
    private boolean mIsFavorite;
    private long mLatestVisitCount;
    private Object mMessageGroupMemberInfo;//MessageGroupMemberInfo
    private Object mMissUInfo;//UserProfileMissUInfo
    private UserOwnerCount mOwnerCount;
    private UserInfo mProfile;//UserInfo
    private List<Object> mProfileExtraLinkList;//ProfileExtraLink
    private Object mProfileImGroupInfo;//ImGroupInfoWrapper
    private Object mProfileShopInfo;//ProfileShopInfo
    private Object mSameFollow;//UserSameFollow
    private int mSelectedTabId;
    private boolean mShowDataAssistantEntrance;
    private Object mStoryInfo;//StoryInfo
    private Object mUserFollowerRelation;//UserFollowerRelation
    private Object mUserRemark;//UserRemark
    private Object mUserSettingOption;//UserSettingOption

    public UserProfile(Hook hook, Object profile) {
        loadUserProfile(hook, profile);
    }

    public void loadUserProfile(Hook hook, Object profile) {
        if (Lang.isNull(profile)) {
            return;
        }
        this.canSendMessage = isCanSendMessage(hook, profile);
        this.isBlocked = isBlocked(hook, profile);
        this.isFans = isFans(hook, profile);
        this.isFollowRequesting = isFollowRequesting(hook, profile);
        this.isFollowing = isFollowing(hook, profile);
        this.isFriend = isFriend(hook, profile);
        this.mAge = getAge(hook, profile);
        this.mAgePrivacy = getAgePrivacy(hook, profile);
        this.mBirthday = getBirthday(hook, profile);
        this.mBirthdayConfig = getBirthdayConfig(hook, profile);
        this.mCityCode = getCityCode(hook, profile);
        this.mCityName = getCityName(hook, profile);
        this.mCollectTabs = getCollectTabs(hook, profile);
        this.mConstellation = getConstellation(hook, profile);
        this.mCourse = getCourse(hook, profile);
        this.mDisplayProfileIntegrityDynamicEffect = isDisplayProfileIntegrityDynamicEffect(hook, profile);
        this.mEnableBatchShareTab = getEnableBatchShareTab(hook, profile);
        this.mEnableMomentTab = isEnableMomentTab(hook, profile);
        this.mFollowReason = getFollowReason(hook, profile);
        this.mFriendFollow = getFriendFollow(hook, profile);
        this.mFrozen = isFrozen(hook, profile);
        this.mFrozenMessage = getFrozenMessage(hook, profile);
        this.mIsBlockedByOwner = isBlockedByOwner(hook, profile);
        this.mIsDefaultBackground = isDefaultBackground(hook, profile);
        this.mIsDefaultHead = isDefaultHead(hook, profile);
        this.mIsDefaultName = isDefaultName(hook, profile);
        this.mIsFavorite = isFavorite(hook, profile);
        this.mLatestVisitCount = getLatestVisitCount(hook, profile);
        this.mMessageGroupMemberInfo = getMessageGroupMemberInfo(hook, profile);
        this.mMissUInfo = getMissUInfo(hook, profile);
        this.mOwnerCount = getOwnerCount(hook, profile);
        this.mProfile = getProfile(hook, profile);
        this.mProfileExtraLinkList = getProfileExtraLinkList(hook, profile);
        this.mProfileImGroupInfo = getProfileImGroupInfo(hook, profile);
        this.mProfileShopInfo = getProfileShopInfo(hook, profile);
        this.mSameFollow = getSameFollow(hook, profile);
        this.mSelectedTabId = getSelectedTabId(hook, profile);
        this.mShowDataAssistantEntrance = isShowDataAssistantEntrance(hook, profile);
        this.mStoryInfo = getStoryInfo(hook, profile);
        this.mUserFollowerRelation = getUserFollowerRelation(hook, profile);
        this.mUserRemark = getUserRemark(hook, profile);
        this.mUserSettingOption = getUserSettingOption(hook, profile);
    }

    public boolean isCanSendMessage() {
        return canSendMessage;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isFans() {
        return isFans;
    }

    public boolean isFollowRequesting() {
        return isFollowRequesting;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public String getmAge() {
        return mAge;
    }

    public String getmAgePrivacy() {
        return mAgePrivacy;
    }

    public String getmBirthday() {
        return mBirthday;
    }

    public Object getmBirthdayConfig() {
        return mBirthdayConfig;
    }

    public String getmCityCode() {
        return mCityCode;
    }

    public String getmCityName() {
        return mCityName;
    }

    public List<String> getmCollectTabs() {
        return mCollectTabs;
    }

    public String getmConstellation() {
        return mConstellation;
    }

    public Object getmCourse() {
        return mCourse;
    }

    public boolean ismDisplayProfileIntegrityDynamicEffect() {
        return mDisplayProfileIntegrityDynamicEffect;
    }

    public List<Integer> getmEnableBatchShareTab() {
        return mEnableBatchShareTab;
    }

    public boolean ismEnableMomentTab() {
        return mEnableMomentTab;
    }

    public String getmFollowReason() {
        return mFollowReason;
    }

    public Object getmFriendFollow() {
        return mFriendFollow;
    }

    public boolean ismFrozen() {
        return mFrozen;
    }

    public String getmFrozenMessage() {
        return mFrozenMessage;
    }

    public boolean ismIsBlockedByOwner() {
        return mIsBlockedByOwner;
    }

    public boolean ismIsDefaultBackground() {
        return mIsDefaultBackground;
    }

    public boolean ismIsDefaultHead() {
        return mIsDefaultHead;
    }

    public boolean ismIsDefaultName() {
        return mIsDefaultName;
    }

    public boolean ismIsFavorite() {
        return mIsFavorite;
    }

    public long getmLatestVisitCount() {
        return mLatestVisitCount;
    }

    public Object getmMessageGroupMemberInfo() {
        return mMessageGroupMemberInfo;
    }

    public Object getmMissUInfo() {
        return mMissUInfo;
    }

    public UserOwnerCount getmOwnerCount() {
        return mOwnerCount;
    }

    public Object getmProfile() {
        return mProfile;
    }

    public List<Object> getmProfileExtraLinkList() {
        return mProfileExtraLinkList;
    }

    public Object getmProfileImGroupInfo() {
        return mProfileImGroupInfo;
    }

    public Object getmProfileShopInfo() {
        return mProfileShopInfo;
    }

    public Object getmSameFollow() {
        return mSameFollow;
    }

    public int getmSelectedTabId() {
        return mSelectedTabId;
    }

    public boolean ismShowDataAssistantEntrance() {
        return mShowDataAssistantEntrance;
    }

    public Object getmStoryInfo() {
        return mStoryInfo;
    }

    public Object getmUserFollowerRelation() {
        return mUserFollowerRelation;
    }

    public Object getmUserRemark() {
        return mUserRemark;
    }

    public Object getmUserSettingOption() {
        return mUserSettingOption;
    }

    private boolean isCanSendMessage(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "canSendMessage");
    }

    private boolean isBlocked(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "isBlocked");
    }

    private boolean isFans(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "isFans");
    }

    private boolean isFollowRequesting(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "isFollowRequesting");
    }

    private boolean isFollowing(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "isFollowing");
    }

    private boolean isFriend(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "isFriend");
    }

    private Object getAdBusinessInfo(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mAdBusinessInfo");
    }

    private String getAge(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mAge");
    }

    private String getAgePrivacy(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mAgePrivacy");
    }

    private String getBirthday(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mBirthday");
    }

    private Object getBirthdayConfig(Hook hook, Object profile) {
        return hook.getField(profile, "mBirthdayConfig");
    }

    private String getCityCode(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mCityCode");
    }

    private String getCityName(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mCityName");
    }

    private List<String> getCollectTabs(Hook hook, Object profile) {
        return (List<String>) hook.getField(profile, "mCollectTabs");
    }

    private String getConstellation(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mConstellation");
    }

    private Object getCourse(Hook hook, Object profile) {
        return hook.getField(profile, "mCourse");
    }

    private boolean isDisplayProfileIntegrityDynamicEffect(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mDisplayProfileIntegrityDynamicEffect");
    }

    private List<Integer> getEnableBatchShareTab(Hook hook, Object profile) {
        return (List<Integer>) hook.getField(profile, "mEnableBatchShareTab");
    }

    private boolean isEnableMomentTab(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mEnableMomentTab");
    }

    private String getFollowReason(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mFollowReason");
    }

    private Object getFriendFollow(Hook hook, Object profile) {
        return hook.getField(profile, "mFriendFollow");
    }

    private boolean isFrozen(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mFrozen");
    }

    private String getFrozenMessage(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mFrozenMessage");
    }

    private boolean isBlockedByOwner(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mIsBlockedByOwner");
    }

    private boolean isDefaultBackground(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mIsDefaultBackground");
    }

    private boolean isDefaultHead(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mIsDefaultHead");
    }

    private boolean isDefaultName(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mIsDefaultName");
    }

    private boolean isFavorite(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mIsFavorite");
    }

    private long getLatestVisitCount(Hook hook, Object profile) {
        return hook.getLongField(profile, "mLatestVisitCount");
    }

    private Object getMessageGroupMemberInfo(Hook hook, Object profile) {
        return hook.getField(profile, "mMessageGroupMemberInfo");
    }

    private Object getMissUInfo(Hook hook, Object profile) {
        return hook.getField(profile, "mMissUInfo");
    }

    private UserOwnerCount getOwnerCount(Hook hook, Object profile) {
        return new UserOwnerCount(hook, hook.getField(profile, "mOwnerCount"));
    }

    private UserInfo getProfile(Hook hook, Object profile) {
        return new UserInfo(hook, hook.getField(profile, "mProfile"));
    }

    private List<Object> getProfileExtraLinkList(Hook hook, Object profile) {
        return (List<Object>) hook.getField(profile, "mProfileExtraLinkList");
    }

    private Object getProfileImGroupInfo(Hook hook, Object profile) {
        return hook.getField(profile, "mProfileImGroupInfo");
    }

    private Object getProfileShopInfo(Hook hook, Object profile) {
        return hook.getField(profile, "mProfileShopInfo");
    }

    private Object getSameFollow(Hook hook, Object profile) {
        return hook.getField(profile, "mSameFollow");
    }

    private int getSelectedTabId(Hook hook, Object profile) {
        return hook.getIntegerField(profile, "mSelectedTabId");
    }

    private boolean isShowDataAssistantEntrance(Hook hook, Object profile) {
        return hook.getBooleanField(profile, "mShowDataAssistantEntrance");
    }

    private Object getStoryInfo(Hook hook, Object profile) {
        return hook.getField(profile, "mStoryInfo");
    }

    private Object getUserFollowerRelation(Hook hook, Object profile) {
        return hook.getField(profile, "mUserFollowerRelation");
    }

    private Object getUserRemark(Hook hook, Object profile) {
        return hook.getField(profile, "mUserRemark");
    }

    private Object getUserSettingOption(Hook hook, Object profile) {
        return hook.getField(profile, "mUserSettingOption");
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "\ncanSendMessage=" + canSendMessage +
                ", \nisBlocked=" + isBlocked +
                ", \nisFans=" + isFans +
                ", \nisFollowRequesting=" + isFollowRequesting +
                ", \nisFollowing=" + isFollowing +
                ", \nisFriend=" + isFriend +
                ", \nmAge='" + mAge + '\'' +
                ", \nmAgePrivacy='" + mAgePrivacy + '\'' +
                ", \nmBirthday='" + mBirthday + '\'' +
                ", \nmBirthdayConfig=" + JSON.toJSONString(mBirthdayConfig) +
                ", \nmCityCode='" + mCityCode + '\'' +
                ", \nmCityName='" + mCityName + '\'' +
                ", \nmCollectTabs=" + JSON.toJSONString(mCollectTabs) +
                ", \nmConstellation='" + mConstellation + '\'' +
                ", \nmCourse=" + JSON.toJSONString(mCourse) +
                ", \nmDisplayProfileIntegrityDynamicEffect=" + mDisplayProfileIntegrityDynamicEffect +
                ", \nmEnableBatchShareTab=" + JSON.toJSONString(mEnableBatchShareTab) +
                ", \nmEnableMomentTab=" + mEnableMomentTab +
                ", \nmFollowReason='" + mFollowReason + '\'' +
                ", \nmFriendFollow=" + JSON.toJSONString(mFriendFollow) +
                ", \nmFrozen=" + mFrozen +
                ", \nmFrozenMessage='" + mFrozenMessage + '\'' +
                ", \nmIsBlockedByOwner=" + mIsBlockedByOwner +
                ", \nmIsDefaultBackground=" + mIsDefaultBackground +
                ", \nmIsDefaultHead=" + mIsDefaultHead +
                ", \nmIsDefaultName=" + mIsDefaultName +
                ", \nmIsFavorite=" + mIsFavorite +
                ", \nmLatestVisitCount=" + mLatestVisitCount +
                ", \nmMessageGroupMemberInfo=" + JSON.toJSONString(mMessageGroupMemberInfo) +
                ", \nmMissUInfo=" + JSON.toJSONString(mMissUInfo) +
                ", \nmOwnerCount=" + mOwnerCount.toString() +
                ", \nmProfile=" + mProfile.toString() +
                ", \nmProfileExtraLinkList=" + JSON.toJSONString(mProfileExtraLinkList) +
                ", \nmProfileImGroupInfo=" + JSON.toJSONString(mProfileImGroupInfo) +
                ", \nmProfileShopInfo=" + JSON.toJSONString(mProfileShopInfo) +
                ", \nmSameFollow=" + JSON.toJSONString(mSameFollow) +
                ", \nmSelectedTabId=" + mSelectedTabId +
                ", \nmShowDataAssistantEntrance=" + mShowDataAssistantEntrance +
                ", \nmStoryInfo=" + JSON.toJSONString(mStoryInfo) +
                ", \nmUserFollowerRelation=" + JSON.toJSONString(mUserFollowerRelation) +
                ", \nmUserRemark=" + JSON.toJSONString(mUserRemark) +
                ", \nmUserSettingOption=" + JSON.toJSONString(mUserSettingOption) +
                '}';
    }
}
