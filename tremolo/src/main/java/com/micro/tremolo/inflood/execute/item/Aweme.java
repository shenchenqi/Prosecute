package com.micro.tremolo.inflood.execute.item;

import com.micro.hook.config.Hook;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/24 18:10
 * @Source 抖音版本960 com.ss.android.ugc.aweme.feed.model.Aweme
 */
public class Aweme {

    private String activityId;
    private Object activityPendant;//b.class
    private int adAwemeSource;
    private boolean adDescHandle = true;
    private int adDescMaxLines = 4;
    private int adLinkType;
    private String adOrderId;
    private String adSchedule;
    private String aid;
    private Object anchor;//Anchor.class
    private Object anchorInfo;//AnchorInfo.class
    private Object author;//User.class
    private Object awemeACLShareInfo;//AwemeACLShare.class
    //private AwemeControl awemeControl = new AwemeControl();
    private Object awemeNationalTask;//AwemeNationalTask.class
    private int awemePosition = -1;
    private Object awemeRawAd;//AwemeRawAd.class
    private Object awemeRiskModel;//AwemeRiskModel.class
    private int awemeType;
    private Object bannerTip;//BannerTip.class
    //private transient j cacheAvUploadMiscInfoStruct;
    private boolean canPlay = true;
    private List<Object> challengeList;//Challenge.class
    private boolean cmtSwt;
    private int collectStatus;
    private int commentSetting;
    private List<Object> commerceConfigDataList;//CommerceConfigData.class
    private Object commerceStickerInfo;//n.class
    private long createTime;
    private long date;
    private Object dcdVideoExtra;//DCDVideoExtra.class
    private String desc;
    private String descLanguage;
    private Object descendantsModel;//DescendantsModel.class
    private String distance;
    private int distributeType;
    private boolean downloadWithoutWatermark;
    private int duetSetting;
    //private transient WeakReference<CharSequence> ellipsizeDesc;
    //private transient CharSequence ellipsizeTransDesc;
    private boolean enableTopView = true;
    private int externalType;
    private String extra;
    private List<Object> familiarRecommendUser;//User.class
    private long feedCount;
    private Object feedRelationLabel;//RelationLabelNew.class
    private Object floatingCardInfo;//FloatingCardInfo.class
    private String forwardCommentId;
    private Object forwardItem;//Aweme.class
    private String forwardItemId;
    private Object fromRawChallenge;//Challenge.class
    private Object gameInfo;//GameInfo.class
    private boolean hasVisionSearchEntry;
    private Object hotListStruct;//HotListStruct.class
    private Object hotSearchInfo;//HotSearchInfo.class
    private String hotSpot;
    private List<Object> imageInfos;//ImageInfo.class
    private List<Object> interactStickerStructs;//InteractStickerStruct.class
    private boolean isAd;
    private boolean isCanCache = true;
    private boolean isEffectDesigner;
    private boolean isFakeResponse;
    private boolean isFamiliar;
    private boolean isFirstInSpot;
    private boolean isFromDouPlusGuideAnimate;
    private int isHashTag;
    private boolean isLastInSpot;
    private boolean isPgcShow;
    private boolean isPreloadScroll;
    private int isPreview;
    private boolean isProhibited;
    private boolean isRelieve;
    private int isTop;
    private boolean isVr;
    private Object labelLarge;//UrlModel.class
    private Object labelPrivate;//UrlModel.class
    private Object labelThumb;//UrlModel.class
    private Object labelTop;//UrlModel.class
    private String landingPage;
    private boolean lawCriticalCountry;
    private Object linkAdData;//r.class
    private List<Object> longVideos;//LongVideo.class
    private Object mCommerceVideoAuthInfo;//com.ss.android.ugc.aweme.commerce.b.class
    private int mConcatAndUploadState;
    private String mLabelMusicStarterText;
    private String mLabelOriginAuthorText;
    private Object mLiveAwesomeSplashInfo;//LiveAwesomeSplashInfo.class
    private HashMap<String, String> mMobParams;
    private List<Object> mNewRelationLabel;//RelationLabelNew.class
    private Object mRoomFeedCellStruct;//RoomFeedCellStruct.class
    private Object microAppInfo;//MicroAppInfo.class
    private Object mixInfo;//MixStruct.class
    private Object music;//Music.class
    private Object musicStarter;//UrlModel.class
    private Object nearbyCardStruct;//NearbyCardStruct.class
    private boolean needVisionSearchEntry;
    private String newSourceId;
    private String newSourceType;
    private List<Object> nicknamePosition;//Position.class
    private String openPlatformName;
    private Object openPlatformStruct;//e.class
    private Object originAuthor;//UrlModel.class
    private List<String> originCommentIds;
    private int originalPos;
    private Object poiOpCardStruct;//ak.class
    private Object poiRankCardStruct;//at.class
    private Object poiStruct;//PoiStruct.class
    private List<Object> position;//Position.class
    private String preForwardId;
    private Object preload;//Preload.class
    private boolean preventDownload;
    private Object promotionOtherInfo;//d.class
    private int rate = -1;
    private String rateScore;
    private String reactFrom;
    private String reactOrigin;
    private int reactSetting;
    private String reactView;
    private String region;
    private Object relationLabel;//RelationDynamicLabel.class
    private String repostFromGroupId;
    private String repostFromUserId;
    private String requestId;
    private Object room;//RoomStruct.class
    private int scenario;
    private Object shareInfo;//ShareInfo.class
    private String shareUrl;
    private Object simplePoiInfoStruct;//SimplePoiInfoStruct.class
    private List<Object> simplePromotions;//com.ss.android.ugc.aweme.commerce.model.e.class
    private String simplePromotionsString = "";
    private String simpleShopSeeding;
    private Object specialSticker;//SpecialSticker.class
    private Object starAtlasInfo;//AwemeStarAtlas.class
    private long starAtlasOrderId;
    private String starRecommendTag;
    private Object statistics;//AwemeStatistics.class
    private Object status;//AwemeStatus.class
    private Object stickerEntranceInfo;//com.ss.android.ugc.aweme.sticker.model.e.class
    private String stickerIDs;
    private Object streamUrlModel;//StreamUrlModel.class
    private String takeDownDesc;
    private int takeDownReason;
    private List<Object> textExtra;//TextExtraStruct.class
    private List<Object> textTopLabels;//AwemeTextLabelModel.class
    private List<Object> textVideoLabels;//AwemeTextLabelModel.class
    private String title;
    //private transient CharSequence transDesc;
    //private transient int transDescLines;
    private List<Object> uniqidPosition;//Position.class
    private String uploadMiscInfoStructStr;
    private int userDigg;
    private Object video;//Video.class
    private Object videoControl;//VideoControl.class
    private List<Object> videoLabels;//AwemeLabelModel.class
    private boolean withPromotionalMusic;
    private Object xiGuaTask;//XiGuaTaskStruct.class

    public Aweme(Hook hook, Object aweme) {
        this.activityId = getActivityId(hook, aweme);
        this.activityPendant = getActivityPendant(hook, aweme);
        this.adAwemeSource = getAdAwemeSource(hook, aweme);
        this.adDescHandle = isAdDescHandle(hook, aweme);
        this.adDescMaxLines = getAdDescMaxLines(hook, aweme);
        this.adLinkType = getAdLinkType(hook, aweme);
        this.adOrderId = getAdOrderId(hook, aweme);
        this.adSchedule = getAdSchedule(hook, aweme);
        this.aid = getAid(hook, aweme);
        this.anchor = getAnchor(hook, aweme);
        this.anchorInfo = getAnchorInfo(hook, aweme);
        this.author = getAuthor(hook, aweme);
        this.awemeACLShareInfo = getAwemeACLShareInfo(hook, aweme);
        this.awemeNationalTask = getAwemeNationalTask(hook, aweme);
        this.awemePosition = getAwemePosition(hook, aweme);
        this.awemeRawAd = getAwemeRawAd(hook, aweme);
        this.awemeRiskModel = getAwemeRiskModel(hook, aweme);
        this.awemeType = getAwemeType(hook, aweme);
        this.bannerTip = getBannerTip(hook, aweme);
        this.canPlay = isCanPlay(hook, aweme);
        this.challengeList = (List<Object>) getChallengeList(hook, aweme);
        this.cmtSwt = isCmtSwt(hook, aweme);
        this.collectStatus = getCollectStatus(hook, aweme);
        this.commentSetting = getCommentSetting(hook, aweme);
        this.commerceConfigDataList = (List<Object>) getCommerceConfigDataList(hook, aweme);
        this.commerceStickerInfo = getCommerceStickerInfo(hook, aweme);
        this.createTime = getCreateTime(hook, aweme);
        this.date = getDate(hook, aweme);
        this.dcdVideoExtra = getDcdVideoExtra(hook, aweme);
        this.desc = getDesc(hook, aweme);
        this.descLanguage = getDescLanguage(hook, aweme);
        this.descendantsModel = getDescendantsModel(hook, aweme);
        this.distance = getDistance(hook, aweme);
        this.distributeType = getDistributeType(hook, aweme);
        this.downloadWithoutWatermark = isDownloadWithoutWatermark(hook, aweme);
        this.duetSetting = getDuetSetting(hook, aweme);
        this.enableTopView = isEnableTopView(hook, aweme);
        this.externalType = getExternalType(hook, aweme);
        this.extra = getExtra(hook, aweme);
        this.familiarRecommendUser = (List<Object>) getFamiliarRecommendUser(hook, aweme);
        this.feedCount = getFeedCount(hook, aweme);
        this.feedRelationLabel = getFeedRelationLabel(hook, aweme);
        this.floatingCardInfo = getFloatingCardInfo(hook, aweme);
        this.forwardCommentId = getForwardCommentId(hook, aweme);
        this.forwardItem = getForwardItem(hook, aweme);
        this.forwardItemId = getForwardItemId(hook, aweme);
        this.fromRawChallenge = getFromRawChallenge(hook, aweme);
        this.gameInfo = getGameInfo(hook, aweme);
        this.hasVisionSearchEntry = isHasVisionSearchEntry(hook, aweme);
        this.hotListStruct = getHotListStruct(hook, aweme);
        this.hotSearchInfo = getHotSearchInfo(hook, aweme);
        this.hotSpot = getHotSpot(hook, aweme);
        this.imageInfos = (List<Object>) getImageInfos(hook, aweme);
        this.interactStickerStructs = (List<Object>) getInteractStickerStructs(hook, aweme);
        this.isAd = isAd(hook, aweme);
        this.isCanCache = isCanCache(hook, aweme);
        this.isEffectDesigner = isEffectDesigner(hook, aweme);
        this.isFakeResponse = isFakeResponse(hook, aweme);
        this.isFamiliar = isFamiliar(hook, aweme);
        this.isFirstInSpot = isFirstInSpot(hook, aweme);
        this.isFromDouPlusGuideAnimate = isFromDouPlusGuideAnimate(hook, aweme);
        this.isHashTag = getIsHashTag(hook, aweme);
        this.isLastInSpot = isLastInSpot(hook, aweme);
        this.isPgcShow = isPgcShow(hook, aweme);
        this.isPreloadScroll = isPreloadScroll(hook, aweme);
        this.isPreview = getIsPreview(hook, aweme);
        this.isProhibited = isProhibited(hook, aweme);
        this.isRelieve = isRelieve(hook, aweme);
        this.isTop = getIsTop(hook, aweme);
        this.isVr = isVr(hook, aweme);
        this.labelLarge = getLabelLarge(hook, aweme);
        this.labelPrivate = getLabelPrivate(hook, aweme);
        this.labelThumb = getLabelThumb(hook, aweme);
        this.labelTop = getLabelTop(hook, aweme);
        this.landingPage = getLandingPage(hook, aweme);
        this.lawCriticalCountry = isLawCriticalCountry(hook, aweme);
        this.linkAdData = getLinkAdData(hook, aweme);
        this.longVideos = (List<Object>) getLongVideos(hook, aweme);
        this.mCommerceVideoAuthInfo = getmCommerceVideoAuthInfo(hook, aweme);
        this.mConcatAndUploadState = getmConcatAndUploadState(hook, aweme);
        this.mLabelMusicStarterText = getmLabelMusicStarterText(hook, aweme);
        this.mLabelOriginAuthorText = getmLabelOriginAuthorText(hook, aweme);
        this.mLiveAwesomeSplashInfo = getmLiveAwesomeSplashInfo(hook, aweme);
        this.mMobParams = getmMobParams(hook, aweme);
        this.mNewRelationLabel = (List<Object>) getmNewRelationLabel(hook, aweme);
        this.mRoomFeedCellStruct = getmRoomFeedCellStruct(hook, aweme);
        this.microAppInfo = getMicroAppInfo(hook, aweme);
        this.mixInfo = getMixInfo(hook, aweme);
        this.music = getMusic(hook, aweme);
        this.musicStarter = getMusicStarter(hook, aweme);
        this.nearbyCardStruct = getNearbyCardStruct(hook, aweme);
        this.needVisionSearchEntry = isNeedVisionSearchEntry(hook, aweme);
        this.newSourceId = getNewSourceId(hook, aweme);
        this.newSourceType = getNewSourceType(hook, aweme);
        this.nicknamePosition = (List<Object>) getNicknamePosition(hook, aweme);
        this.openPlatformName = getOpenPlatformName(hook, aweme);
        this.openPlatformStruct = getOpenPlatformStruct(hook, aweme);
        this.originAuthor = getOriginAuthor(hook, aweme);
        this.originCommentIds = getOriginCommentIds(hook, aweme);
        this.originalPos = getOriginalPos(hook, aweme);
        this.poiOpCardStruct = getPoiOpCardStruct(hook, aweme);
        this.poiRankCardStruct = getPoiRankCardStruct(hook, aweme);
        this.poiStruct = getPoiStruct(hook, aweme);
        this.position = (List<Object>) getPosition(hook, aweme);
        this.preForwardId = getPreForwardId(hook, aweme);
        this.preload = getPreload(hook, aweme);
        this.preventDownload = isPreventDownload(hook, aweme);
        this.promotionOtherInfo = getPromotionOtherInfo(hook, aweme);
        this.rate = getRate(hook, aweme);
        this.rateScore = getRateScore(hook, aweme);
        this.reactFrom = getReactFrom(hook, aweme);
        this.reactOrigin = getReactOrigin(hook, aweme);
        this.reactSetting = getReactSetting(hook, aweme);
        this.reactView = getReactView(hook, aweme);
        this.region = getRegion(hook, aweme);
        this.relationLabel = getRelationLabel(hook, aweme);
        this.repostFromGroupId = getRepostFromGroupId(hook, aweme);
        this.repostFromUserId = getRepostFromUserId(hook, aweme);
        this.requestId = getRequestId(hook, aweme);
        this.room = getRoom(hook, aweme);
        this.scenario = getScenario(hook, aweme);
        this.shareInfo = getShareInfo(hook, aweme);
        this.shareUrl = getShareUrl(hook, aweme);
        this.simplePoiInfoStruct = getSimplePoiInfoStruct(hook, aweme);
        this.simplePromotions = (List<Object>) getSimplePromotions(hook, aweme);
        this.simplePromotionsString = getSimplePromotionsString(hook, aweme);
        this.simpleShopSeeding = getSimpleShopSeeding(hook, aweme);
        this.specialSticker = getSpecialSticker(hook, aweme);
        this.starAtlasInfo = getStarAtlasInfo(hook, aweme);
        this.starAtlasOrderId = getStarAtlasOrderId(hook, aweme);
        this.starRecommendTag = getStarRecommendTag(hook, aweme);
        this.statistics = getStatistics(hook, aweme);
        this.status = getStatus(hook, aweme);
        this.stickerEntranceInfo = getStickerEntranceInfo(hook, aweme);
        this.stickerIDs = getStickerIDs(hook, aweme);
        this.streamUrlModel = getStreamUrlModel(hook, aweme);
        this.takeDownDesc = getTakeDownDesc(hook, aweme);
        this.takeDownReason = getTakeDownReason(hook, aweme);
        this.textExtra = (List<Object>) getTextExtra(hook, aweme);
        this.textTopLabels = (List<Object>) getTextTopLabels(hook, aweme);
        this.textVideoLabels = (List<Object>) getTextVideoLabels(hook, aweme);
        this.title = getTitle(hook, aweme);
        this.uniqidPosition = (List<Object>) getUniqidPosition(hook, aweme);
        this.uploadMiscInfoStructStr = getUploadMiscInfoStructStr(hook, aweme);
        this.userDigg = getUserDigg(hook, aweme);
        this.video = getVideo(hook, aweme);
        this.videoControl = getVideoControl(hook, aweme);
        this.videoLabels = (List<Object>) getVideoLabels(hook, aweme);
        this.withPromotionalMusic = isWithPromotionalMusic(hook, aweme);
        this.xiGuaTask = getXiGuaTask(hook, aweme);
    }

    public String getActivityId() {
        return activityId;
    }

    public Object getActivityPendant() {
        return activityPendant;
    }

    public int getAdAwemeSource() {
        return adAwemeSource;
    }

    public boolean isAdDescHandle() {
        return adDescHandle;
    }

    public int getAdDescMaxLines() {
        return adDescMaxLines;
    }

    public int getAdLinkType() {
        return adLinkType;
    }

    public String getAdOrderId() {
        return adOrderId;
    }

    public String getAdSchedule() {
        return adSchedule;
    }

    public String getAid() {
        return aid;
    }

    public Object getAnchor() {
        return anchor;
    }

    public Object getAnchorInfo() {
        return anchorInfo;
    }

    public Object getAuthor() {
        return author;
    }

    public Object getAwemeACLShareInfo() {
        return awemeACLShareInfo;
    }

    public Object getAwemeNationalTask() {
        return awemeNationalTask;
    }

    public int getAwemePosition() {
        return awemePosition;
    }

    public Object getAwemeRawAd() {
        return awemeRawAd;
    }

    public Object getAwemeRiskModel() {
        return awemeRiskModel;
    }

    public int getAwemeType() {
        return awemeType;
    }

    public Object getBannerTip() {
        return bannerTip;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public List<Object> getChallengeList() {
        return challengeList;
    }

    public boolean isCmtSwt() {
        return cmtSwt;
    }

    public int getCollectStatus() {
        return collectStatus;
    }

    public int getCommentSetting() {
        return commentSetting;
    }

    public List<Object> getCommerceConfigDataList() {
        return commerceConfigDataList;
    }

    public Object getCommerceStickerInfo() {
        return commerceStickerInfo;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getDate() {
        return date;
    }

    public Object getDcdVideoExtra() {
        return dcdVideoExtra;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescLanguage() {
        return descLanguage;
    }

    public Object getDescendantsModel() {
        return descendantsModel;
    }

    public String getDistance() {
        return distance;
    }

    public int getDistributeType() {
        return distributeType;
    }

    public boolean isDownloadWithoutWatermark() {
        return downloadWithoutWatermark;
    }

    public int getDuetSetting() {
        return duetSetting;
    }

    public boolean isEnableTopView() {
        return enableTopView;
    }

    public int getExternalType() {
        return externalType;
    }

    public String getExtra() {
        return extra;
    }

    public List<Object> getFamiliarRecommendUser() {
        return familiarRecommendUser;
    }

    public long getFeedCount() {
        return feedCount;
    }

    public Object getFeedRelationLabel() {
        return feedRelationLabel;
    }

    public Object getFloatingCardInfo() {
        return floatingCardInfo;
    }

    public String getForwardCommentId() {
        return forwardCommentId;
    }

    public Object getForwardItem() {
        return forwardItem;
    }

    public String getForwardItemId() {
        return forwardItemId;
    }

    public Object getFromRawChallenge() {
        return fromRawChallenge;
    }

    public Object getGameInfo() {
        return gameInfo;
    }

    public boolean isHasVisionSearchEntry() {
        return hasVisionSearchEntry;
    }

    public Object getHotListStruct() {
        return hotListStruct;
    }

    public Object getHotSearchInfo() {
        return hotSearchInfo;
    }

    public String getHotSpot() {
        return hotSpot;
    }

    public List<Object> getImageInfos() {
        return imageInfos;
    }

    public List<Object> getInteractStickerStructs() {
        return interactStickerStructs;
    }

    public boolean isAd() {
        return isAd;
    }

    public boolean isCanCache() {
        return isCanCache;
    }

    public boolean isEffectDesigner() {
        return isEffectDesigner;
    }

    public boolean isFakeResponse() {
        return isFakeResponse;
    }

    public boolean isFamiliar() {
        return isFamiliar;
    }

    public boolean isFirstInSpot() {
        return isFirstInSpot;
    }

    public boolean isFromDouPlusGuideAnimate() {
        return isFromDouPlusGuideAnimate;
    }

    public int getIsHashTag() {
        return isHashTag;
    }

    public boolean isLastInSpot() {
        return isLastInSpot;
    }

    public boolean isPgcShow() {
        return isPgcShow;
    }

    public boolean isPreloadScroll() {
        return isPreloadScroll;
    }

    public int getIsPreview() {
        return isPreview;
    }

    public boolean isProhibited() {
        return isProhibited;
    }

    public boolean isRelieve() {
        return isRelieve;
    }

    public int getIsTop() {
        return isTop;
    }

    public boolean isVr() {
        return isVr;
    }

    public Object getLabelLarge() {
        return labelLarge;
    }

    public Object getLabelPrivate() {
        return labelPrivate;
    }

    public Object getLabelThumb() {
        return labelThumb;
    }

    public Object getLabelTop() {
        return labelTop;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public boolean isLawCriticalCountry() {
        return lawCriticalCountry;
    }

    public Object getLinkAdData() {
        return linkAdData;
    }

    public List<Object> getLongVideos() {
        return longVideos;
    }

    public Object getmCommerceVideoAuthInfo() {
        return mCommerceVideoAuthInfo;
    }

    public int getmConcatAndUploadState() {
        return mConcatAndUploadState;
    }

    public String getmLabelMusicStarterText() {
        return mLabelMusicStarterText;
    }

    public String getmLabelOriginAuthorText() {
        return mLabelOriginAuthorText;
    }

    public Object getmLiveAwesomeSplashInfo() {
        return mLiveAwesomeSplashInfo;
    }

    public HashMap<String, String> getmMobParams() {
        return mMobParams;
    }

    public List<Object> getmNewRelationLabel() {
        return mNewRelationLabel;
    }

    public Object getmRoomFeedCellStruct() {
        return mRoomFeedCellStruct;
    }

    public Object getMicroAppInfo() {
        return microAppInfo;
    }

    public Object getMixInfo() {
        return mixInfo;
    }

    public Object getMusic() {
        return music;
    }

    public Object getMusicStarter() {
        return musicStarter;
    }

    public Object getNearbyCardStruct() {
        return nearbyCardStruct;
    }

    public boolean isNeedVisionSearchEntry() {
        return needVisionSearchEntry;
    }

    public String getNewSourceId() {
        return newSourceId;
    }

    public String getNewSourceType() {
        return newSourceType;
    }

    public List<Object> getNicknamePosition() {
        return nicknamePosition;
    }

    public String getOpenPlatformName() {
        return openPlatformName;
    }

    public Object getOpenPlatformStruct() {
        return openPlatformStruct;
    }

    public Object getOriginAuthor() {
        return originAuthor;
    }

    public List<String> getOriginCommentIds() {
        return originCommentIds;
    }

    public int getOriginalPos() {
        return originalPos;
    }

    public Object getPoiOpCardStruct() {
        return poiOpCardStruct;
    }

    public Object getPoiRankCardStruct() {
        return poiRankCardStruct;
    }

    public Object getPoiStruct() {
        return poiStruct;
    }

    public List<Object> getPosition() {
        return position;
    }

    public String getPreForwardId() {
        return preForwardId;
    }

    public Object getPreload() {
        return preload;
    }

    public boolean isPreventDownload() {
        return preventDownload;
    }

    public Object getPromotionOtherInfo() {
        return promotionOtherInfo;
    }

    public int getRate() {
        return rate;
    }

    public String getRateScore() {
        return rateScore;
    }

    public String getReactFrom() {
        return reactFrom;
    }

    public String getReactOrigin() {
        return reactOrigin;
    }

    public int getReactSetting() {
        return reactSetting;
    }

    public String getReactView() {
        return reactView;
    }

    public String getRegion() {
        return region;
    }

    public Object getRelationLabel() {
        return relationLabel;
    }

    public String getRepostFromGroupId() {
        return repostFromGroupId;
    }

    public String getRepostFromUserId() {
        return repostFromUserId;
    }

    public String getRequestId() {
        return requestId;
    }

    public Object getRoom() {
        return room;
    }

    public int getScenario() {
        return scenario;
    }

    public Object getShareInfo() {
        return shareInfo;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public Object getSimplePoiInfoStruct() {
        return simplePoiInfoStruct;
    }

    public List<Object> getSimplePromotions() {
        return simplePromotions;
    }

    public String getSimplePromotionsString() {
        return simplePromotionsString;
    }

    public String getSimpleShopSeeding() {
        return simpleShopSeeding;
    }

    public Object getSpecialSticker() {
        return specialSticker;
    }

    public Object getStarAtlasInfo() {
        return starAtlasInfo;
    }

    public long getStarAtlasOrderId() {
        return starAtlasOrderId;
    }

    public String getStarRecommendTag() {
        return starRecommendTag;
    }

    public Object getStatistics() {
        return statistics;
    }

    public Object getStatus() {
        return status;
    }

    public Object getStickerEntranceInfo() {
        return stickerEntranceInfo;
    }

    public String getStickerIDs() {
        return stickerIDs;
    }

    public Object getStreamUrlModel() {
        return streamUrlModel;
    }

    public String getTakeDownDesc() {
        return takeDownDesc;
    }

    public int getTakeDownReason() {
        return takeDownReason;
    }

    public List<Object> getTextExtra() {
        return textExtra;
    }

    public List<Object> getTextTopLabels() {
        return textTopLabels;
    }

    public List<Object> getTextVideoLabels() {
        return textVideoLabels;
    }

    public String getTitle() {
        return title;
    }

    public List<Object> getUniqidPosition() {
        return uniqidPosition;
    }

    public String getUploadMiscInfoStructStr() {
        return uploadMiscInfoStructStr;
    }

    public int getUserDigg() {
        return userDigg;
    }

    public Object getVideo() {
        return video;
    }

    public Object getVideoControl() {
        return videoControl;
    }

    public List<Object> getVideoLabels() {
        return videoLabels;
    }

    public boolean isWithPromotionalMusic() {
        return withPromotionalMusic;
    }

    public Object getXiGuaTask() {
        return xiGuaTask;
    }

    private String getActivityId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "activityId");
    }

    private Object getActivityPendant(Hook hook, Object aweme) {
        return hook.getField(aweme, "activityPendant");
    }

    private int getAdAwemeSource(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "adAwemeSource");
    }

    private boolean isAdDescHandle(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "adDescHandle");
    }

    private int getAdDescMaxLines(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "adDescMaxLines");
    }

    private int getAdLinkType(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "adLinkType");
    }

    private String getAdOrderId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "adOrderId");
    }

    private String getAdSchedule(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "adSchedule");
    }

    private String getAid(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "aid");
    }

    private Object getAnchor(Hook hook, Object aweme) {
        return hook.getField(aweme, "anchor");
    }

    private Object getAnchorInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "anchorInfo");
    }

    private Object getAuthor(Hook hook, Object aweme) {
        return hook.getField(aweme, "author");
    }

    private Object getAwemeACLShareInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "awemeACLShareInfo");
    }

    private Object getAwemeNationalTask(Hook hook, Object aweme) {
        return hook.getField(aweme, "awemeNationalTask");
    }

    private int getAwemePosition(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "awemePosition");
    }

    private Object getAwemeRawAd(Hook hook, Object aweme) {
        return hook.getField(aweme, "awemeRawAd");
    }

    private Object getAwemeRiskModel(Hook hook, Object aweme) {
        return hook.getField(aweme, "awemeRiskModel");
    }

    private int getAwemeType(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "awemeType");
    }

    private Object getBannerTip(Hook hook, Object aweme) {
        return hook.getField(aweme, "bannerTip");
    }

    private boolean isCanPlay(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "canPlay");
    }

    private List<?> getChallengeList(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "challengeList");
    }

    private boolean isCmtSwt(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "cmtSwt");
    }

    private int getCollectStatus(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "collectStatus");
    }

    private int getCommentSetting(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "commentSetting");
    }

    private List<?> getCommerceConfigDataList(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "commerceConfigDataList");
    }

    private Object getCommerceStickerInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "commerceStickerInfo");
    }

    private long getCreateTime(Hook hook, Object aweme) {
        return hook.getLongField(aweme, "createTime");
    }

    private long getDate(Hook hook, Object aweme) {
        return hook.getLongField(aweme, "date");
    }

    private Object getDcdVideoExtra(Hook hook, Object aweme) {
        return hook.getField(aweme, "dcdVideoExtra");
    }

    private String getDesc(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "desc");
    }

    private String getDescLanguage(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "descLanguage");
    }

    private Object getDescendantsModel(Hook hook, Object aweme) {
        return hook.getField(aweme, "descendantsModel");
    }

    private String getDistance(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "distance");
    }

    private int getDistributeType(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "distributeType");
    }

    private boolean isDownloadWithoutWatermark(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "downloadWithoutWatermark");
    }

    private int getDuetSetting(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "duetSetting");
    }

    private boolean isEnableTopView(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "enableTopView");
    }

    private int getExternalType(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "externalType");
    }

    private String getExtra(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "extra");
    }

    private List<?> getFamiliarRecommendUser(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "familiarRecommendUser");
    }

    private long getFeedCount(Hook hook, Object aweme) {
        return hook.getLongField(aweme, "feedCount");
    }

    private Object getFeedRelationLabel(Hook hook, Object aweme) {
        return hook.getField(aweme, "feedRelationLabel");
    }

    private Object getFloatingCardInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "floatingCardInfo");
    }

    private String getForwardCommentId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "forwardCommentId");
    }

    private Object getForwardItem(Hook hook, Object aweme) {
        return hook.getField(aweme, "forwardItem");
    }

    private String getForwardItemId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "forwardItemId");
    }

    private Object getFromRawChallenge(Hook hook, Object aweme) {
        return hook.getField(aweme, "fromRawChallenge");
    }

    private Object getGameInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "gameInfo");
    }

    private boolean isHasVisionSearchEntry(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "hasVisionSearchEntry");
    }

    private Object getHotListStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "hotListStruct");
    }

    private Object getHotSearchInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "hotSearchInfo");
    }

    private String getHotSpot(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "hotSpot");
    }

    private List<?> getImageInfos(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "imageInfos");
    }

    private List<?> getInteractStickerStructs(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "interactStickerStructs");
    }

    private boolean isAd(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isAd");
    }

    private boolean isCanCache(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isCanCache");
    }

    private boolean isEffectDesigner(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isEffectDesigner");
    }

    private boolean isFakeResponse(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isFakeResponse");
    }

    private boolean isFamiliar(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isFamiliar");
    }

    private boolean isFirstInSpot(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isFirstInSpot");
    }

    private boolean isFromDouPlusGuideAnimate(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isFromDouPlusGuideAnimate");
    }

    private int getIsHashTag(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "isHashTag");
    }

    private boolean isLastInSpot(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isLastInSpot");
    }

    private boolean isPgcShow(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isPgcShow");
    }

    private boolean isPreloadScroll(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isPreloadScroll");
    }

    private int getIsPreview(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "isPreview");
    }

    private boolean isProhibited(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isProhibited");
    }

    private boolean isRelieve(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isRelieve");
    }

    private int getIsTop(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "isTop");
    }

    private boolean isVr(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "isVr");
    }

    private Object getLabelLarge(Hook hook, Object aweme) {
        return hook.getField(aweme, "labelLarge");
    }

    private Object getLabelPrivate(Hook hook, Object aweme) {
        return hook.getField(aweme, "labelPrivate");
    }

    private Object getLabelThumb(Hook hook, Object aweme) {
        return hook.getField(aweme, "labelThumb");
    }

    private Object getLabelTop(Hook hook, Object aweme) {
        return hook.getField(aweme, "labelTop");
    }

    private String getLandingPage(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "landingPage");
    }

    private boolean isLawCriticalCountry(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "lawCriticalCountry");
    }

    private Object getLinkAdData(Hook hook, Object aweme) {
        return hook.getField(aweme, "linkAdData");
    }

    private List<?> getLongVideos(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "longVideos");
    }

    private Object getmCommerceVideoAuthInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "Hook hook, Object aweme");
    }

    private int getmConcatAndUploadState(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "mConcatAndUploadState");
    }

    private String getmLabelMusicStarterText(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "mLabelMusicStarterText");
    }

    private String getmLabelOriginAuthorText(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "mLabelOriginAuthorText");
    }

    private Object getmLiveAwesomeSplashInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "mLiveAwesomeSplashInfo");
    }

    private HashMap<String, String> getmMobParams(Hook hook, Object aweme) {
        return (HashMap<String, String>) hook.getField(aweme, "mMobParams");
    }

    private List<?> getmNewRelationLabel(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "mNewRelationLabel");
    }

    private Object getmRoomFeedCellStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "mRoomFeedCellStruct");
    }

    private Object getMicroAppInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "microAppInfo");
    }

    private Object getMixInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "mixInfo");
    }

    private Object getMusic(Hook hook, Object aweme) {
        return hook.getField(aweme, "music");
    }

    private Object getMusicStarter(Hook hook, Object aweme) {
        return hook.getField(aweme, "musicStarter");
    }

    private Object getNearbyCardStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "nearbyCardStruct");
    }

    private boolean isNeedVisionSearchEntry(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "needVisionSearchEntry");
    }

    private String getNewSourceId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "newSourceId");
    }

    private String getNewSourceType(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "newSourceType");
    }

    private List<?> getNicknamePosition(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "nicknamePosition");
    }

    private String getOpenPlatformName(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "openPlatformName");
    }

    private Object getOpenPlatformStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "openPlatformStruct");
    }

    private Object getOriginAuthor(Hook hook, Object aweme) {
        return hook.getField(aweme, "originAuthor");
    }

    private List<String> getOriginCommentIds(Hook hook, Object aweme) {
        return (List<String>) hook.getField(aweme, "originCommentIds");
    }

    private int getOriginalPos(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "originalPos");
    }

    private Object getPoiOpCardStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "poiOpCardStruct");
    }

    private Object getPoiRankCardStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "poiRankCardStruct");
    }

    private Object getPoiStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "poiStruct");
    }

    private List<?> getPosition(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "position");
    }

    private String getPreForwardId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "preForwardId");
    }

    private Object getPreload(Hook hook, Object aweme) {
        return hook.getField(aweme, "preload");
    }

    private boolean isPreventDownload(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "preventDownload");
    }

    private Object getPromotionOtherInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "promotionOtherInfo");
    }

    private int getRate(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "rate");
    }

    private String getRateScore(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "rateScore");
    }

    private String getReactFrom(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "reactFrom");
    }

    private String getReactOrigin(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "reactOrigin");
    }

    private int getReactSetting(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "reactSetting");
    }

    private String getReactView(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "reactView");
    }

    private String getRegion(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "region");
    }

    private Object getRelationLabel(Hook hook, Object aweme) {
        return hook.getField(aweme, "relationLabel");
    }

    private String getRepostFromGroupId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "repostFromGroupId");
    }

    private String getRepostFromUserId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "repostFromUserId");
    }

    private String getRequestId(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "requestId");
    }

    private Object getRoom(Hook hook, Object aweme) {
        return hook.getField(aweme, "room");
    }

    private int getScenario(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "scenario");
    }

    private Object getShareInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "shareInfo");
    }

    private String getShareUrl(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "shareUrl");
    }

    private Object getSimplePoiInfoStruct(Hook hook, Object aweme) {
        return hook.getField(aweme, "simplePoiInfoStruct");
    }

    private List<?> getSimplePromotions(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "simplePromotions");
    }

    private String getSimplePromotionsString(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "simplePromotionsString");
    }

    private String getSimpleShopSeeding(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "simpleShopSeeding");
    }

    private Object getSpecialSticker(Hook hook, Object aweme) {
        return hook.getField(aweme, "specialSticker");
    }

    private Object getStarAtlasInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "starAtlasInfo");
    }

    private long getStarAtlasOrderId(Hook hook, Object aweme) {
        return hook.getLongField(aweme, "starAtlasOrderId");
    }

    private String getStarRecommendTag(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "starRecommendTag");
    }

    private Object getStatistics(Hook hook, Object aweme) {
        return hook.getField(aweme, "statistics");
    }

    private Object getStatus(Hook hook, Object aweme) {
        return hook.getField(aweme, "status");
    }

    private Object getStickerEntranceInfo(Hook hook, Object aweme) {
        return hook.getField(aweme, "stickerEntranceInfo");
    }

    private String getStickerIDs(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "stickerIDs");
    }

    private Object getStreamUrlModel(Hook hook, Object aweme) {
        return hook.getField(aweme, "streamUrlModel");
    }

    private String getTakeDownDesc(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "takeDownDesc");
    }

    private int getTakeDownReason(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "takeDownReason");
    }

    private List<?> getTextExtra(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "textExtra");
    }

    private List<?> getTextTopLabels(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "textTopLabels");
    }

    private List<?> getTextVideoLabels(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "textVideoLabels");
    }

    private String getTitle(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "title");
    }

    private List<?> getUniqidPosition(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "uniqidPosition");
    }

    private String getUploadMiscInfoStructStr(Hook hook, Object aweme) {
        return (String) hook.getField(aweme, "uploadMiscInfoStructStr");
    }

    private int getUserDigg(Hook hook, Object aweme) {
        return hook.getIntegerField(aweme, "userDigg");
    }

    private Object getVideo(Hook hook, Object aweme) {
        return hook.getField(aweme, "video");
    }

    private Object getVideoControl(Hook hook, Object aweme) {
        return hook.getField(aweme, "videoControl");
    }

    private List<?> getVideoLabels(Hook hook, Object aweme) {
        return (List<?>) hook.getField(aweme, "videoLabels");
    }

    private boolean isWithPromotionalMusic(Hook hook, Object aweme) {
        return hook.getBooleanField(aweme, "withPromotionalMusic");
    }

    private Object getXiGuaTask(Hook hook, Object aweme) {
        return hook.getField(aweme, "xiGuaTask");
    }
}