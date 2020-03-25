package com.micro.tremolo.inflood.execute.item;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/24 13:27
 * @Source 抖音版本960 com.ss.android.ugc.aweme.profile.model.User
 */
public class User {

    private boolean acceptPrivatePolicy;
    private String accountRegion;
    private Object adCoverTitle;//AdCoverTitle.class
    private List<Object> adCoverUrl;//UrlModel.class
    private String adOrderId;
    private int allowStatus;
    private long authorityStatus;
    private Object avatarDecoration;//AvatarDecoration.class
    private Object avatarLarger;//UrlModel.class
    private Object avatarMedium;//UrlModel.class 中等头像
    private Object avatarPendantLarger;//UrlModel.class
    private Object avatarPendantMedium;//UrlModel.class
    private Object avatarPendantThumb;//UrlModel.class
    private Object avatarThumb;//UrlModel.class 小头像
    private boolean avatarUpdateReminder;
    private Object avatarVideoUri;//UrlModel.class
    private int awemeCount;
    private Object awemeCover;//UserAwemeCover.class
    private String bindPhone;
    private String bioEmail;
    private String bioSecureUrl;
    private String bioUrl;
    private String birthday;
    private int birthdayHideLevel;
    private Object brandInfo;//BlueVBrandInfo.class
    private boolean canModifySchoolInfo;
    private List<Object> challengeList;//Challenge.class 挑战
    private String city;
    private String cityName;
    private int collectCount;
    private String collegeName;
    private int commentFilterStatus;
    private int commentSetting;
    private Object commerceInfo;//d
    private Object commercePermission;//CommercePermissionStruct.class
    private Object commerceUserInfo;//CommerceUserInfo.class
    private int commerceUserLevel;
    private int constellation;
    private String country;
    private List<Object> coverUrls;//UrlModel.class  覆盖路径
    private Long createTime;
    private String customVerify;//官方认证
    private boolean displayWvalantineActivityEntry;
    private String district;
    private int dongtai_count;
    private int douPlusShareLocation;
    private int downloadSetting;
    private int duetSetting;
    private int education;
    private Object effectArtistDetail;//EffectArtistDetail.class
    private String email;
    private String enrollYear;
    private String enterpriseVerifyReason;//企业认证
    private int fansCount; //粉丝数
    private int favoritingCount; //获赞数
    private long fbExpireTime;
    private int followStatus;
    private int followerCount; //关注数
    private List<Object> followerDetailList;//FollowerDetail.class 关注者列表
    private int followerStatus;
    private int followingCount;
    private boolean forcePrivateAccount;
    private int gender;
    private String googleAccount;
    private boolean hasEmail;
    private boolean hasFacebookToken;
    private boolean hasMedal;
    private boolean hasOrders;
    private boolean hasStory;
    private boolean hasTwitterToken;
    private Boolean hasUnreadStory;
    private boolean hasYoutubeToken;
    private boolean hideCity;
    private int hideFollowingFollowerList;
    private boolean hideSearch;
    private List<Object> homepageBottomToast;//HomePageBottomToast.class
    private Object honorStruct;//HonorStruct.class
    private String insId;
    private boolean isActivityUser;
    private boolean isAdFake;
    private boolean isBindedWeibo;
    private boolean isBlock;
    private boolean isBlocked;
    private boolean isContentLanguageDialogShown;
    private boolean isCreater;
    private boolean isDisciplineMember;
    private boolean isEffectArtist;
    private boolean isEmailVerified;
    private boolean isFlowcardMember;
    private boolean isMinor;
    private boolean isNewRecommend;
    private boolean isOldDouplusUser;
    private boolean isPhoneBinded;
    private boolean isProAccount;
    private boolean isStar;
    private int isSyncToutiao;
    private boolean isVerified;
    private String isoCountryCode;
    private String language;
    private Long latestOrderTime;
    private List<Object> latestStoryCover;//UrlModel.class
    private int liveAgreement;
    private boolean liveCommerce;
    private int loginPlatform;
    private int mAtType;
    private Object mGeneralPermission;//GeneralPermission
    private Object mHotListStruct;//HotListStruct
    private boolean mIsGovMediaVip;
    private String nameField;
    private boolean needAddrCard;
    private List<Object> needPoints;//NeedPointStruct
    private boolean needRecommend;
    private int neiguangShield;
    private String nickname;//昵称
    private boolean nicknameUpdateReminder;
    private int notifyPrivateAccount;
    private Object originalMusician;//g
    private Object platformInfos;//PlatformInfo[]
    private boolean postDefaultDownloadSetting;
    private boolean preventDownload;
    private int privateAwemeCount;
    private float profileCompletion;
    private long profilePv;
    private String province;
    private Object quickShopInfo;//QuickShopInfo
    private Object rFansGroupInfo;//RocketFansGroupInfo
    private List<Object> recommendAwemeItems;//RecommendAwemeItem
    private String recommendReason;
    private String recommendReasonRelation;
    private double recommendScore;
    private String region;
    private int registerStatus;
    private long registerTime;
    private String relationLabel;
    private List<Object> relativeUserInfos;//RelativeUserInfo
    private String remarkName;
    private String requestId;
    private Object roomCover;//UrlModel
    private String roomData;
    private long roomId;
    private String roomTypeTag;
    private int schoolInfoShowRange;
    private String schoolName;
    private String schoolPoiId;
    private int schoolType;
    private String secUid;
    private boolean secret;
    private Object shareInfo;//ShareInfo 分享信息
    private int shieldCommentNotice;
    private int shieldDiggNotice;
    private int shieldFollowNotice;
    private String shopMicroApp;
    private String shortId;//抖音号
    private int showArtistPlaylist;
    private boolean showFavoriteList;
    private int showGenderStrategy;
    private boolean showImageBubble;
    private String signature;//签名
    private String signatureLanguage;
    private Object sprintSupportUserInfo;//HotSearchSprintStruct
    private int starBillboardRank;
    private boolean starUseNewDownload;
    private Object storyBlockInfo;//StoryBlockInfo
    private int storyCount;
    private boolean storyOpen;
    private Object tabSetting;//TabSetting
    private int tabType;
    private String thirdName;
    private int totalFavorited;
    private long twExpireTime;
    private String twitterId;
    private String twitterName;
    private long[] typeLabels;
    private String uid;
    private String uniqueId;//抖音号，显示优先级比shortId高
    private long unique_id_modify_time;
    private Object urgeDetail;//UrgeStruct
    private boolean userCancelled;
    private Object userHonor;//UserHonor
    private int userMode = -1;
    private int userPeriod;
    private int userRate;
    private Object userRateRemindInfo;//UserRateRemindInfo
    private int userStoryCount;
    private String vcdSchemaUrl;
    private int verificationBadgeType;
    private int verificationType;
    private String verifyInfo;
    private int verifyStatus;
    private Object videoCover;//VideoCover
    private int watchStatus;
    private String weiboNickname;
    private String weiboSchema;
    private String weiboUrl;
    private String weiboVerify;
    private boolean withCommerceEnterpriseTabEntry;
    private boolean withCommerceEntry;
    private boolean withCommerceNewbieTask;
    private boolean withDouEntry = true;
    private boolean withDouplusEntry;
    private boolean withFusionShopEntry;
    private boolean withItemCommerceEntry;
    private boolean withLubanEntry;
    private boolean withNewGoods;
    private boolean withStarAtlasEntry;
    private int wxTag;
    private int xmasUnlockCount;
    private long youTubeLastRefreshTime;
    private String youTubeRefreshToken;
    private String youtubeChannelId;
    private String youtubeChannelTitle;
    private long youtubeExpireTime;

    public User(Hook hook, Object user) {
        this.acceptPrivatePolicy = isAcceptPrivatePolicy(hook, user);
        this.accountRegion = getAccountRegion(hook, user);
        this.adCoverTitle = getAdCoverTitle(hook, user);
        this.adCoverUrl = (List<Object>) getAdCoverUrl(hook, user);
        this.adOrderId = getAdOrderId(hook, user);
        this.allowStatus = getAllowStatus(hook, user);
        this.authorityStatus = getAuthorityStatus(hook, user);
        this.avatarDecoration = getAvatarDecoration(hook, user);
        this.avatarLarger = getAvatarLarger(hook, user);
        this.avatarMedium = getAvatarMedium(hook, user);
        this.avatarPendantLarger = getAvatarPendantLarger(hook, user);
        this.avatarPendantMedium = getAvatarPendantMedium(hook, user);
        this.avatarPendantThumb = getAvatarPendantThumb(hook, user);
        this.avatarThumb = getAvatarThumb(hook, user);
        this.avatarUpdateReminder = isAvatarUpdateReminder(hook, user);
        this.avatarVideoUri = getAvatarVideoUri(hook, user);
        this.awemeCount = getAwemeCount(hook, user);
        this.awemeCover = getAwemeCover(hook, user);
        this.bindPhone = getBindPhone(hook, user);
        this.bioEmail = getBioEmail(hook, user);
        this.bioSecureUrl = getBioSecureUrl(hook, user);
        this.bioUrl = getBioUrl(hook, user);
        this.birthday = getBirthday(hook, user);
        this.birthdayHideLevel = getBirthdayHideLevel(hook, user);
        this.brandInfo = getBrandInfo(hook, user);
        this.canModifySchoolInfo = isCanModifySchoolInfo(hook, user);
        this.challengeList = (List<Object>) getChallengeList(hook, user);
        this.city = getLocation(hook, user);
        this.cityName = getCity(hook, user);
        this.collectCount = getCollectCount(hook, user);
        this.collegeName = getCollegeName(hook, user);
        this.commentFilterStatus = getCommentFilterStatus(hook, user);
        this.commentSetting = getCommentSetting(hook, user);
        this.commerceInfo = getCommerceInfo(hook, user);
        this.commercePermission = getCommercePermission(hook, user);
        this.commerceUserInfo = getCommerceUserInfo(hook, user);
        this.commerceUserLevel = getCommerceUserLevel(hook, user);
        this.constellation = getConstellation(hook, user);
        this.country = getCountry(hook, user);
        this.coverUrls = (List<Object>) getCoverUrls(hook, user);
        /*this.createTime = getCreateTime(hook, user);*/
        this.customVerify = getCustomVerify(hook, user);
        this.displayWvalantineActivityEntry = getDisplayWvalantineActivityEntry(hook, user);
        this.district = getDistrict(hook, user);
        this.dongtai_count = getDongtaiCount(hook, user);
        this.douPlusShareLocation = getDouPlusShareLocation(hook, user);
        this.downloadSetting = getDownloadSetting(hook, user);
        this.duetSetting = getDuetSetting(hook, user);
        this.education = getEducation(hook, user);
        this.effectArtistDetail = getEffectArtistDetail(hook, user);
        this.email = getEmail(hook, user);
        this.enrollYear = getEnrollYear(hook, user);
        this.enterpriseVerifyReason = getEnterpriseVerifyReason(hook, user);
        this.fansCount = getFansCount(hook, user);
        this.favoritingCount = getFavoritingCount(hook, user);
        this.fbExpireTime = getFbExpireTime(hook, user);
        this.followStatus = getFollowStatus(hook, user);
        this.followerCount = getFollowerCount(hook, user);
        this.followerDetailList = (List<Object>) getFollowerDetailList(hook, user);
        this.followerStatus = getFollowerStatus(hook, user);
        this.followingCount = getFollowingCount(hook, user);
        this.forcePrivateAccount = getForcePrivateAccount(hook, user);
        this.gender = getGender(hook, user);
        this.googleAccount = getGoogleAccount(hook, user);
        this.hasEmail = isHasEmail(hook, user);
        this.hasFacebookToken = isHasFacebookToken(hook, user);
        this.hasMedal = isHasMedal(hook, user);
        this.hasOrders = isHasOrders(hook, user);
        this.hasStory = getHasStory(hook, user);
        this.hasTwitterToken = isHasTwitterToken(hook, user);
        /*this.hasUnreadStory = isHasUnreadStory(hook, user);*/
        this.hasYoutubeToken = isHasYoutubeToken(hook, user);
        this.hideCity = isHideCity(hook, user);
        this.hideFollowingFollowerList = getHideFollowingFollowerList(hook, user);
        this.hideSearch = isHideSearch(hook, user);
        this.homepageBottomToast = (List<Object>) getHomepageBottomToast(hook, user);
        this.honorStruct = getHonorStruct(hook, user);
        this.insId = getInsId(hook, user);
        this.isActivityUser = isActivityUser(hook, user);
        this.isAdFake = isAdFake(hook, user);
        this.isBindedWeibo = isBindedWeibo(hook, user);
        this.isBlock = isBlock(hook, user);
        this.isBlocked = isBlocked(hook, user);
        this.isContentLanguageDialogShown = isContentLanguageDialogShown(hook, user);
        this.isCreater = isCreater(hook, user);
        this.isDisciplineMember = isDisciplineMember(hook, user);
        this.isEffectArtist = isEffectArtist(hook, user);
        this.isEmailVerified = isEmailVerified(hook, user);
        this.isFlowcardMember = isFlowcardMember(hook, user);
        this.isMinor = isMinor(hook, user);
        this.isNewRecommend = isNewRecommend(hook, user);
        this.isOldDouplusUser = isOldDouplusUser(hook, user);
        this.isPhoneBinded = isPhoneBinded(hook, user);
        this.isProAccount = isProAccount(hook, user);
        this.isStar = isStar(hook, user);
        this.isSyncToutiao = getIsSyncToutiao(hook, user);
        this.isVerified = isVerified(hook, user);
        this.isoCountryCode = getIsoCountryCode(hook, user);
        this.language = getLanguage(hook, user);
        /*this.latestOrderTime = getLatestOrderTime(hook, user);*/
        this.latestStoryCover = (List<Object>) getLatestStoryCover(hook, user);
        this.liveAgreement = getLiveAgreement(hook, user);
        this.liveCommerce = isLiveCommerce(hook, user);
        this.loginPlatform = getLoginPlatform(hook, user);
        this.mAtType = getMAtType(hook, user);
        this.mGeneralPermission = getMGeneralPermission(hook, user);
        this.mHotListStruct = getMHotListStruct(hook, user);
        this.mIsGovMediaVip = isMIsGovMediaVip(hook, user);
        this.nameField = getNameField(hook, user);
        this.needAddrCard = isNeedAddrCard(hook, user);
        this.needPoints = (List<Object>) getNeedPoints(hook, user);
        this.needRecommend = isNeedRecommend(hook, user);
        this.neiguangShield = getNeiguangShield(hook, user);
        this.nickname = getNickname(hook, user);
        this.nicknameUpdateReminder = isNicknameUpdateReminder(hook, user);
        this.notifyPrivateAccount = getNotifyPrivateAccount(hook, user);
        this.originalMusician = getOriginalMusician(hook, user);
        this.platformInfos = getPlatformInfos(hook, user);
        this.postDefaultDownloadSetting = isPostDefaultDownloadSetting(hook, user);
        this.preventDownload = isPreventDownload(hook, user);
        this.privateAwemeCount = getPrivateAwemeCount(hook, user);
        this.profileCompletion = getProfileCompletion(hook, user);
        this.profilePv = getProfilePv(hook, user);
        this.province = getProvince(hook, user);
        this.quickShopInfo = getQuickShopInfo(hook, user);
        this.rFansGroupInfo = getRFansGroupInfo(hook, user);
        this.recommendAwemeItems = (List<Object>) getRecommendAwemeItems(hook, user);
        this.recommendReason = getRecommendReason(hook, user);
        this.recommendReasonRelation = getRecommendReasonRelation(hook, user);
        this.recommendScore = getRecommendScore(hook, user);
        this.region = getRegion(hook, user);
        this.registerStatus = getRegisterStatus(hook, user);
        this.registerTime = getRegisterTime(hook, user);
        this.relationLabel = getRelationLabel(hook, user);
        this.relativeUserInfos = (List<Object>) getRelativeUserInfos(hook, user);
        this.remarkName = getRemarkName(hook, user);
        this.requestId = getRequestId(hook, user);
        this.roomCover = getRoomCover(hook, user);
        this.roomData = getRoomData(hook, user);
        this.roomId = getRoomId(hook, user);
        this.roomTypeTag = getRoomTypeTag(hook, user);
        this.schoolInfoShowRange = getSchoolInfoShowRange(hook, user);
        this.schoolName = getSchoolName(hook, user);
        this.schoolPoiId = getSchoolPoiId(hook, user);
        this.schoolType = getSchoolType(hook, user);
        this.secUid = getSecUid(hook, user);
        this.secret = isSecret(hook, user);
        this.shareInfo = getShareInfo(hook, user);
        this.shieldCommentNotice = getShieldCommentNotice(hook, user);
        this.shieldDiggNotice = getShieldDiggNotice(hook, user);
        this.shieldFollowNotice = getShieldFollowNotice(hook, user);
        this.shopMicroApp = getShopMicroApp(hook, user);
        this.shortId = getShortId(hook, user);
        this.showArtistPlaylist = getShowArtistPlaylist(hook, user);
        this.showFavoriteList = isShowFavoriteList(hook, user);
        this.showGenderStrategy = getShowGenderStrategy(hook, user);
        this.showImageBubble = isShowImageBubble(hook, user);
        this.signature = getSignature(hook, user);
        this.signatureLanguage = getSignatureLanguage(hook, user);
        this.sprintSupportUserInfo = getSprintSupportUserInfo(hook, user);
        this.starBillboardRank = getStarBillboardRank(hook, user);
        this.starUseNewDownload = isStarUseNewDownload(hook, user);
        this.storyBlockInfo = getStoryBlockInfo(hook, user);
        this.storyCount = getStoryCount(hook, user);
        this.storyOpen = isStoryOpen(hook, user);
        this.tabSetting = getTabSetting(hook, user);
        this.tabType = getTabType(hook, user);
        this.thirdName = getThirdName(hook, user);
        this.totalFavorited = getTotalFavorited(hook, user);
        this.twExpireTime = getTwExpireTime(hook, user);
        this.twitterId = getTwitterId(hook, user);
        this.twitterName = getTwitterName(hook, user);
        this.typeLabels = getTypeLabels(hook, user);
        this.uid = getUid(hook, user);
        this.uniqueId = getUniqueId(hook, user);
        this.unique_id_modify_time = getUniqueIdModifyTime(hook, user);
        this.urgeDetail = getUrgeDetail(hook, user);
        this.userCancelled = isUserCancelled(hook, user);
        this.userHonor = getUserHonor(hook, user);
        this.userMode = getUserMode(hook, user);
        this.userPeriod = getUserPeriod(hook, user);
        this.userRate = getUserRate(hook, user);
        this.userRateRemindInfo = getUserRateRemindInfo(hook, user);
        this.userStoryCount = getUserStoryCount(hook, user);
        this.vcdSchemaUrl = getVcdSchemaUrl(hook, user);
        this.verificationBadgeType = getVerificationBadgeType(hook, user);
        this.verificationType = getVerificationType(hook, user);
        this.verifyInfo = getVerifyInfo(hook, user);
        this.verifyStatus = getVerifyStatus(hook, user);
        this.videoCover = getVideoCover(hook, user);
        this.watchStatus = getWatchStatus(hook, user);
        this.weiboNickname = getWeiboNickname(hook, user);
        this.weiboSchema = getWeiboSchema(hook, user);
        this.weiboUrl = getWeiboUrl(hook, user);
        this.weiboVerify = getWeiboVerify(hook, user);
        this.withCommerceEnterpriseTabEntry = isWithCommerceEnterpriseTabEntry(hook, user);
        this.withCommerceEntry = isWithCommerceEntry(hook, user);
        this.withCommerceNewbieTask = isWithCommerceNewbieTask(hook, user);
        this.withDouEntry = isWithDouEntry(hook, user);
        this.withDouplusEntry = isWithDouplusEntry(hook, user);
        this.withFusionShopEntry = isWithFusionShopEntry(hook, user);
        this.withItemCommerceEntry = isWithItemCommerceEntry(hook, user);
        this.withLubanEntry = isWithLubanEntry(hook, user);
        this.withNewGoods = isWithNewGoods(hook, user);
        this.withStarAtlasEntry = isWithStarAtlasEntry(hook, user);
        this.wxTag = getWxTag(hook, user);
        this.xmasUnlockCount = getXmasUnlockCount(hook, user);
        this.youTubeLastRefreshTime = getYouTubeLastRefreshTime(hook, user);
        this.youTubeRefreshToken = getYouTubeRefreshToken(hook, user);
        this.youtubeChannelId = getYoutubeChannelId(hook, user);
        this.youtubeChannelTitle = getYoutubeChannelTitle(hook, user);
        this.youtubeExpireTime = getYoutubeExpireTime(hook, user);
    }

    public boolean isAcceptPrivatePolicy() {
        return acceptPrivatePolicy;
    }

    public String getAccountRegion() {
        return accountRegion;
    }

    public Object getAdCoverTitle() {
        return adCoverTitle;
    }

    public List<Object> getAdCoverUrl() {
        return adCoverUrl;
    }

    public String getAdOrderId() {
        return adOrderId;
    }

    public int getAllowStatus() {
        return allowStatus;
    }

    public long getAuthorityStatus() {
        return authorityStatus;
    }

    public Object getAvatarDecoration() {
        return avatarDecoration;
    }

    public Object getAvatarLarger() {
        return avatarLarger;
    }

    public Object getAvatarMedium() {
        return avatarMedium;
    }

    public Object getAvatarPendantLarger() {
        return avatarPendantLarger;
    }

    public Object getAvatarPendantMedium() {
        return avatarPendantMedium;
    }

    public Object getAvatarPendantThumb() {
        return avatarPendantThumb;
    }

    public Object getAvatarThumb() {
        return avatarThumb;
    }

    public boolean isAvatarUpdateReminder() {
        return avatarUpdateReminder;
    }

    public Object getAvatarVideoUri() {
        return avatarVideoUri;
    }

    public int getAwemeCount() {
        return awemeCount;
    }

    public Object getAwemeCover() {
        return awemeCover;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public String getBioEmail() {
        return bioEmail;
    }

    public String getBioSecureUrl() {
        return bioSecureUrl;
    }

    public String getBioUrl() {
        return bioUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getBirthdayHideLevel() {
        return birthdayHideLevel;
    }

    public Object getBrandInfo() {
        return brandInfo;
    }

    public boolean isCanModifySchoolInfo() {
        return canModifySchoolInfo;
    }

    public List<Object> getChallengeList() {
        return challengeList;
    }

    public String getCity() {
        return city;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public int getCommentFilterStatus() {
        return commentFilterStatus;
    }

    public int getCommentSetting() {
        return commentSetting;
    }

    public Object getCommerceInfo() {
        return commerceInfo;
    }

    public Object getCommercePermission() {
        return commercePermission;
    }

    public Object getCommerceUserInfo() {
        return commerceUserInfo;
    }

    public int getCommerceUserLevel() {
        return commerceUserLevel;
    }

    public int getConstellation() {
        return constellation;
    }

    public String getCountry() {
        return country;
    }

    public List<Object> getCoverUrls() {
        return coverUrls;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getCustomVerify() {
        return customVerify;
    }

    public boolean isDisplayWvalantineActivityEntry() {
        return displayWvalantineActivityEntry;
    }

    public String getDistrict() {
        return district;
    }

    public int getDongtai_count() {
        return dongtai_count;
    }

    public int getDouPlusShareLocation() {
        return douPlusShareLocation;
    }

    public int getDownloadSetting() {
        return downloadSetting;
    }

    public int getDuetSetting() {
        return duetSetting;
    }

    public int getEducation() {
        return education;
    }

    public Object getEffectArtistDetail() {
        return effectArtistDetail;
    }

    public String getEmail() {
        return email;
    }

    public String getEnrollYear() {
        return enrollYear;
    }

    public String getEnterpriseVerifyReason() {
        return enterpriseVerifyReason;
    }

    public int getFansCount() {
        return fansCount;
    }

    public int getFavoritingCount() {
        return favoritingCount;
    }

    public long getFbExpireTime() {
        return fbExpireTime;
    }

    public int getFollowStatus() {
        return followStatus;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public List<Object> getFollowerDetailList() {
        return followerDetailList;
    }

    public int getFollowerStatus() {
        return followerStatus;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public boolean isForcePrivateAccount() {
        return forcePrivateAccount;
    }

    public int getGender() {
        return gender;
    }

    public String getGoogleAccount() {
        return googleAccount;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }

    public boolean isHasFacebookToken() {
        return hasFacebookToken;
    }

    public boolean isHasMedal() {
        return hasMedal;
    }

    public boolean isHasOrders() {
        return hasOrders;
    }

    public boolean isHasStory() {
        return hasStory;
    }

    public boolean isHasTwitterToken() {
        return hasTwitterToken;
    }

    public Boolean getHasUnreadStory() {
        return hasUnreadStory;
    }

    public boolean isHasYoutubeToken() {
        return hasYoutubeToken;
    }

    public boolean isHideCity() {
        return hideCity;
    }

    public int getHideFollowingFollowerList() {
        return hideFollowingFollowerList;
    }

    public boolean isHideSearch() {
        return hideSearch;
    }

    public List<Object> getHomepageBottomToast() {
        return homepageBottomToast;
    }

    public Object getHonorStruct() {
        return honorStruct;
    }

    public String getInsId() {
        return insId;
    }

    public boolean isActivityUser() {
        return isActivityUser;
    }

    public boolean isAdFake() {
        return isAdFake;
    }

    public boolean isBindedWeibo() {
        return isBindedWeibo;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isContentLanguageDialogShown() {
        return isContentLanguageDialogShown;
    }

    public boolean isCreater() {
        return isCreater;
    }

    public boolean isDisciplineMember() {
        return isDisciplineMember;
    }

    public boolean isEffectArtist() {
        return isEffectArtist;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public boolean isFlowcardMember() {
        return isFlowcardMember;
    }

    public boolean isMinor() {
        return isMinor;
    }

    public boolean isNewRecommend() {
        return isNewRecommend;
    }

    public boolean isOldDouplusUser() {
        return isOldDouplusUser;
    }

    public boolean isPhoneBinded() {
        return isPhoneBinded;
    }

    public boolean isProAccount() {
        return isProAccount;
    }

    public boolean isStar() {
        return isStar;
    }

    public int getIsSyncToutiao() {
        return isSyncToutiao;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public String getLanguage() {
        return language;
    }

    public Long getLatestOrderTime() {
        return latestOrderTime;
    }

    public List<Object> getLatestStoryCover() {
        return latestStoryCover;
    }

    public int getLiveAgreement() {
        return liveAgreement;
    }

    public boolean isLiveCommerce() {
        return liveCommerce;
    }

    public int getLoginPlatform() {
        return loginPlatform;
    }

    public int getmAtType() {
        return mAtType;
    }

    public Object getmGeneralPermission() {
        return mGeneralPermission;
    }

    public Object getmHotListStruct() {
        return mHotListStruct;
    }

    public boolean ismIsGovMediaVip() {
        return mIsGovMediaVip;
    }

    public String getNameField() {
        return nameField;
    }

    public boolean isNeedAddrCard() {
        return needAddrCard;
    }

    public List<Object> getNeedPoints() {
        return needPoints;
    }

    public boolean isNeedRecommend() {
        return needRecommend;
    }

    public int getNeiguangShield() {
        return neiguangShield;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isNicknameUpdateReminder() {
        return nicknameUpdateReminder;
    }

    public int getNotifyPrivateAccount() {
        return notifyPrivateAccount;
    }

    public Object getOriginalMusician() {
        return originalMusician;
    }

    public Object getPlatformInfos() {
        return platformInfos;
    }

    public boolean isPostDefaultDownloadSetting() {
        return postDefaultDownloadSetting;
    }

    public boolean isPreventDownload() {
        return preventDownload;
    }

    public int getPrivateAwemeCount() {
        return privateAwemeCount;
    }

    public float getProfileCompletion() {
        return profileCompletion;
    }

    public long getProfilePv() {
        return profilePv;
    }

    public String getProvince() {
        return province;
    }

    public Object getQuickShopInfo() {
        return quickShopInfo;
    }

    public Object getrFansGroupInfo() {
        return rFansGroupInfo;
    }

    public List<Object> getRecommendAwemeItems() {
        return recommendAwemeItems;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public String getRecommendReasonRelation() {
        return recommendReasonRelation;
    }

    public double getRecommendScore() {
        return recommendScore;
    }

    public String getRegion() {
        return region;
    }

    public int getRegisterStatus() {
        return registerStatus;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public String getRelationLabel() {
        return relationLabel;
    }

    public List<Object> getRelativeUserInfos() {
        return relativeUserInfos;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public String getRequestId() {
        return requestId;
    }

    public Object getRoomCover() {
        return roomCover;
    }

    public String getRoomData() {
        return roomData;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getRoomTypeTag() {
        return roomTypeTag;
    }

    public int getSchoolInfoShowRange() {
        return schoolInfoShowRange;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolPoiId() {
        return schoolPoiId;
    }

    public int getSchoolType() {
        return schoolType;
    }

    public String getSecUid() {
        return secUid;
    }

    public boolean isSecret() {
        return secret;
    }

    public Object getShareInfo() {
        return shareInfo;
    }

    public int getShieldCommentNotice() {
        return shieldCommentNotice;
    }

    public int getShieldDiggNotice() {
        return shieldDiggNotice;
    }

    public int getShieldFollowNotice() {
        return shieldFollowNotice;
    }

    public String getShopMicroApp() {
        return shopMicroApp;
    }

    public String getShortId() {
        return shortId;
    }

    public int getShowArtistPlaylist() {
        return showArtistPlaylist;
    }

    public boolean isShowFavoriteList() {
        return showFavoriteList;
    }

    public int getShowGenderStrategy() {
        return showGenderStrategy;
    }

    public boolean isShowImageBubble() {
        return showImageBubble;
    }

    public String getSignature() {
        return signature;
    }

    public String getSignatureLanguage() {
        return signatureLanguage;
    }

    public Object getSprintSupportUserInfo() {
        return sprintSupportUserInfo;
    }

    public int getStarBillboardRank() {
        return starBillboardRank;
    }

    public boolean isStarUseNewDownload() {
        return starUseNewDownload;
    }

    public Object getStoryBlockInfo() {
        return storyBlockInfo;
    }

    public int getStoryCount() {
        return storyCount;
    }

    public boolean isStoryOpen() {
        return storyOpen;
    }

    public Object getTabSetting() {
        return tabSetting;
    }

    public int getTabType() {
        return tabType;
    }

    public String getThirdName() {
        return thirdName;
    }

    public int getTotalFavorited() {
        return totalFavorited;
    }

    public long getTwExpireTime() {
        return twExpireTime;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public long[] getTypeLabels() {
        return typeLabels;
    }

    public String getUid() {
        return uid;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public long getUnique_id_modify_time() {
        return unique_id_modify_time;
    }

    public Object getUrgeDetail() {
        return urgeDetail;
    }

    public boolean isUserCancelled() {
        return userCancelled;
    }

    public Object getUserHonor() {
        return userHonor;
    }

    public int getUserMode() {
        return userMode;
    }

    public int getUserPeriod() {
        return userPeriod;
    }

    public int getUserRate() {
        return userRate;
    }

    public Object getUserRateRemindInfo() {
        return userRateRemindInfo;
    }

    public int getUserStoryCount() {
        return userStoryCount;
    }

    public String getVcdSchemaUrl() {
        return vcdSchemaUrl;
    }

    public int getVerificationBadgeType() {
        return verificationBadgeType;
    }

    public int getVerificationType() {
        return verificationType;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public Object getVideoCover() {
        return videoCover;
    }

    public int getWatchStatus() {
        return watchStatus;
    }

    public String getWeiboNickname() {
        return weiboNickname;
    }

    public String getWeiboSchema() {
        return weiboSchema;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public String getWeiboVerify() {
        return weiboVerify;
    }

    public boolean isWithCommerceEnterpriseTabEntry() {
        return withCommerceEnterpriseTabEntry;
    }

    public boolean isWithCommerceEntry() {
        return withCommerceEntry;
    }

    public boolean isWithCommerceNewbieTask() {
        return withCommerceNewbieTask;
    }

    public boolean isWithDouEntry() {
        return withDouEntry;
    }

    public boolean isWithDouplusEntry() {
        return withDouplusEntry;
    }

    public boolean isWithFusionShopEntry() {
        return withFusionShopEntry;
    }

    public boolean isWithItemCommerceEntry() {
        return withItemCommerceEntry;
    }

    public boolean isWithLubanEntry() {
        return withLubanEntry;
    }

    public boolean isWithNewGoods() {
        return withNewGoods;
    }

    public boolean isWithStarAtlasEntry() {
        return withStarAtlasEntry;
    }

    public int getWxTag() {
        return wxTag;
    }

    public int getXmasUnlockCount() {
        return xmasUnlockCount;
    }

    public long getYouTubeLastRefreshTime() {
        return youTubeLastRefreshTime;
    }

    public String getYouTubeRefreshToken() {
        return youTubeRefreshToken;
    }

    public String getYoutubeChannelId() {
        return youtubeChannelId;
    }

    public String getYoutubeChannelTitle() {
        return youtubeChannelTitle;
    }

    public long getYoutubeExpireTime() {
        return youtubeExpireTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "acceptPrivatePolicy=" + acceptPrivatePolicy +
                ", accountRegion='" + accountRegion + '\'' +
                ", adOrderId='" + adOrderId + '\'' +
                ", allowStatus=" + allowStatus +
                ", authorityStatus=" + authorityStatus +
                ", avatarUpdateReminder=" + avatarUpdateReminder +
                ", awemeCount=" + awemeCount +
                ", bindPhone='" + bindPhone + '\'' +
                ", bioEmail='" + bioEmail + '\'' +
                ", bioSecureUrl='" + bioSecureUrl + '\'' +
                ", bioUrl='" + bioUrl + '\'' +
                ", birthday='" + birthday + '\'' +
                ", birthdayHideLevel=" + birthdayHideLevel +
                ", canModifySchoolInfo=" + canModifySchoolInfo +
                ", city='" + city + '\'' +
                ", cityName='" + cityName + '\'' +
                ", collectCount=" + collectCount +
                ", collegeName='" + collegeName + '\'' +
                ", commentFilterStatus=" + commentFilterStatus +
                ", commentSetting=" + commentSetting +
                ", commerceUserLevel=" + commerceUserLevel +
                ", constellation=" + constellation +
                ", country='" + country + '\'' +
                ", createTime=" + createTime +
                ", customVerify='" + customVerify + '\'' +
                ", displayWvalantineActivityEntry=" + displayWvalantineActivityEntry +
                ", district='" + district + '\'' +
                ", dongtai_count=" + dongtai_count +
                ", douPlusShareLocation=" + douPlusShareLocation +
                ", downloadSetting=" + downloadSetting +
                ", duetSetting=" + duetSetting +
                ", education=" + education +
                ", email='" + email + '\'' +
                ", enrollYear='" + enrollYear + '\'' +
                ", enterpriseVerifyReason='" + enterpriseVerifyReason + '\'' +
                ", fansCount=" + fansCount +
                ", favoritingCount=" + favoritingCount +
                ", fbExpireTime=" + fbExpireTime +
                ", followStatus=" + followStatus +
                ", followerCount=" + followerCount +
                ", followerStatus=" + followerStatus +
                ", followingCount=" + followingCount +
                ", forcePrivateAccount=" + forcePrivateAccount +
                ", gender=" + gender +
                ", googleAccount='" + googleAccount + '\'' +
                ", hasEmail=" + hasEmail +
                ", hasFacebookToken=" + hasFacebookToken +
                ", hasMedal=" + hasMedal +
                ", hasOrders=" + hasOrders +
                ", hasStory=" + hasStory +
                ", hasTwitterToken=" + hasTwitterToken +
                ", hasUnreadStory=" + hasUnreadStory +
                ", hasYoutubeToken=" + hasYoutubeToken +
                ", hideCity=" + hideCity +
                ", hideFollowingFollowerList=" + hideFollowingFollowerList +
                ", hideSearch=" + hideSearch +
                ", insId='" + insId + '\'' +
                ", isActivityUser=" + isActivityUser +
                ", isAdFake=" + isAdFake +
                ", isBindedWeibo=" + isBindedWeibo +
                ", isBlock=" + isBlock +
                ", isBlocked=" + isBlocked +
                ", isContentLanguageDialogShown=" + isContentLanguageDialogShown +
                ", isCreater=" + isCreater +
                ", isDisciplineMember=" + isDisciplineMember +
                ", isEffectArtist=" + isEffectArtist +
                ", isEmailVerified=" + isEmailVerified +
                ", isFlowcardMember=" + isFlowcardMember +
                ", isMinor=" + isMinor +
                ", isNewRecommend=" + isNewRecommend +
                ", isOldDouplusUser=" + isOldDouplusUser +
                ", isPhoneBinded=" + isPhoneBinded +
                ", isProAccount=" + isProAccount +
                ", isStar=" + isStar +
                ", isSyncToutiao=" + isSyncToutiao +
                ", isVerified=" + isVerified +
                ", isoCountryCode='" + isoCountryCode + '\'' +
                ", language='" + language + '\'' +
                ", latestOrderTime=" + latestOrderTime +
                ", liveAgreement=" + liveAgreement +
                ", liveCommerce=" + liveCommerce +
                ", loginPlatform=" + loginPlatform +
                ", mAtType=" + mAtType +
                ", mIsGovMediaVip=" + mIsGovMediaVip +
                ", nameField='" + nameField + '\'' +
                ", needAddrCard=" + needAddrCard +
                ", needRecommend=" + needRecommend +
                ", neiguangShield=" + neiguangShield +
                ", nickname='" + nickname + '\'' +
                ", nicknameUpdateReminder=" + nicknameUpdateReminder +
                ", notifyPrivateAccount=" + notifyPrivateAccount +
                ", postDefaultDownloadSetting=" + postDefaultDownloadSetting +
                ", preventDownload=" + preventDownload +
                ", privateAwemeCount=" + privateAwemeCount +
                ", profileCompletion=" + profileCompletion +
                ", profilePv=" + profilePv +
                ", province='" + province + '\'' +
                ", recommendReason='" + recommendReason + '\'' +
                ", recommendReasonRelation='" + recommendReasonRelation + '\'' +
                ", recommendScore=" + recommendScore +
                ", region='" + region + '\'' +
                ", registerStatus=" + registerStatus +
                ", registerTime=" + registerTime +
                ", relationLabel='" + relationLabel + '\'' +
                ", remarkName='" + remarkName + '\'' +
                ", requestId='" + requestId + '\'' +
                ", roomData='" + roomData + '\'' +
                ", roomId=" + roomId +
                ", roomTypeTag='" + roomTypeTag + '\'' +
                ", schoolInfoShowRange=" + schoolInfoShowRange +
                ", schoolName='" + schoolName + '\'' +
                ", schoolPoiId='" + schoolPoiId + '\'' +
                ", schoolType=" + schoolType +
                ", secUid='" + secUid + '\'' +
                ", secret=" + secret +
                ", shieldCommentNotice=" + shieldCommentNotice +
                ", shieldDiggNotice=" + shieldDiggNotice +
                ", shieldFollowNotice=" + shieldFollowNotice +
                ", shopMicroApp='" + shopMicroApp + '\'' +
                ", shortId='" + shortId + '\'' +
                ", showArtistPlaylist=" + showArtistPlaylist +
                ", showFavoriteList=" + showFavoriteList +
                ", showGenderStrategy=" + showGenderStrategy +
                ", showImageBubble=" + showImageBubble +
                ", signature='" + signature + '\'' +
                ", signatureLanguage='" + signatureLanguage + '\'' +
                ", starBillboardRank=" + starBillboardRank +
                ", starUseNewDownload=" + starUseNewDownload +
                ", storyCount=" + storyCount +
                ", storyOpen=" + storyOpen +
                ", tabType=" + tabType +
                ", thirdName='" + thirdName + '\'' +
                ", totalFavorited=" + totalFavorited +
                ", twExpireTime=" + twExpireTime +
                ", twitterId='" + twitterId + '\'' +
                ", twitterName='" + twitterName + '\'' +
                ", typeLabels=" + Arrays.toString(typeLabels) +
                ", uid='" + uid + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", unique_id_modify_time=" + unique_id_modify_time +
                ", userCancelled=" + userCancelled +
                ", userMode=" + userMode +
                ", userPeriod=" + userPeriod +
                ", userRate=" + userRate +
                ", userStoryCount=" + userStoryCount +
                ", vcdSchemaUrl='" + vcdSchemaUrl + '\'' +
                ", verificationBadgeType=" + verificationBadgeType +
                ", verificationType=" + verificationType +
                ", verifyInfo='" + verifyInfo + '\'' +
                ", verifyStatus=" + verifyStatus +
                ", watchStatus=" + watchStatus +
                ", weiboNickname='" + weiboNickname + '\'' +
                ", weiboSchema='" + weiboSchema + '\'' +
                ", weiboUrl='" + weiboUrl + '\'' +
                ", weiboVerify='" + weiboVerify + '\'' +
                ", withCommerceEnterpriseTabEntry=" + withCommerceEnterpriseTabEntry +
                ", withCommerceEntry=" + withCommerceEntry +
                ", withCommerceNewbieTask=" + withCommerceNewbieTask +
                ", withDouEntry=" + withDouEntry +
                ", withDouplusEntry=" + withDouplusEntry +
                ", withFusionShopEntry=" + withFusionShopEntry +
                ", withItemCommerceEntry=" + withItemCommerceEntry +
                ", withLubanEntry=" + withLubanEntry +
                ", withNewGoods=" + withNewGoods +
                ", withStarAtlasEntry=" + withStarAtlasEntry +
                ", wxTag=" + wxTag +
                ", xmasUnlockCount=" + xmasUnlockCount +
                ", youTubeLastRefreshTime=" + youTubeLastRefreshTime +
                ", youTubeRefreshToken='" + youTubeRefreshToken + '\'' +
                ", youtubeChannelId='" + youtubeChannelId + '\'' +
                ", youtubeChannelTitle='" + youtubeChannelTitle + '\'' +
                ", youtubeExpireTime=" + youtubeExpireTime +
                '}';
    }

    private Boolean isAcceptPrivatePolicy(Hook hook, Object user) {//接受隐私政策
        return hook.getBooleanField(user, "acceptPrivatePolicy");
    }

    private String getAccountRegion(Hook hook, Object user) {//帐户区域
        return (String) hook.getField(user, "accountRegion");
    }

    private Object getAdCoverTitle(Hook hook, Object user) {
        return hook.getField(user, "adCoverTitle");
    }

    private List<?> getAdCoverUrl(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "adCoverUrl");
    }

    private String getAdOrderId(Hook hook, Object user) {
        return (String) hook.getField(user, "adOrderId");
    }

    private Integer getAllowStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "allowStatus");
    }

    private Long getAuthorityStatus(Hook hook, Object user) {
        return hook.getLongField(user, "authorityStatus");
    }

    private Object getAvatarDecoration(Hook hook, Object user) {
        return hook.getField(user, "avatarDecoration");
    }

    private Object getAvatarLarger(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarLarger");
    }

    private Object getAvatarMedium(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarMedium");
    }

    private Object getAvatarPendantLarger(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarPendantLarger");
    }

    private Object getAvatarPendantMedium(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarPendantMedium");
    }

    private Object getAvatarPendantThumb(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarPendantThumb");
    }

    private Object getAvatarThumb(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarThumb");
    }

    private Object getAvatarVideoUri(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "avatarVideoUri");
    }

    private Integer getAwemeCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "awemeCount");
    }

    private Object getAwemeCover(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "awemeCover");
    }

    private String getBindPhone(Hook hook, Object user) {
        return (String) hook.getField(user, "bindPhone");
    }

    private String getBioEmail(Hook hook, Object user) {
        return (String) hook.getField(user, "bioEmail");
    }

    private String getBioSecureUrl(Hook hook, Object user) {
        return (String) hook.getField(user, "bioSecureUrl");
    }

    private String getBioUrl(Hook hook, Object user) {
        return (String) hook.getField(user, "bioUrl");
    }

    private String getBirthday(Hook hook, Object user) {
        return (String) hook.getField(user, "birthday");
    }

    private Integer getBirthdayHideLevel(Hook hook, Object user) {
        return hook.getIntegerField(user, "birthdayHideLevel");
    }

    private Object getBrandInfo(Hook hook, Object user) {
        return hook.getField(user, "brandInfo");
    }

    private Boolean isCanModifySchoolInfo(Hook hook, Object user) {
        return hook.getBooleanField(user, "canModifySchoolInfo");
    }

    private List<?> getChallengeList(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "challengeList");
    }

    private String getLocation(Hook hook, Object user) {
        return (String) hook.getField(user, "city");
    }

    private String getCity(Hook hook, Object user) {
        return (String) hook.getField(user, "cityName");
    }

    private Integer getCollectCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "collectCount");
    }

    private String getProvince(Hook hook, Object user) {
        return (String) hook.getField(user, "province");
    }

    private Object getQuickShopInfo(Hook hook, Object user) {
        return hook.getField(user, "quickShopInfo");
    }

    private Object getRFansGroupInfo(Hook hook, Object user) {
        return hook.getField(user, "rFansGroupInfo");
    }

    private List<?> getRecommendAwemeItems(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "recommendAwemeItems");
    }

    private String getCountry(Hook hook, Object user) {
        return (String) hook.getField(user, "country");
    }

    private String getRegion(Hook hook, Object user) {
        return (String) hook.getField(user, "region");
    }

    private String getCollegeName(Hook hook, Object user) {
        return (String) hook.getField(user, "collegeName");
    }

    private Integer getCommentFilterStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "commentFilterStatus");
    }

    private Integer getCommentSetting(Hook hook, Object user) {
        return hook.getIntegerField(user, "commentSetting");
    }

    private Object getCommerceInfo(Hook hook, Object user) {//CommerceInfo.class
        return hook.getField(user, "commerceInfo");
    }

    private Object getCommercePermission(Hook hook, Object user) {
        return hook.getField(user, "commercePermission");
    }

    private Object getCommerceUserInfo(Hook hook, Object user) {
        return hook.getField(user, "commerceUserInfo");
    }

    private Integer getCommerceUserLevel(Hook hook, Object user) {
        return hook.getIntegerField(user, "commerceUserLevel");
    }

    private Integer getConstellation(Hook hook, Object user) {
        return hook.getIntegerField(user, "constellation");
    }

    private List<?> getCoverUrls(Hook hook, Object user) {//List<UrlModel>
        return (List<?>) hook.getField(user, "coverUrls");
    }

    private long getCreateTime(Hook hook, Object user) {
        return hook.getLongField(user, "createTime");
    }

    private String getCustomVerify(Hook hook, Object user) {
        return (String) hook.getField(user, "customVerify");
    }

    private Boolean getDisplayWvalantineActivityEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "displayWvalantineActivityEntry");
    }

    private String getDistrict(Hook hook, Object user) {
        return (String) hook.getField(user, "district");
    }

    private Integer getDongtaiCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "dongtai_count");
    }

    private Integer getDouPlusShareLocation(Hook hook, Object user) {
        return hook.getIntegerField(user, "douPlusShareLocation");
    }

    private Integer getDownloadSetting(Hook hook, Object user) {
        return hook.getIntegerField(user, "downloadSetting");
    }

    private Integer getDuetSetting(Hook hook, Object user) {
        return hook.getIntegerField(user, "duetSetting");
    }

    private Integer getEducation(Hook hook, Object user) {
        return hook.getIntegerField(user, "education");
    }

    private Object getEffectArtistDetail(Hook hook, Object user) {
        return hook.getField(user, "effectArtistDetail");
    }

    private String getEmail(Hook hook, Object user) {
        return (String) hook.getField(user, "email");
    }

    private String getEnrollYear(Hook hook, Object user) {
        return (String) hook.getField(user, "enrollYear");
    }

    private String getEnterpriseVerifyReason(Hook hook, Object user) {
        return (String) hook.getField(user, "enterpriseVerifyReason");
    }

    private Integer getFansCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "fansCount");
    }

    private Integer getFavoritingCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "favoritingCount");
    }

    private Long getFbExpireTime(Hook hook, Object user) {
        return hook.getLongField(user, "fbExpireTime");
    }

    private Integer getFollowStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "followStatus");
    }

    private Integer getFollowerCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "followerCount");
    }

    private List<?> getFollowerDetailList(Hook hook, Object user) {//List<FollowerDetail>
        return (List<?>) hook.getField(user, "followerDetailList");
    }

    private Integer getFollowerStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "followerStatus");
    }

    private Integer getFollowingCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "followingCount");
    }

    private Boolean getForcePrivateAccount(Hook hook, Object user) {
        return hook.getBooleanField(user, "forcePrivateAccount");
    }

    private Integer getGender(Hook hook, Object user) {
        return hook.getIntegerField(user, "gender");
    }

    private String getGoogleAccount(Hook hook, Object user) {
        return (String) hook.getField(user, "googleAccount");
    }

    private Boolean isHasEmail(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasEmail");
    }

    private Boolean isHasFacebookToken(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasFacebookToken");
    }

    private Boolean isHasMedal(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasMedal");
    }

    private Boolean isHasOrders(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasOrders");
    }

    private Boolean getHasStory(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasStory");
    }

    private Boolean isHasTwitterToken(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasTwitterToken");
    }

    private Boolean isHasUnreadStory(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasUnreadStory");
    }

    private Boolean isHasYoutubeToken(Hook hook, Object user) {
        return hook.getBooleanField(user, "hasYoutubeToken");
    }

    private Boolean isHideCity(Hook hook, Object user) {
        return hook.getBooleanField(user, "hideCity");
    }

    private Integer getHideFollowingFollowerList(Hook hook, Object user) {
        return hook.getIntegerField(user, "hideFollowingFollowerList");
    }

    private Boolean isHideSearch(Hook hook, Object user) {
        return hook.getBooleanField(user, "hideSearch");
    }

    private List<?> getHomepageBottomToast(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "homepageBottomToast");
    }

    private Object getHonorStruct(Hook hook, Object user) {
        return hook.getField(user, "honorStruct");
    }

    private String getInsId(Hook hook, Object user) {
        return (String) hook.getField(user, "insId");
    }

    private Boolean isActivityUser(Hook hook, Object user) {
        return hook.getBooleanField(user, "isActivityUser");
    }

    private Boolean isAdFake(Hook hook, Object user) {
        return hook.getBooleanField(user, "isAdFake");
    }

    private Boolean isBindedWeibo(Hook hook, Object user) {
        return hook.getBooleanField(user, "isBindedWeibo");
    }

    private Boolean isBlock(Hook hook, Object user) {
        return hook.getBooleanField(user, "isBlock");
    }

    private Boolean isBlocked(Hook hook, Object user) {
        return hook.getBooleanField(user, "isBlocked");
    }

    private Boolean isContentLanguageDialogShown(Hook hook, Object user) {
        return hook.getBooleanField(user, "isContentLanguageDialogShown");
    }

    private Boolean isCreater(Hook hook, Object user) {
        return hook.getBooleanField(user, "isCreater");
    }

    private Boolean isDisciplineMember(Hook hook, Object user) {
        return hook.getBooleanField(user, "isDisciplineMember");
    }

    private Boolean isEffectArtist(Hook hook, Object user) {
        return hook.getBooleanField(user, "isEffectArtist");
    }

    private Boolean isEmailVerified(Hook hook, Object user) {
        return hook.getBooleanField(user, "isEmailVerified");
    }

    private Boolean isFlowcardMember(Hook hook, Object user) {
        return hook.getBooleanField(user, "isFlowcardMember");
    }

    private Boolean isMinor(Hook hook, Object user) {
        return hook.getBooleanField(user, "isMinor");
    }

    private Boolean isNewRecommend(Hook hook, Object user) {
        return hook.getBooleanField(user, "isNewRecommend");
    }

    private Boolean isOldDouplusUser(Hook hook, Object user) {
        return hook.getBooleanField(user, "isOldDouplusUser");
    }

    private Boolean isPhoneBinded(Hook hook, Object user) {
        return hook.getBooleanField(user, "isPhoneBinded");
    }

    private Boolean isProAccount(Hook hook, Object user) {
        return hook.getBooleanField(user, "isProAccount");
    }

    private Boolean isStar(Hook hook, Object user) {
        return hook.getBooleanField(user, "isStar");
    }

    private Integer getIsSyncToutiao(Hook hook, Object user) {
        return hook.getIntegerField(user, "isSyncToutiao");
    }

    private Boolean isVerified(Hook hook, Object user) {
        return hook.getBooleanField(user, "isVerified");
    }

    private String getIsoCountryCode(Hook hook, Object user) {
        return (String) hook.getField(user, "isoCountryCode");
    }

    private String getLanguage(Hook hook, Object user) {
        return (String) hook.getField(user, "language");
    }

    private Long getLatestOrderTime(Hook hook, Object user) {
        return hook.getLongField(user, "latestOrderTime");
    }

    private List<?> getLatestStoryCover(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "latestStoryCover");
    }

    private Integer getLiveAgreement(Hook hook, Object user) {
        return hook.getIntegerField(user, "liveAgreement");
    }

    private Boolean isLiveCommerce(Hook hook, Object user) {
        return hook.getBooleanField(user, "liveCommerce");
    }

    private Integer getLoginPlatform(Hook hook, Object user) {
        return hook.getIntegerField(user, "loginPlatform");
    }

    private Integer getMAtType(Hook hook, Object user) {
        return hook.getIntegerField(user, "mAtType");
    }

    private Object getMGeneralPermission(Hook hook, Object user) {
        return hook.getField(user, "mGeneralPermission");
    }

    private Object getMHotListStruct(Hook hook, Object user) {
        return hook.getField(user, "mHotListStruct");
    }

    private Boolean isMIsGovMediaVip(Hook hook, Object user) {
        return hook.getBooleanField(user, "mIsGovMediaVip");
    }

    private String getNameField(Hook hook, Object user) {
        return (String) hook.getField(user, "nameField");
    }

    private Boolean isNeedAddrCard(Hook hook, Object user) {
        return hook.getBooleanField(user, "needAddrCard");
    }

    private List<?> getNeedPoints(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "needPoints");
    }

    private Boolean isNeedRecommend(Hook hook, Object user) {
        return hook.getBooleanField(user, "needRecommend");
    }

    private Integer getNeiguangShield(Hook hook, Object user) {
        return hook.getIntegerField(user, "neiguangShield");
    }

    private String getNickname(Hook hook, Object user) {
        return (String) hook.getField(user, "nickname");
    }

    private Boolean isNicknameUpdateReminder(Hook hook, Object user) {
        return hook.getBooleanField(user, "nicknameUpdateReminder");
    }

    private Integer getNotifyPrivateAccount(Hook hook, Object user) {
        return hook.getIntegerField(user, "notifyPrivateAccount");
    }

    private Object getOriginalMusician(Hook hook, Object user) {//g.class
        return hook.getField(user, "originalMusician");
    }

    private Object getPlatformInfos(Hook hook, Object user) {//PlatformInfo[].class
        return hook.getField(user, "platformInfos");
    }

    private Boolean isPostDefaultDownloadSetting(Hook hook, Object user) {
        return hook.getBooleanField(user, "postDefaultDownloadSetting");
    }

    private Boolean isPreventDownload(Hook hook, Object user) {
        return hook.getBooleanField(user, "preventDownload");
    }

    private Integer getPrivateAwemeCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "privateAwemeCount");
    }

    private Float getProfileCompletion(Hook hook, Object user) {
        return hook.getFloatField(user, "profileCompletion");
    }

    private Long getProfilePv(Hook hook, Object user) {
        return hook.getLongField(user, "profilePv");
    }

    private String getRecommendReason(Hook hook, Object user) {
        return (String) hook.getField(user, "recommendReason");
    }

    private String getRecommendReasonRelation(Hook hook, Object user) {
        return (String) hook.getField(user, "recommendReasonRelation");
    }

    private Double getRecommendScore(Hook hook, Object user) {
        return hook.getDoubleField(user, "recommendScore");
    }

    private Integer getRegisterStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "registerStatus");
    }

    private Long getRegisterTime(Hook hook, Object user) {
        return hook.getLongField(user, "registerTime");
    }

    private String getRelationLabel(Hook hook, Object user) {
        return (String) hook.getField(user, "relationLabel");
    }

    private List<?> getRelativeUserInfos(Hook hook, Object user) {
        return (List<?>) hook.getField(user, "relativeUserInfos");
    }

    private String getRemarkName(Hook hook, Object user) {
        return (String) hook.getField(user, "remarkName");
    }

    private String getRequestId(Hook hook, Object user) {
        return (String) hook.getField(user, "requestId");
    }

    private Object getRoomCover(Hook hook, Object user) {//UrlModel.class
        return hook.getField(user, "roomCover");
    }

    private String getRoomData(Hook hook, Object user) {
        return (String) hook.getField(user, "roomData");
    }

    private Long getRoomId(Hook hook, Object user) {
        return hook.getLongField(user, "roomId");
    }

    private String getRoomTypeTag(Hook hook, Object user) {
        return (String) hook.getField(user, "roomTypeTag");
    }

    private Integer getSchoolInfoShowRange(Hook hook, Object user) {
        return hook.getIntegerField(user, "schoolInfoShowRange");
    }

    private String getSchoolName(Hook hook, Object user) {
        return (String) hook.getField(user, "schoolName");
    }

    private String getSchoolPoiId(Hook hook, Object user) {
        return (String) hook.getField(user, "schoolPoiId");
    }

    private Integer getSchoolType(Hook hook, Object user) {
        return hook.getIntegerField(user, "schoolType");
    }

    private String getSecUid(Hook hook, Object user) {
        return (String) hook.getField(user, "secUid");
    }

    private Boolean isSecret(Hook hook, Object user) {
        return hook.getBooleanField(user, "secret");
    }

    private Object getShareInfo(Hook hook, Object user) {//ShareInfo.class
        return hook.getField(user, "shareInfo");
    }

    private Integer getShieldCommentNotice(Hook hook, Object user) {
        return hook.getIntegerField(user, "shieldCommentNotice");
    }

    private Integer getShieldDiggNotice(Hook hook, Object user) {
        return hook.getIntegerField(user, "shieldDiggNotice");
    }

    private Integer getShieldFollowNotice(Hook hook, Object user) {
        return hook.getIntegerField(user, "shieldFollowNotice");
    }

    private String getShopMicroApp(Hook hook, Object user) {
        return (String) hook.getField(user, "shopMicroApp");
    }

    private String getShortId(Hook hook, Object user) {
        return (String) hook.getField(user, "shortId");
    }

    private Integer getShowArtistPlaylist(Hook hook, Object user) {
        return hook.getIntegerField(user, "showArtistPlaylist");
    }

    private Integer getShowGenderStrategy(Hook hook, Object user) {
        return hook.getIntegerField(user, "showGenderStrategy");
    }

    private Boolean isShowFavoriteList(Hook hook, Object user) {
        return hook.getBooleanField(user, "showFavoriteList");
    }

    private Boolean isShowImageBubble(Hook hook, Object user) {
        return hook.getBooleanField(user, "showImageBubble");
    }

    private String getSignature(Hook hook, Object user) {
        return (String) hook.getField(user, "signature");
    }

    private String getSignatureLanguage(Hook hook, Object user) {
        return (String) hook.getField(user, "signatureLanguage");
    }

    private Object getSprintSupportUserInfo(Hook hook, Object user) {
        return hook.getField(user, "sprintSupportUserInfo");
    }

    private Integer getStarBillboardRank(Hook hook, Object user) {
        return hook.getIntegerField(user, "starBillboardRank");
    }

    private Boolean isStarUseNewDownload(Hook hook, Object user) {
        return hook.getBooleanField(user, "starUseNewDownload");
    }

    private Object getStoryBlockInfo(Hook hook, Object user) {
        return hook.getField(user, "storyBlockInfo");
    }

    private Integer getStoryCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "storyCount");
    }

    private Boolean isStoryOpen(Hook hook, Object user) {
        return hook.getBooleanField(user, "storyOpen");
    }

    private Object getTabSetting(Hook hook, Object user) {
        return hook.getField(user, "tabSetting");
    }

    private Integer getTabType(Hook hook, Object user) {
        return hook.getIntegerField(user, "tabType");
    }

    private String getThirdName(Hook hook, Object user) {
        return (String) hook.getField(user, "thirdName");
    }

    private Integer getTotalFavorited(Hook hook, Object user) {
        return hook.getIntegerField(user, "totalFavorited");
    }

    private Long getTwExpireTime(Hook hook, Object user) {
        return hook.getLongField(user, "twExpireTime");
    }

    private String getTwitterId(Hook hook, Object user) {
        return (String) hook.getField(user, "twitterId");
    }

    private String getTwitterName(Hook hook, Object user) {
        return (String) hook.getField(user, "twitterName");
    }

    private long[] getTypeLabels(Hook hook, Object user) {
        return (long[]) hook.getField(user, "typeLabels");
    }

    private String getUid(Hook hook, Object user) {
        return (String) hook.getField(user, "uid");
    }

    private String getUniqueId(Hook hook, Object user) {
        return (String) hook.getField(user, "uniqueId");
    }

    private Long getUniqueIdModifyTime(Hook hook, Object user) {
        return hook.getLongField(user, "unique_id_modify_time");
    }

    private Boolean isUserCancelled(Hook hook, Object user) {
        return hook.getBooleanField(user, "userCancelled");
    }

    private Object getUserHonor(Hook hook, Object user) {//UserHonor.class
        return hook.getField(user, "userHonor");
    }

    private Integer getVerificationType(Hook hook, Object user) {
        return hook.getIntegerField(user, "verificationType");
    }

    private String getVerifyInfo(Hook hook, Object user) {
        return (String) hook.getField(user, "verifyInfo");
    }

    private Integer getVerifyStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "verifyStatus");
    }

    private Integer getWatchStatus(Hook hook, Object user) {
        return hook.getIntegerField(user, "watchStatus");
    }

    private String getWeiboNickname(Hook hook, Object user) {
        return (String) hook.getField(user, "weiboNickname");
    }

    private String getWeiboSchema(Hook hook, Object user) {
        return (String) hook.getField(user, "weiboSchema");
    }

    private String getWeiboUrl(Hook hook, Object user) {
        return (String) hook.getField(user, "weiboUrl");
    }

    private String getWeiboVerify(Hook hook, Object user) {
        return (String) hook.getField(user, "weiboVerify");
    }

    private Boolean isWithCommerceEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withCommerceEntry");
    }

    private Boolean isWithDouEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withDouEntry");
    }

    private Boolean isWithDouplusEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withDouplusEntry");
    }

    private Boolean isWithFusionShopEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withFusionShopEntry");
    }

    private Boolean isWithNewGoods(Hook hook, Object user) {
        return hook.getBooleanField(user, "withNewGoods");
    }

    private String getYoutubeChannelId(Hook hook, Object user) {
        return (String) hook.getField(user, "youtubeChannelId");
    }

    private String getYoutubeChannelTitle(Hook hook, Object user) {
        return (String) hook.getField(user, "youtubeChannelTitle");
    }

    private Long getYoutubeExpireTime(Hook hook, Object user) {
        return hook.getLongField(user, "youtubeExpireTime");
    }

    private boolean isAvatarUpdateReminder(Hook hook, Object user) {
        return hook.getBooleanField(user, "avatarUpdateReminder");
    }

    private Object getUrgeDetail(Hook hook, Object user) {
        return hook.getField(user, "urgeDetail");
    }

    private int getUserMode(Hook hook, Object user) {
        return hook.getIntegerField(user, "userMode");
    }

    private int getUserPeriod(Hook hook, Object user) {
        return hook.getIntegerField(user, "userPeriod");
    }

    private int getUserRate(Hook hook, Object user) {
        return hook.getIntegerField(user, "userRate");
    }

    private Object getUserRateRemindInfo(Hook hook, Object user) {
        return hook.getField(user, "userRateRemindInfo");
    }

    private int getUserStoryCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "userStoryCount");
    }

    private String getVcdSchemaUrl(Hook hook, Object user) {
        return (String) hook.getField(user, "vcdSchemaUrl");
    }

    private int getVerificationBadgeType(Hook hook, Object user) {
        return hook.getIntegerField(user, "verificationBadgeType");
    }

    private Object getVideoCover(Hook hook, Object user) {
        return hook.getField(user, "videoCover");
    }

    private boolean isWithCommerceEnterpriseTabEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withCommerceEnterpriseTabEntry");
    }

    private boolean isWithCommerceNewbieTask(Hook hook, Object user) {
        return hook.getBooleanField(user, "withCommerceNewbieTask");
    }

    private boolean isWithItemCommerceEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withItemCommerceEntry");
    }

    private boolean isWithLubanEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withLubanEntry");
    }

    private boolean isWithStarAtlasEntry(Hook hook, Object user) {
        return hook.getBooleanField(user, "withStarAtlasEntry");
    }

    private int getWxTag(Hook hook, Object user) {
        return hook.getIntegerField(user, "wxTag");
    }

    private int getXmasUnlockCount(Hook hook, Object user) {
        return hook.getIntegerField(user, "xmasUnlockCount");
    }

    private long getYouTubeLastRefreshTime(Hook hook, Object user) {
        return hook.getLongField(user, "youTubeLastRefreshTime");
    }

    private String getYouTubeRefreshToken(Hook hook, Object user) {
        return (String) hook.getField(user, "youTubeRefreshToken");
    }
}