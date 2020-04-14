package com.micro.worker.inflood.inner.replace;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/14 10:02
 */
public class User {
    private String hint;
    private int mAge;
    private String mAlias;//昵称
    private String mAvatar;//头像
    private Object[] mAvatars;//CDNUrl[] 头像数组
    private String mBackgroundUrl;//背景图片
    private Object[] mBackgroundUrls;//CDNUrl[]
    private boolean mBanned;
    private boolean mBlacked;
    private boolean mCommentDeny;
    private Object mCommonPoint;//UserCommonPoint
    private String mConstellation;
    private String mContactName;
    private double mDistance;
    private String mDistanceInfo;
    private boolean mDownloadDeny;
    private String mExactMatchTip;
    private Object mExtraInfo;//UserExtraInfo
    private int mFansCount;
    private boolean mFavorited;
    private String mFollowReason;
    private boolean mFollowRequesting;
    private Object mFollowStatus;//FollowStatus
    private boolean mFollowed;
    private Object mFollowerRelation;//UserFollowerRelation
    private boolean mFriend;
    private boolean mHasGreeted;
    private transient boolean mHasUnReadStory;
    private String mHiddenUserDesc;
    private String mHiddenUserName;
    private String mId;//用户ID
    private boolean mIsDefaultHead;
    private boolean mIsHiddenUser;
    private boolean mIsLiving;
    private String mKwaiId;//快手号
    private Object mLiveTipInfo;//LiveTipInfo
    private String mLlsid;
    private boolean mMessageDeny;
    private boolean mMissUDeny;
    private Object mMissUInfo;//UserProfileMissUInfo
    private List<String> mMissURelation;
    private long mMissUTime;
    private String mMobileHash;
    private String mName;//名称
    private boolean mNewest;
    private String mOnlineTimeInfo;
    private Object mOpenFriendName;//OpenFriendName
    private UserOwnerCount mOwnerCount;//UserOwnerCount 统计数据
    private transient String mPage;
    private int mPendantType;
    private Object[] mPendants;//CDNUrl[]
    private transient List<Object> mPhotoList;//BaseFeed
    private int mPlatform;
    private String mPlatformUserName;
    private boolean mPrivate;
    private Object mProfilePageInfo;//ProfilePageInfo
    private String mPrsid;
    private int mRelation;
    private String mSearchUssid;
    private String mSex;//性别 M-男;F-女;
    private String mSubtitle;
    private String mText;//签名
    private int mUserAge;
    private boolean mUserMessageDeny;
    private boolean mVerified;//是否有标签
    private UserVerifiedDetail mVerifiedDetail;//UserVerifiedDetail 标签信息

    public User(Hook hook, Object user) {
        loadUser(hook, user);
    }

    public void loadUser(Hook hook, Object user) {
        if (Lang.isNull(user)) {
            return;
        }
        this.hint = getHint(hook, user);
        this.mAge = getAge(hook, user);
        this.mAlias = getAlias(hook, user);
        this.mAvatar = getAvatar(hook, user);
        this.mAvatars = getAvatars(hook, user);
        this.mBackgroundUrl = getBackgroundUrl(hook, user);
        this.mBackgroundUrls = getBackgroundUrls(hook, user);
        this.mBanned = isBanned(hook, user);
        this.mBlacked = isBlacked(hook, user);
        this.mCommentDeny = isCommentDeny(hook, user);
        this.mCommonPoint = getCommonPoint(hook, user);
        this.mConstellation = getConstellation(hook, user);
        this.mContactName = getContactName(hook, user);
        this.mDistance = getDistance(hook, user);
        this.mDistanceInfo = getDistanceInfo(hook, user);
        this.mDownloadDeny = isDownloadDeny(hook, user);
        this.mExactMatchTip = getExactMatchTip(hook, user);
        this.mExtraInfo = getExtraInfo(hook, user);
        this.mFansCount = getFansCount(hook, user);
        this.mFavorited = isFavorited(hook, user);
        this.mFollowReason = getFollowReason(hook, user);
        this.mFollowRequesting = isFollowRequesting(hook, user);
        this.mFollowStatus = getFollowStatus(hook, user);
        this.mFollowed = isFollowed(hook, user);
        this.mFollowerRelation = getFollowerRelation(hook, user);
        this.mFriend = isFriend(hook, user);
        this.mHasGreeted = isHasGreeted(hook, user);
        this.mHasUnReadStory = isHasUnReadStory(hook, user);
        this.mHiddenUserDesc = getHiddenUserDesc(hook, user);
        this.mHiddenUserName = getHiddenUserName(hook, user);
        this.mId = getId(hook, user);
        this.mIsDefaultHead = isIsDefaultHead(hook, user);
        this.mIsHiddenUser = isIsHiddenUser(hook, user);
        this.mIsLiving = isIsLiving(hook, user);
        this.mKwaiId = getKwaiId(hook, user);
        this.mLiveTipInfo = getLiveTipInfo(hook, user);
        this.mLlsid = getLlsid(hook, user);
        this.mMessageDeny = isMessageDeny(hook, user);
        this.mMissUDeny = isMissUDeny(hook, user);
        this.mMissUInfo = getMissUInfo(hook, user);
        this.mMissURelation = getMissURelation(hook, user);
        this.mMissUTime = getMissUTime(hook, user);
        this.mMobileHash = getMobileHash(hook, user);
        this.mName = getName(hook, user);
        this.mNewest = isNewest(hook, user);
        this.mOnlineTimeInfo = getOnlineTimeInfo(hook, user);
        this.mOpenFriendName = getOpenFriendName(hook, user);
        this.mOwnerCount = getOwnerCount(hook, user);
        this.mPage = getPage(hook, user);
        this.mPendantType = getPendantType(hook, user);
        this.mPendants = getPendants(hook, user);
        this.mPhotoList = getPhotoList(hook, user);
        this.mPlatform = getPlatform(hook, user);
        this.mPlatformUserName = getPlatformUserName(hook, user);
        this.mPrivate = isPrivate(hook, user);
        this.mProfilePageInfo = getProfilePageInfo(hook, user);
        this.mPrsid = getPrsid(hook, user);
        this.mRelation = getRelation(hook, user);
        this.mSearchUssid = getSearchUssid(hook, user);
        this.mSex = getSex(hook, user);
        this.mSubtitle = getSubtitle(hook, user);
        this.mText = getText(hook, user);
        this.mUserAge = getUserAge(hook, user);
        this.mUserMessageDeny = isUserMessageDeny(hook, user);
        this.mVerified = isVerified(hook, user);
        this.mVerifiedDetail = getVerifiedDetail(hook, user);
    }

    public String getHint() {
        return hint;
    }

    public int getAge() {
        return mAge;
    }

    public String getAlias() {
        return mAlias;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public Object[] getAvatars() {
        return mAvatars;
    }

    public String getBackgroundUrl() {
        return mBackgroundUrl;
    }

    public Object[] getBackgroundUrls() {
        return mBackgroundUrls;
    }

    public boolean isBanned() {
        return mBanned;
    }

    public boolean isBlacked() {
        return mBlacked;
    }

    public boolean isCommentDeny() {
        return mCommentDeny;
    }

    public Object getCommonPoint() {
        return mCommonPoint;
    }

    public String getConstellation() {
        return mConstellation;
    }

    public String getContactName() {
        return mContactName;
    }

    public double getDistance() {
        return mDistance;
    }

    public String getDistanceInfo() {
        return mDistanceInfo;
    }

    public boolean ismDownloadDeny() {
        return mDownloadDeny;
    }

    public String getExactMatchTip() {
        return mExactMatchTip;
    }

    public Object getExtraInfo() {
        return mExtraInfo;
    }

    public int getFansCount() {
        return mFansCount;
    }

    public boolean isFavorited() {
        return mFavorited;
    }

    public String getFollowReason() {
        return mFollowReason;
    }

    public boolean isFollowRequesting() {
        return mFollowRequesting;
    }

    public Object getFollowStatus() {
        return mFollowStatus;
    }

    public boolean isFollowed() {
        return mFollowed;
    }

    public Object getFollowerRelation() {
        return mFollowerRelation;
    }

    public boolean isFriend() {
        return mFriend;
    }

    public boolean isHasGreeted() {
        return mHasGreeted;
    }

    public boolean isHasUnReadStory() {
        return mHasUnReadStory;
    }

    public String getHiddenUserDesc() {
        return mHiddenUserDesc;
    }

    public String getHiddenUserName() {
        return mHiddenUserName;
    }

    public String getId() {
        return mId;
    }

    public boolean isIsDefaultHead() {
        return mIsDefaultHead;
    }

    public boolean isIsHiddenUser() {
        return mIsHiddenUser;
    }

    public boolean isIsLiving() {
        return mIsLiving;
    }

    public String getKwaiId() {
        return mKwaiId;
    }

    public Object getLiveTipInfo() {
        return mLiveTipInfo;
    }

    public String getLlsid() {
        return mLlsid;
    }

    public boolean isMessageDeny() {
        return mMessageDeny;
    }

    public boolean isMissUDeny() {
        return mMissUDeny;
    }

    public Object getMissUInfo() {
        return mMissUInfo;
    }

    public List<String> getMissURelation() {
        return mMissURelation;
    }

    public long getMissUTime() {
        return mMissUTime;
    }

    public String getMobileHash() {
        return mMobileHash;
    }

    public String getName() {
        return mName;
    }

    public boolean isNewest() {
        return mNewest;
    }

    public String getOnlineTimeInfo() {
        return mOnlineTimeInfo;
    }

    public Object getOpenFriendName() {
        return mOpenFriendName;
    }

    public Object getOwnerCount() {
        return mOwnerCount;
    }

    public String getPage() {
        return mPage;
    }

    public int getPendantType() {
        return mPendantType;
    }

    public Object[] getPendants() {
        return mPendants;
    }

    public List<Object> getPhotoList() {
        return mPhotoList;
    }

    public int getPlatform() {
        return mPlatform;
    }

    public String getPlatformUserName() {
        return mPlatformUserName;
    }

    public boolean isPrivate() {
        return mPrivate;
    }

    public Object getProfilePageInfo() {
        return mProfilePageInfo;
    }

    public String getPrsid() {
        return mPrsid;
    }

    public int getRelation() {
        return mRelation;
    }

    public String getSearchUssid() {
        return mSearchUssid;
    }

    public String getSex() {
        return mSex;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public String getText() {
        return mText;
    }

    public int getUserAge() {
        return mUserAge;
    }

    public boolean isUserMessageDeny() {
        return mUserMessageDeny;
    }

    public boolean isVerified() {
        return mVerified;
    }

    public Object getVerifiedDetail() {
        return mVerifiedDetail;
    }

    private String getHint(Hook hook, Object user) {
        return (String) hook.getField(user, "hint");
    }

    private int getAge(Hook hook, Object user) {
        return hook.getIntegerField(user, "mAge");
    }

    private String getAlias(Hook hook, Object user) {
        return (String) hook.getField(user, "mAlias");
    }

    private String getAvatar(Hook hook, Object user) {
        return (String) hook.getField(user, "mAvatar");
    }

    private Object[] getAvatars(Hook hook, Object user) {
        return (Object[]) hook.getField(user, "mAvatars");
    }

    private String getBackgroundUrl(Hook hook, Object user) {
        return (String) hook.getField(user, "mBackgroundUrl");
    }

    private Object[] getBackgroundUrls(Hook hook, Object user) {
        return (Object[]) hook.getField(user, "mBackgroundUrls");
    }

    private boolean isBanned(Hook hook, Object user) {
        return hook.getBooleanField(user, "mBanned");
    }

    private boolean isBlacked(Hook hook, Object user) {
        return hook.getBooleanField(user, "mBlacked");
    }

    private boolean isCommentDeny(Hook hook, Object user) {
        return hook.getBooleanField(user, "mCommentDeny");
    }

    private Object getCommonPoint(Hook hook, Object user) {
        return hook.getField(user, "mCommonPoint");
    }

    private String getConstellation(Hook hook, Object user) {
        return (String) hook.getField(user, "mConstellation");
    }

    private String getContactName(Hook hook, Object user) {
        return (String) hook.getField(user, "mContactName");
    }

    private double getDistance(Hook hook, Object user) {
        return hook.getDoubleField(user, "mDistance");
    }

    private String getDistanceInfo(Hook hook, Object user) {
        return (String) hook.getField(user, "mDistanceInfo");
    }

    private boolean isDownloadDeny(Hook hook, Object user) {
        return hook.getBooleanField(user, "mDownloadDeny");
    }

    private String getExactMatchTip(Hook hook, Object user) {
        return (String) hook.getField(user, "mExactMatchTip");
    }

    private Object getExtraInfo(Hook hook, Object user) {
        return hook.getField(user, "mExtraInfo");
    }

    private int getFansCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "mFansCount");
    }

    private boolean isFavorited(Hook hook, Object user) {
        return hook.getBooleanField(user, "mFavorited");
    }

    private String getFollowReason(Hook hook, Object user) {
        return (String) hook.getField(user, "mFollowReason");
    }

    private boolean isFollowRequesting(Hook hook, Object user) {
        return hook.getBooleanField(user, "mFollowRequesting");
    }

    private Object getFollowStatus(Hook hook, Object user) {
        return hook.getField(user, "mFollowStatus");
    }

    private boolean isFollowed(Hook hook, Object user) {
        return hook.getBooleanField(user, "mFollowed");
    }

    private Object getFollowerRelation(Hook hook, Object user) {
        return hook.getField(user, "mFollowerRelation");
    }

    private boolean isFriend(Hook hook, Object user) {
        return hook.getBooleanField(user, "mFriend");
    }

    private boolean isHasGreeted(Hook hook, Object user) {
        return hook.getBooleanField(user, "mHasGreeted");
    }

    private boolean isHasUnReadStory(Hook hook, Object user) {
        return hook.getBooleanField(user, "mHasUnReadStory");
    }

    private String getHiddenUserDesc(Hook hook, Object user) {
        return (String) hook.getField(user, "mHiddenUserDesc");
    }

    private String getHiddenUserName(Hook hook, Object user) {
        return (String) hook.getField(user, "mHiddenUserName");
    }

    private String getId(Hook hook, Object user) {
        return (String) hook.getField(user, "mId");
    }

    private boolean isIsDefaultHead(Hook hook, Object user) {
        return hook.getBooleanField(user, "mIsDefaultHead");
    }

    private boolean isIsHiddenUser(Hook hook, Object user) {
        return hook.getBooleanField(user, "mIsHiddenUser");
    }

    private boolean isIsLiving(Hook hook, Object user) {
        return hook.getBooleanField(user, "mIsLiving");
    }

    private String getKwaiId(Hook hook, Object user) {
        return (String) hook.getField(user, "mKwaiId");
    }

    private Object getLiveTipInfo(Hook hook, Object user) {
        return hook.getField(user, "mLiveTipInfo");
    }

    private String getLlsid(Hook hook, Object user) {
        return (String) hook.getField(user, "mLlsid");
    }

    private boolean isMessageDeny(Hook hook, Object user) {
        return hook.getBooleanField(user, "mMessageDeny");
    }

    private boolean isMissUDeny(Hook hook, Object user) {
        return hook.getBooleanField(user, "mMissUDeny");
    }

    private Object getMissUInfo(Hook hook, Object user) {
        return hook.getField(user, "mMissUInfo");
    }

    private List<String> getMissURelation(Hook hook, Object user) {
        return (List<String>) hook.getField(user, "mMissURelation");
    }

    private long getMissUTime(Hook hook, Object user) {
        return hook.getLongField(user, "mMissUTime");
    }

    private String getMobileHash(Hook hook, Object user) {
        return (String) hook.getField(user, "mMobileHash");
    }

    private String getName(Hook hook, Object user) {
        return (String) hook.getField(user, "mName");
    }

    private boolean isNewest(Hook hook, Object user) {
        return hook.getBooleanField(user, "mNewest");
    }

    private String getOnlineTimeInfo(Hook hook, Object user) {
        return (String) hook.getField(user, "mOnlineTimeInfo");
    }

    private Object getOpenFriendName(Hook hook, Object user) {
        return hook.getField(user, "mOpenFriendName");
    }

    private UserOwnerCount getOwnerCount(Hook hook, Object user) {
        return new UserOwnerCount(hook, hook.getField(user, "mOwnerCount"));
    }

    private String getPage(Hook hook, Object user) {
        return (String) hook.getField(user, "mPage");
    }

    private int getPendantType(Hook hook, Object user) {
        return hook.getIntegerField(user, "mPendantType");
    }

    private Object[] getPendants(Hook hook, Object user) {
        return (Object[]) hook.getField(user, "mPendants");
    }

    private List<Object> getPhotoList(Hook hook, Object user) {
        return (List<Object>) hook.getField(user, "mPhotoList");
    }

    private int getPlatform(Hook hook, Object user) {
        return hook.getIntegerField(user, "mPlatform");
    }

    private String getPlatformUserName(Hook hook, Object user) {
        return (String) hook.getField(user, "mPlatformUserName");
    }

    private boolean isPrivate(Hook hook, Object user) {
        return hook.getBooleanField(user, "mPrivate");
    }

    private Object getProfilePageInfo(Hook hook, Object user) {
        return hook.getField(user, "mProfilePageInfo");
    }

    private String getPrsid(Hook hook, Object user) {
        return (String) hook.getField(user, "mPrsid");
    }

    private int getRelation(Hook hook, Object user) {
        return hook.getIntegerField(user, "mRelation");
    }

    private String getSearchUssid(Hook hook, Object user) {
        return (String) hook.getField(user, "mSearchUssid");
    }

    private String getSex(Hook hook, Object user) {
        return (String) hook.getField(user, "mSex");
    }

    private String getSubtitle(Hook hook, Object user) {
        return (String) hook.getField(user, "mSubtitle");
    }

    private String getText(Hook hook, Object user) {
        return (String) hook.getField(user, "mText");
    }

    private int getUserAge(Hook hook, Object user) {
        return hook.getIntegerField(user, "mUserAge");
    }

    private boolean isUserMessageDeny(Hook hook, Object user) {
        return hook.getBooleanField(user, "mUserMessageDeny");
    }

    private boolean isVerified(Hook hook, Object user) {
        return hook.getBooleanField(user, "mVerified");
    }

    private UserVerifiedDetail getVerifiedDetail(Hook hook, Object user) {
        return new UserVerifiedDetail(hook, hook.getField(user, "mVerifiedDetail"));
    }

    @Override
    public String toString() {
        return "User{" +
                "\nhint='" + hint + '\'' +
                ", \nmAge=" + mAge +
                ", \nmAlias='" + mAlias + '\'' +
                ", \nmAvatar='" + mAvatar + '\'' +
                ", \nmAvatars=" + JSON.toJSONString(mAvatars) +
                ", \nmBackgroundUrl='" + mBackgroundUrl + '\'' +
                ", \nmBackgroundUrls=" + JSON.toJSONString(mBackgroundUrls) +
                ", \nmBanned=" + mBanned +
                ", \nmBlacked=" + mBlacked +
                ", \nmCommentDeny=" + mCommentDeny +
                ", \nmCommonPoint=" + JSON.toJSONString(mCommonPoint) +
                ", \nmConstellation='" + mConstellation + '\'' +
                ", \nmContactName='" + mContactName + '\'' +
                ", \nmDistance=" + mDistance +
                ", \nmDistanceInfo='" + mDistanceInfo + '\'' +
                ", \nmDownloadDeny=" + mDownloadDeny +
                ", \nmExactMatchTip='" + mExactMatchTip + '\'' +
                ", \nmExtraInfo=" + JSON.toJSONString(mExtraInfo) +
                ", \nmFansCount=" + mFansCount +
                ", \nmFavorited=" + mFavorited +
                ", \nmFollowReason='" + mFollowReason + '\'' +
                ", \nmFollowRequesting=" + mFollowRequesting +
                ", \nmFollowStatus=" + JSON.toJSONString(mFollowStatus) +
                ", \nmFollowed=" + mFollowed +
                ", \nmFollowerRelation=" + JSON.toJSONString(mFollowerRelation) +
                ", \nmFriend=" + mFriend +
                ", \nmHasGreeted=" + mHasGreeted +
                ", \nmHasUnReadStory=" + mHasUnReadStory +
                ", \nmHiddenUserDesc='" + mHiddenUserDesc + '\'' +
                ", \nmHiddenUserName='" + mHiddenUserName + '\'' +
                ", \nmId='" + mId + '\'' +
                ", \nmIsDefaultHead=" + mIsDefaultHead +
                ", \nmIsHiddenUser=" + mIsHiddenUser +
                ", \nmIsLiving=" + mIsLiving +
                ", \nmKwaiId='" + mKwaiId + '\'' +
                ", \nmLiveTipInfo=" + JSON.toJSONString(mLiveTipInfo) +
                ", \nmLlsid='" + mLlsid + '\'' +
                ", \nmMessageDeny=" + mMessageDeny +
                ", \nmMissUDeny=" + mMissUDeny +
                ", \nmMissUInfo=" + JSON.toJSONString(mMissUInfo) +
                ", \nmMissURelation=" + mMissURelation +
                ", \nmMissUTime=" + mMissUTime +
                ", \nmMobileHash='" + mMobileHash + '\'' +
                ", \nmName='" + mName + '\'' +
                ", \nmNewest=" + mNewest +
                ", \nmOnlineTimeInfo='" + mOnlineTimeInfo + '\'' +
                ", \nmOpenFriendName=" + JSON.toJSONString(mOpenFriendName) +
                ", \nmOwnerCount=" + JSON.toJSONString(mOwnerCount) +
                ", \nmPage='" + mPage + '\'' +
                ", \nmPendantType=" + mPendantType +
                ", \nmPendants=" + JSON.toJSONString(mPendants) +
                ", \nmPhotoList=" + mPhotoList +
                ", \nmPlatform=" + mPlatform +
                ", \nmPlatformUserName='" + mPlatformUserName + '\'' +
                ", \nmPrivate=" + mPrivate +
                ", \nmProfilePageInfo=" + JSON.toJSONString(mProfilePageInfo) +
                ", \nmPrsid='" + mPrsid + '\'' +
                ", \nmRelation=" + mRelation +
                ", \nmSearchUssid='" + mSearchUssid + '\'' +
                ", \nmSex='" + mSex + '\'' +
                ", \nmSubtitle='" + mSubtitle + '\'' +
                ", \nmText='" + mText + '\'' +
                ", \nmUserAge=" + mUserAge +
                ", \nmUserMessageDeny=" + mUserMessageDeny +
                ", \nmVerified=" + mVerified +
                ", \nmVerifiedDetail=" + JSON.toJSONString(mVerifiedDetail) +
                " \n}";
    }
}