package com.micro.worker.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 9:30
 */
public class UserProfile {
    public boolean canSendMessage;
    public boolean isBlocked;
    public boolean isFans;
    public boolean isFollowRequesting;
    public boolean isFollowing;
    public boolean isFriend;
    public Object mAdBusinessInfo;//AdBusinessInfo
    public String mAge;
    public String mAgePrivacy;
    public String mBirthday;
    public Object mBirthdayConfig;//BirthdayConfig
    public String mCityCode;
    public String mCityName;
    public List<String> mCollectTabs = new ArrayList();
    public String mConstellation;
    public Object mCourse;//CourseInfo
    public boolean mDisplayProfileIntegrityDynamicEffect;
    public List<Integer> mEnableBatchShareTab;
    public boolean mEnableMomentTab;
    public String mFollowReason;
    public Object mFriendFollow;//FriendFollow
    public boolean mFrozen;
    public String mFrozenMessage;
    public boolean mIsBlockedByOwner;
    public boolean mIsDefaultBackground;
    public boolean mIsDefaultHead;
    public boolean mIsDefaultName;
    public boolean mIsFavorite;
    public long mLatestVisitCount;
    public Object mMessageGroupMemberInfo;//MessageGroupMemberInfo
    public Object mMissUInfo;//UserProfileMissUInfo
    public UserOwnerCount mOwnerCount;
    public Object mProfile;//UserInfo
    public List<Object> mProfileExtraLinkList;//ProfileExtraLink
    public Object mProfileImGroupInfo;//ImGroupInfoWrapper
    public Object mProfileShopInfo;//ProfileShopInfo
    public Object mSameFollow;//UserSameFollow
    public int mSelectedTabId;
    public boolean mShowDataAssistantEntrance;
    public Object mStoryInfo;//StoryInfo
    public Object mUserFollowerRelation;//UserFollowerRelation
    public Object mUserRemark;//UserRemark
    public Object mUserSettingOption;//UserSettingOption

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
        this.mAdBusinessInfo = getAdBusinessInfo(hook, profile);
        this.mAge = getAge(hook, profile);
        this.mAgePrivacy = getAgePrivacy(hook, profile);
        this.mBirthday = getBirthday(hook, profile);
        this.mBirthdayConfig = getBirthdayConfig(hook, profile);
        this.mCityCode = getCityCode(hook, profile);
        this.mCityName = getCityName(hook, profile);
        this.mCollectTabs = getCollectTabs(hook, profile);
        this.mConstellation = mConstellation;
        this.mCourse = mCourse;
        this.mDisplayProfileIntegrityDynamicEffect = mDisplayProfileIntegrityDynamicEffect;
        this.mEnableBatchShareTab = mEnableBatchShareTab;
        this.mEnableMomentTab = mEnableMomentTab;
        this.mFollowReason = mFollowReason;
        this.mFriendFollow = mFriendFollow;
        this.mFrozen = mFrozen;
        this.mFrozenMessage = mFrozenMessage;
        this.mIsBlockedByOwner = mIsBlockedByOwner;
        this.mIsDefaultBackground = mIsDefaultBackground;
        this.mIsDefaultHead = mIsDefaultHead;
        this.mIsDefaultName = mIsDefaultName;
        this.mIsFavorite = mIsFavorite;
        this.mLatestVisitCount = mLatestVisitCount;
        this.mMessageGroupMemberInfo = mMessageGroupMemberInfo;
        this.mMissUInfo = mMissUInfo;
        this.mOwnerCount = mOwnerCount;
        this.mProfile = mProfile;
        this.mProfileExtraLinkList = mProfileExtraLinkList;
        this.mProfileImGroupInfo = mProfileImGroupInfo;
        this.mProfileShopInfo = mProfileShopInfo;
        this.mSameFollow = mSameFollow;
        this.mSelectedTabId = mSelectedTabId;
        this.mShowDataAssistantEntrance = mShowDataAssistantEntrance;
        this.mStoryInfo = mStoryInfo;
        this.mUserFollowerRelation = mUserFollowerRelation;
        this.mUserRemark = mUserRemark;
        this.mUserSettingOption = mUserSettingOption;
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

    public Object getmAdBusinessInfo() {
        return mAdBusinessInfo;
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
        return hook.getBooleanField(hook, "mAdBusinessInfo");
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
        return hook.getBooleanField(hook, "mDisplayProfileIntegrityDynamicEffect");
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

    private Object getProfile(Hook hook, Object profile) {
        return hook.getField(profile, "mProfile");
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
}
