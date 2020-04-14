package com.micro.tremolo.inflood.inner.replace;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/24 18:10
 * @Source 抖音版本960 com.ss.android.ugc.aweme.feed.model.Aweme
 */
public class Aweme {

    public static void update(final Hook hook, final Callback callback) {
        hook.methodMonitor(TremoloParam.AWEME_FEED_MODEL_AWEME_CLASS, TremoloParam.AWEME_UPDATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                callback.loadData(new Aweme(hook, param.getArgs()[0]), monitorToString(hook, param.getArgs()[0]));
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_MODEL_AWEME_CLASS));
    }

    public static Aweme clone(final Hook hook, Object aweme) {
        return new Aweme(hook, hook.callMethod(aweme, TremoloParam.AWEME_CLONE_METHOD));
    }

    public static String monitorToString(Hook hook, Object aweme) {
        return hook.callMethod(aweme, TremoloParam.AWEME_TO_STRING_METHOD).toString();
    }

    private String activityId;
    private CommerceActivityStruct activityPendant;//b.class
    private int adAwemeSource;
    private boolean adDescHandle = true;
    private int adDescMaxLines = 4;
    private int adLinkType;
    private String adOrderId;
    private String adSchedule;
    private String aid;
    private Object anchor;//Anchor.class
    private Object anchorInfo;//AnchorInfo.class
    private User author;//User.class
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
    private List<User> familiarRecommendUser;//User.class
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
    private UrlModel labelLarge;//UrlModel.class
    private UrlModel labelPrivate;//UrlModel.class
    private UrlModel labelThumb;//UrlModel.class
    private UrlModel labelTop;//UrlModel.class
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
    private UrlModel musicStarter;//UrlModel.class
    private Object nearbyCardStruct;//NearbyCardStruct.class
    private boolean needVisionSearchEntry;
    private String newSourceId;
    private String newSourceType;
    private List<Object> nicknamePosition;//Position.class
    private String openPlatformName;
    private Object openPlatformStruct;//e.class
    private UrlModel originAuthor;//UrlModel.class
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
    private AwemeStatistics statistics;//AwemeStatistics.class
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
    private Video video;//Video.class
    private Object videoControl;//VideoControl.class
    private List<Object> videoLabels;//AwemeLabelModel.class
    private boolean withPromotionalMusic;
    private Object xiGuaTask;//XiGuaTaskStruct.class

    public Aweme(Hook hook, Object aweme) {
        loadAweme(hook, aweme);
        monitorToString(hook, aweme);
    }

    public void loadAweme(Hook hook, Object aweme) {
        if (Lang.isNull(aweme)) {
            return;
        }
        this.activityId = getActivityId(hook, aweme);
        //logger.d("Aweme", String.format("activityId[%s]", activityId));
        this.activityPendant = new CommerceActivityStruct(hook, getActivityPendant(hook, aweme));
        //logger.d("Aweme", String.format("activityPendant[%s]", JSON.toJSONString(activityPendant)));
        this.adAwemeSource = getAdAwemeSource(hook, aweme);
        //logger.d("Aweme", String.format("adAwemeSource[%s]", adAwemeSource));
        this.adDescHandle = isAdDescHandle(hook, aweme);
        //logger.d("Aweme", String.format("adDescHandle[%s]", adDescHandle));
        this.adDescMaxLines = getAdDescMaxLines(hook, aweme);
        //logger.d("Aweme", String.format("adDescMaxLines[%s]", adDescMaxLines));
        this.adLinkType = getAdLinkType(hook, aweme);
        //logger.d("Aweme", String.format("adLinkType[%s]", adLinkType));
        this.adOrderId = getAdOrderId(hook, aweme);
        //logger.d("Aweme", String.format("adOrderId[%s]", adOrderId));
        this.adSchedule = getAdSchedule(hook, aweme);
        //logger.d("Aweme", String.format("adSchedule[%s]", adSchedule));
        this.aid = getAid(hook, aweme);
        //logger.d("Aweme", String.format("视频Id[%s]", aid));
        this.anchor = getAnchor(hook, aweme);
        //logger.d("Aweme", String.format("anchor[%s]", anchor));
        this.anchorInfo = getAnchorInfo(hook, aweme);
        //logger.d("Aweme", String.format("anchorInfo[%s]", anchorInfo));
        this.author = new User(hook, getAuthor(hook, aweme));
        //logger.d("Aweme", String.format("author[%s]", author));
        this.awemeACLShareInfo = getAwemeACLShareInfo(hook, aweme);
        //logger.d("Aweme", String.format("awemeACLShareInfo[%s]", awemeACLShareInfo));
        this.awemeNationalTask = getAwemeNationalTask(hook, aweme);
        //logger.d("Aweme", String.format("awemeNationalTask[%s]", awemeNationalTask));
        this.awemePosition = getAwemePosition(hook, aweme);
        //logger.d("Aweme", String.format("awemePosition[%s]", awemePosition));
        this.awemeRawAd = getAwemeRawAd(hook, aweme);
        //logger.d("Aweme", String.format("awemeRawAd[%s]", awemeRawAd));
        this.awemeRiskModel = getAwemeRiskModel(hook, aweme);
        //logger.d("Aweme", String.format("awemeRiskModel[%s]", JSON.toJSONString(awemeRiskModel)));
        this.awemeType = getAwemeType(hook, aweme);
        //logger.d("Aweme", String.format("awemeType[%s]", awemeType));
        this.bannerTip = getBannerTip(hook, aweme);
        //logger.d("Aweme", String.format("bannerTip[%s]", bannerTip));
        this.canPlay = isCanPlay(hook, aweme);
        //logger.d("Aweme", String.format("canPlay[%s]", canPlay));
        this.challengeList = (List<Object>) getChallengeList(hook, aweme);
        //logger.d("Aweme", String.format("challengeList[%s]", JSON.toJSONString(challengeList)));
        this.cmtSwt = isCmtSwt(hook, aweme);
        //logger.d("Aweme", String.format("cmtSwt[%s]", cmtSwt));
        this.collectStatus = getCollectStatus(hook, aweme);
        //logger.d("Aweme", String.format("collectStatus[%s]", collectStatus));
        this.commentSetting = getCommentSetting(hook, aweme);
        //logger.d("Aweme", String.format("commentSetting[%s]", commentSetting));
        this.commerceConfigDataList = (List<Object>) getCommerceConfigDataList(hook, aweme);
        //logger.d("Aweme", String.format("commerceConfigDataList[%s]", JSON.toJSONString(commerceConfigDataList)));
        this.commerceStickerInfo = getCommerceStickerInfo(hook, aweme);
        //logger.d("Aweme", String.format("commerceStickerInfo[%s]", commerceStickerInfo));
        this.createTime = getCreateTime(hook, aweme);
        //logger.d("Aweme", String.format("创建时间[%s]", createTime));
        this.date = getDate(hook, aweme);
        //logger.d("Aweme", String.format("date[%s]", date));
        this.dcdVideoExtra = getDcdVideoExtra(hook, aweme);
        //logger.d("Aweme", String.format("dcdVideoExtra[%s]", dcdVideoExtra));
        this.desc = getDesc(hook, aweme);
        //logger.d("Aweme", String.format("标题[%s]", desc));
        this.descLanguage = getDescLanguage(hook, aweme);
        //logger.d("Aweme", String.format("descLanguage[%s]", descLanguage));
        this.descendantsModel = getDescendantsModel(hook, aweme);
        //logger.d("Aweme", String.format("descendantsModel[%s]", descendantsModel));
        this.distance = getDistance(hook, aweme);
        //logger.d("Aweme", String.format("distance[%s]", JSON.toJSONString(distance)));
        this.distributeType = getDistributeType(hook, aweme);
        //logger.d("Aweme", String.format("distributeType[%s]", distributeType));
        this.downloadWithoutWatermark = isDownloadWithoutWatermark(hook, aweme);
        //logger.d("Aweme", String.format("downloadWithoutWatermark[%s]", downloadWithoutWatermark));
        this.duetSetting = getDuetSetting(hook, aweme);
        //logger.d("Aweme", String.format("duetSetting[%s]", duetSetting));
        this.enableTopView = isEnableTopView(hook, aweme);
        //logger.d("Aweme", String.format("enableTopView[%s]", enableTopView));
        this.externalType = getExternalType(hook, aweme);
        //logger.d("Aweme", String.format("externalType[%s]", externalType));
        this.extra = getExtra(hook, aweme);
        //logger.d("Aweme", String.format("extra[%s]", extra));
        this.familiarRecommendUser = getFamiliarRecommendUser(hook, aweme);
        //logger.d("Aweme", String.format("familiarRecommendUser[%s]", familiarRecommendUser));
        this.feedCount = getFeedCount(hook, aweme);
        //logger.d("Aweme", String.format("feedCount[%s]", feedCount));
        this.feedRelationLabel = getFeedRelationLabel(hook, aweme);
        //logger.d("Aweme", String.format("feedRelationLabel[%s]", feedRelationLabel));
        this.floatingCardInfo = getFloatingCardInfo(hook, aweme);
        //logger.d("Aweme", String.format("floatingCardInfo[%s]", floatingCardInfo));
        this.forwardCommentId = getForwardCommentId(hook, aweme);
        //logger.d("Aweme", String.format("forwardCommentId[%s]", forwardCommentId));
        this.forwardItem = getForwardItem(hook, aweme);
        //logger.d("Aweme", String.format("forwardItem[%s]", forwardItem));
        this.forwardItemId = getForwardItemId(hook, aweme);
        //logger.d("Aweme", String.format("forwardItemId[%s]", forwardItemId));
        this.fromRawChallenge = getFromRawChallenge(hook, aweme);
        //logger.d("Aweme", String.format("fromRawChallenge[%s]", fromRawChallenge));
        this.gameInfo = getGameInfo(hook, aweme);
        //logger.d("Aweme", String.format("gameInfo[%s]", gameInfo));
        this.hasVisionSearchEntry = isHasVisionSearchEntry(hook, aweme);
        //logger.d("Aweme", String.format("hasVisionSearchEntry[%s]", hasVisionSearchEntry));
        this.hotListStruct = getHotListStruct(hook, aweme);
        //logger.d("Aweme", String.format("hotListStruct[%s]", hotListStruct));
        this.hotSearchInfo = getHotSearchInfo(hook, aweme);
        //logger.d("Aweme", String.format("hotSearchInfo[%s]", hotSearchInfo));
        this.hotSpot = getHotSpot(hook, aweme);
        //logger.d("Aweme", String.format("hotSpot[%s]", hotSpot));
        this.imageInfos = (List<Object>) getImageInfos(hook, aweme);
        //logger.d("Aweme", String.format("imageInfos[%s]", JSON.toJSONString(imageInfos)));
        this.interactStickerStructs = (List<Object>) getInteractStickerStructs(hook, aweme);
        //logger.d("Aweme", String.format("interactStickerStructs[%s]", JSON.toJSONString(interactStickerStructs)));
        this.isAd = isAd(hook, aweme);
        //logger.d("Aweme", String.format("isAd[%s]", isAd));
        this.isCanCache = isCanCache(hook, aweme);
        //logger.d("Aweme", String.format("isCanCache[%s]", isCanCache));
        this.isEffectDesigner = isEffectDesigner(hook, aweme);
        //logger.d("Aweme", String.format("isEffectDesigner[%s]", isEffectDesigner));
        this.isFakeResponse = isFakeResponse(hook, aweme);
        //logger.d("Aweme", String.format("isFakeResponse[%s]", isFakeResponse));
        this.isFamiliar = isFamiliar(hook, aweme);
        //logger.d("Aweme", String.format("isFamiliar[%s]", isFamiliar));
        this.isFirstInSpot = isFirstInSpot(hook, aweme);
        //logger.d("Aweme", String.format("isFirstInSpot[%s]", isFirstInSpot));
        this.isFromDouPlusGuideAnimate = isFromDouPlusGuideAnimate(hook, aweme);
        //logger.d("Aweme", String.format("isFromDouPlusGuideAnimate[%s]", isFromDouPlusGuideAnimate));
        this.isHashTag = getIsHashTag(hook, aweme);
        //logger.d("Aweme", String.format("isHashTag[%s]", isHashTag));
        this.isLastInSpot = isLastInSpot(hook, aweme);
        //logger.d("Aweme", String.format("isLastInSpot[%s]", isLastInSpot));
        this.isPgcShow = isPgcShow(hook, aweme);
        //logger.d("Aweme", String.format("isPgcShow[%s]", isPgcShow));
        this.isPreloadScroll = isPreloadScroll(hook, aweme);
        //logger.d("Aweme", String.format("isPreloadScroll[%s]", isPreloadScroll));
        this.isPreview = getIsPreview(hook, aweme);
        //logger.d("Aweme", String.format("isPreview[%s]", isPreview));
        this.isProhibited = isProhibited(hook, aweme);
        //logger.d("Aweme", String.format("isProhibited[%s]", isProhibited));
        this.isRelieve = isRelieve(hook, aweme);
        //logger.d("Aweme", String.format("isRelieve[%s]", isRelieve));
        this.isTop = getIsTop(hook, aweme);
        //logger.d("Aweme", String.format("isTop[%s]", isTop));
        this.isVr = isVr(hook, aweme);
        //logger.d("Aweme", String.format("isVr[%s]", isVr));
        this.labelLarge = new UrlModel(hook, getLabelLarge(hook, aweme));
        //logger.d("Aweme", String.format("labelLarge[%s]", JSON.toJSONString(labelLarge)));
        this.labelPrivate = new UrlModel(hook, getLabelPrivate(hook, aweme));
        //logger.d("Aweme", String.format("labelPrivate[%s]", JSON.toJSONString(labelPrivate)));
        this.labelThumb = new UrlModel(hook, getLabelThumb(hook, aweme));
        //logger.d("Aweme", String.format("labelThumb[%s]", JSON.toJSONString(labelThumb)));
        this.labelTop = new UrlModel(hook, getLabelThumb(hook, aweme));
        //logger.d("Aweme", String.format("labelTop[%s]", JSON.toJSONString(labelTop)));
        this.landingPage = getLandingPage(hook, aweme);
        //logger.d("Aweme", String.format("landingPage[%s]", landingPage));
        this.lawCriticalCountry = isLawCriticalCountry(hook, aweme);
        //logger.d("Aweme", String.format("lawCriticalCountry[%s]", lawCriticalCountry));
        this.linkAdData = getLinkAdData(hook, aweme);
        //logger.d("Aweme", String.format("linkAdData[%s]", linkAdData));
        this.longVideos = (List<Object>) getLongVideos(hook, aweme);
        //logger.d("Aweme", String.format("longVideos[%s]", JSON.toJSONString(longVideos)));
        this.mCommerceVideoAuthInfo = getmCommerceVideoAuthInfo(hook, aweme);
        //logger.d("Aweme", String.format("mCommerceVideoAuthInfo[%s]", mCommerceVideoAuthInfo));
        this.mConcatAndUploadState = getmConcatAndUploadState(hook, aweme);
        //logger.d("Aweme", String.format("mConcatAndUploadState[%s]", mConcatAndUploadState));
        this.mLabelMusicStarterText = getmLabelMusicStarterText(hook, aweme);
        //logger.d("Aweme", String.format("mLabelMusicStarterText[%s]", mLabelMusicStarterText));
        this.mLabelOriginAuthorText = getmLabelOriginAuthorText(hook, aweme);
        //logger.d("Aweme", String.format("mLabelOriginAuthorText[%s]", mLabelOriginAuthorText));
        this.mLiveAwesomeSplashInfo = getmLiveAwesomeSplashInfo(hook, aweme);
        //logger.d("Aweme", String.format("mLiveAwesomeSplashInfo[%s]", mLiveAwesomeSplashInfo));
        this.mMobParams = getmMobParams(hook, aweme);
        //logger.d("Aweme", String.format("mMobParams[%s]", mMobParams));
        this.mNewRelationLabel = (List<Object>) getmNewRelationLabel(hook, aweme);
        //logger.d("Aweme", String.format("mNewRelationLabel[%s]", mNewRelationLabel));
        this.mRoomFeedCellStruct = getmRoomFeedCellStruct(hook, aweme);
        //logger.d("Aweme", String.format("mRoomFeedCellStruct[%s]", mRoomFeedCellStruct));
        this.microAppInfo = getMicroAppInfo(hook, aweme);
        //logger.d("Aweme", String.format("microAppInfo[%s]", microAppInfo));
        this.mixInfo = getMixInfo(hook, aweme);
        //logger.d("Aweme", String.format("mixInfo[%s]", mixInfo));
        this.music = getMusic(hook, aweme);
        //logger.d("Aweme", String.format("music[%s]", JSON.toJSONString(music)));
        this.musicStarter = new UrlModel(hook, getMusicStarter(hook, aweme));
        //logger.d("Aweme", String.format("musicStarter[%s]", JSON.toJSONString(musicStarter)));
        this.nearbyCardStruct = getNearbyCardStruct(hook, aweme);
        //logger.d("Aweme", String.format("nearbyCardStruct[%s]", nearbyCardStruct));
        this.needVisionSearchEntry = isNeedVisionSearchEntry(hook, aweme);
        //logger.d("Aweme", String.format("needVisionSearchEntry[%s]", needVisionSearchEntry));
        this.newSourceId = getNewSourceId(hook, aweme);
        //logger.d("Aweme", String.format("newSourceId[%s]", newSourceId));
        this.newSourceType = getNewSourceType(hook, aweme);
        //logger.d("Aweme", String.format("newSourceType[%s]", newSourceType));
        this.nicknamePosition = (List<Object>) getNicknamePosition(hook, aweme);
        //logger.d("Aweme", String.format("nicknamePosition[%s]", JSON.toJSONString(nicknamePosition)));
        this.openPlatformName = getOpenPlatformName(hook, aweme);
        //logger.d("Aweme", String.format("openPlatformName[%s]", openPlatformName));
        this.openPlatformStruct = getOpenPlatformStruct(hook, aweme);
        //logger.d("Aweme", String.format("openPlatformStruct[%s]", openPlatformStruct));
        this.originAuthor = new UrlModel(hook, getOriginAuthor(hook, aweme));
        //logger.d("Aweme", String.format("originAuthor[%s]", JSON.toJSONString(originAuthor)));
        this.originCommentIds = getOriginCommentIds(hook, aweme);
        //logger.d("Aweme", String.format("originCommentIds[%s]", JSON.toJSONString(originCommentIds)));
        this.originalPos = getOriginalPos(hook, aweme);
        //logger.d("Aweme", String.format("originalPos[%s]", originalPos));
        this.poiOpCardStruct = getPoiOpCardStruct(hook, aweme);
        //logger.d("Aweme", String.format("poiOpCardStruct[%s]", poiOpCardStruct));
        this.poiRankCardStruct = getPoiRankCardStruct(hook, aweme);
        //logger.d("Aweme", String.format("poiRankCardStruct[%s]", poiRankCardStruct));
        this.poiStruct = getPoiStruct(hook, aweme);
        //logger.d("Aweme", String.format("poiStruct[%s]", poiStruct));
        this.position = (List<Object>) getPosition(hook, aweme);
        //logger.d("Aweme", String.format("position[%s]", JSON.toJSONString(position)));
        this.preForwardId = getPreForwardId(hook, aweme);
        //logger.d("Aweme", String.format("preForwardId[%s]", preForwardId));
        this.preload = getPreload(hook, aweme);
        //logger.d("Aweme", String.format("preload[%s]", preload));
        this.preventDownload = isPreventDownload(hook, aweme);
        //logger.d("Aweme", String.format("preventDownload[%s]", preventDownload));
        this.promotionOtherInfo = getPromotionOtherInfo(hook, aweme);
        //logger.d("Aweme", String.format("promotionOtherInfo[%s]", promotionOtherInfo));
        this.rate = getRate(hook, aweme);
        //logger.d("Aweme", String.format("rate[%s]", rate));
        this.rateScore = getRateScore(hook, aweme);
        //logger.d("Aweme", String.format("rateScore[%s]", rateScore));
        this.reactFrom = getReactFrom(hook, aweme);
        //logger.d("Aweme", String.format("reactFrom[%s]", reactFrom));
        this.reactOrigin = getReactOrigin(hook, aweme);
        //logger.d("Aweme", String.format("reactOrigin[%s]", reactOrigin));
        this.reactSetting = getReactSetting(hook, aweme);
        //logger.d("Aweme", String.format("reactSetting[%s]", reactSetting));
        this.reactView = getReactView(hook, aweme);
        //logger.d("Aweme", String.format("reactView[%s]", reactView));
        this.region = getRegion(hook, aweme);
        //logger.d("Aweme", String.format("region[%s]", JSON.toJSONString(region)));
        this.relationLabel = getRelationLabel(hook, aweme);
        //logger.d("Aweme", String.format("relationLabel[%s]", relationLabel));
        this.repostFromGroupId = getRepostFromGroupId(hook, aweme);
        //logger.d("Aweme", String.format("repostFromGroupId[%s]", repostFromGroupId));
        this.repostFromUserId = getRepostFromUserId(hook, aweme);
        //logger.d("Aweme", String.format("repostFromUserId[%s]", repostFromUserId));
        this.requestId = getRequestId(hook, aweme);
        //logger.d("Aweme", String.format("requestId[%s]", requestId));
        this.room = getRoom(hook, aweme);
        //logger.d("Aweme", String.format("room[%s]", room));
        this.scenario = getScenario(hook, aweme);
        //logger.d("Aweme", String.format("scenario[%s]", scenario));
        this.shareInfo = getShareInfo(hook, aweme);
        //logger.d("Aweme", String.format("shareInfo[%s]", JSON.toJSONString(shareInfo)));
        this.shareUrl = getShareUrl(hook, aweme);
        //logger.d("Aweme", String.format("分享链接[%s]", shareUrl));
        this.simplePoiInfoStruct = getSimplePoiInfoStruct(hook, aweme);
        //logger.d("Aweme", String.format("simplePoiInfoStruct[%s]", simplePoiInfoStruct));
        this.simplePromotions = (List<Object>) getSimplePromotions(hook, aweme);
        //logger.d("Aweme", String.format("simplePromotions[%s]", simplePromotions));
        this.simplePromotionsString = getSimplePromotionsString(hook, aweme);
        //logger.d("Aweme", String.format("simplePromotionsString[%s]", simplePromotionsString));
        this.simpleShopSeeding = getSimpleShopSeeding(hook, aweme);
        //logger.d("Aweme", String.format("simpleShopSeeding[%s]", simpleShopSeeding));
        this.specialSticker = getSpecialSticker(hook, aweme);
        //logger.d("Aweme", String.format("specialSticker[%s]", specialSticker));
        this.starAtlasInfo = getStarAtlasInfo(hook, aweme);
        //logger.d("Aweme", String.format("starAtlasInfo[%s]", starAtlasInfo));
        this.starAtlasOrderId = getStarAtlasOrderId(hook, aweme);
        //logger.d("Aweme", String.format("starAtlasOrderId[%s]", starAtlasOrderId));
        this.starRecommendTag = getStarRecommendTag(hook, aweme);
        //logger.d("Aweme", String.format("starRecommendTag[%s]", starRecommendTag));
        this.statistics = new AwemeStatistics(hook, getStatistics(hook, aweme));
        //logger.d("Aweme", String.format("statistics[%s]", JSON.toJSONString(statistics)));
        this.status = getStatus(hook, aweme);
        //logger.d("Aweme", String.format("status[%s]", status));
        this.stickerEntranceInfo = getStickerEntranceInfo(hook, aweme);
        //logger.d("Aweme", String.format("stickerEntranceInfo[%s]", stickerEntranceInfo));
        this.stickerIDs = getStickerIDs(hook, aweme);
        //logger.d("Aweme", String.format("stickerIDs[%s]", stickerIDs));
        this.streamUrlModel = getStreamUrlModel(hook, aweme);
        //logger.d("Aweme", String.format("streamUrlModel[%s]", streamUrlModel));
        this.takeDownDesc = getTakeDownDesc(hook, aweme);
        //logger.d("Aweme", String.format("takeDownDesc[%s]", takeDownDesc));
        this.takeDownReason = getTakeDownReason(hook, aweme);
        //logger.d("Aweme", String.format("takeDownReason[%s]", takeDownReason));
        this.textExtra = (List<Object>) getTextExtra(hook, aweme);
        //logger.d("Aweme", String.format("textExtra[%s]", JSON.toJSONString(textExtra)));
        this.textTopLabels = (List<Object>) getTextTopLabels(hook, aweme);
        //logger.d("Aweme", String.format("textTopLabels[%s]", JSON.toJSONString(textTopLabels)));
        this.textVideoLabels = (List<Object>) getTextVideoLabels(hook, aweme);
        //logger.d("Aweme", String.format("textVideoLabels[%s]", JSON.toJSONString(textVideoLabels)));
        this.title = getTitle(hook, aweme);
        //logger.d("Aweme", String.format("title[%s]", title));
        this.uniqidPosition = (List<Object>) getUniqidPosition(hook, aweme);
        //logger.d("Aweme", String.format("uniqidPosition[%s]", JSON.toJSONString(uniqidPosition)));
        this.uploadMiscInfoStructStr = getUploadMiscInfoStructStr(hook, aweme);
        //logger.d("Aweme", String.format("uploadMiscInfoStructStr[%s]", uploadMiscInfoStructStr));
        this.userDigg = getUserDigg(hook, aweme);
        //logger.d("Aweme", String.format("userDigg[%s]", userDigg));
        this.video = getVideo(hook, aweme);
        //logger.d("Aweme", String.format("video[%s]", video));
        this.videoControl = getVideoControl(hook, aweme);
        //logger.d("Aweme", String.format("videoControl[%s]", JSON.toJSONString(videoControl)));
        this.videoLabels = (List<Object>) getVideoLabels(hook, aweme);
        //logger.d("Aweme", String.format("videoLabels[%s]", JSON.toJSONString(videoLabels)));
        this.withPromotionalMusic = isWithPromotionalMusic(hook, aweme);
        //logger.d("Aweme", String.format("withPromotionalMusic[%s]", withPromotionalMusic));
        this.xiGuaTask = getXiGuaTask(hook, aweme);
        //logger.d("Aweme", String.format("xiGuaTask[%s]", JSON.toJSONString(xiGuaTask)));
    }

    public String getActivityId() {
        return activityId;
    }

    public CommerceActivityStruct getActivityPendant() {
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

    public User getAuthor() {
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

    public List<User> getFamiliarRecommendUser() {
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

    public UrlModel getLabelLarge() {
        return labelLarge;
    }

    public UrlModel getLabelPrivate() {
        return labelPrivate;
    }

    public UrlModel getLabelThumb() {
        return labelThumb;
    }

    public UrlModel getLabelTop() {
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

    public UrlModel getMusicStarter() {
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

    public UrlModel getOriginAuthor() {
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

    public AwemeStatistics getStatistics() {
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

    public Video getVideo() {
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

    private List<User> getFamiliarRecommendUser(Hook hook, Object aweme) {
        List<User> users = new ArrayList<>();
        List<Object> objects = (List<Object>) hook.getField(aweme, "familiarRecommendUser");
        if (objects != null) {
            for (Object object : objects) {
                User user = new User(hook, object);
                users.add(user);
            }
        }
        return users;
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
        return hook.getField(aweme, "mCommerceVideoAuthInfo");
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

    private Video getVideo(Hook hook, Object aweme) {
        return new Video(hook, hook.getField(aweme, "video"));
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

    public interface Callback {
        void loadData(Aweme aweme, String msg);
    }
}