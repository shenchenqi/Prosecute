package com.micro.tremolo.inflood.inner.execute.account;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-18 上午10:21
 */
public class Account extends Plugin<AccountPresenter, AccountInter> implements AccountInter {

    public Account(Hook hook, Context context) throws Throwable {
        super(hook, context);
        logger.i("账户初始化");
    }

    @Override
    protected AccountPresenter getPresenter() {
        return new AccountPresenter();
    }

    @Override
    protected void execute() {
        monitor(getHook());
    }

    @Override
    public void monitor(Hook hook) {
    }

    public void sendUserData() {
        try {
            if (!User.isLogin(getHook())) {
                logger.i("用户未登录");
            } else {
                Object userInfo = User.getUserInfo(getHook());
                Map<String, Object> userData = getUserData(userInfo);
                logger.i( "用户信息: " + JSON.toJSONString(userData));
            }
        } catch (Exception e) {
            logger.e(e, "用户信息失败");
        }
    }

    private Map<String, Object> getUserData(Object selfUserInfo) throws Exception {
        Map<String, Object> accountData = new HashMap<>();
        accountData.put("uid", uid(selfUserInfo));
        accountData.put("nickName", nickName(selfUserInfo));
        accountData.put("gender", gender(selfUserInfo));
        accountData.put("language", language(selfUserInfo));
        accountData.put("location", location(selfUserInfo));
        accountData.put("country", country(selfUserInfo));
        accountData.put("region", region(selfUserInfo));
        accountData.put("province", province(selfUserInfo));
        accountData.put("city", city(selfUserInfo));
        accountData.put("fansCount", fansCount(selfUserInfo));
        accountData.put("signature", signature(selfUserInfo));
        accountData.put("schoolName", schoolName(selfUserInfo));
        accountData.put("bindPhone", bindPhone(selfUserInfo));
        accountData.put("dongtaiCount", dongtaiCount(selfUserInfo));
        accountData.put("favoritingCount", favoritingCount(selfUserInfo));
        accountData.put("totalFavorited", totalFavorited(selfUserInfo));
        accountData.put("followingCount", followingCount(selfUserInfo));
        return accountData;
    }

    private String uid(Object selfUserInfo) throws Exception {
        String Uid = User.getUid(getHook(), selfUserInfo);
        logger.i(String.format("Uid[%s]", Uid));
        return Uid;
    }

    private String nickName(Object selfUserInfo) throws Exception {
        String nickname = User.getNickname(getHook(), selfUserInfo);
        logger.i(String.format("昵称[%s]", nickname));
        return nickname;
    }

    private String gender(Object selfUserInfo) throws Exception {
        String gender = User.getGender(getHook(), selfUserInfo);
        logger.i(String.format("性别[%s]", gender));
        return gender;
    }

    private String language(Object selfUserInfo) throws Exception {
        String language = User.getLanguage(getHook(), selfUserInfo);
        logger.i(String.format("语言[%s]", language));
        return language;
    }

    private String location(Object selfUserInfo) throws Exception {
        String location = User.getLocation(getHook(), selfUserInfo);
        logger.i(String.format("位置[%s]", location));
        return location;
    }

    private String country(Object selfUserInfo) throws Exception {
        String country = User.getCountry(getHook(), selfUserInfo);
        logger.i(String.format("国家[%s]", country));
        return country;
    }

    private String region(Object selfUserInfo) throws Exception {
        String region = User.getRegion(getHook(), selfUserInfo);
        logger.i(String.format("区域[%s]", region));
        return region;
    }

    private String province(Object selfUserInfo) throws Exception {
        String province = User.getProvince(getHook(), selfUserInfo);
        logger.i(String.format("省[%s]", province));
        return province;
    }

    private String city(Object selfUserInfo) throws Exception {
        String city = User.getCity(getHook(), selfUserInfo);
        logger.i(String.format("城市[%s]", city));
        return city;
    }

    private Integer fansCount(Object selfUserInfo) throws Exception {
        Integer fansCount = User.getFansCount(getHook(), selfUserInfo);
        logger.i(String.format("粉丝数[%s]", fansCount));
        return fansCount;
    }

    private String signature(Object selfUserInfo) throws Exception {
        String signature = User.getSignature(getHook(), selfUserInfo);
        logger.i(String.format("个性签名[%s]", signature));
        return signature;
    }

    private String schoolName(Object selfUserInfo) throws Exception {
        String schoolName = User.getSchoolName(getHook(), selfUserInfo);
        logger.i(String.format("学校名[%s]", schoolName));
        return schoolName;
    }

    private String bindPhone(Object selfUserInfo) throws Exception {
        String bindPhone = User.getBindPhone(getHook(), selfUserInfo);
        logger.i(String.format("手机号[%s]", bindPhone));
        return bindPhone;
    }

    private Integer dongtaiCount(Object selfUserInfo) throws Exception {
        Integer dongtaiCount = User.getDongtaiCount(getHook(), selfUserInfo);
        logger.i(String.format("动态数[%s]", dongtaiCount));
        return dongtaiCount;
    }

    private Integer favoritingCount(Object selfUserInfo) throws Exception {
        Integer favoritingCount = User.getFavoritingCount(getHook(), selfUserInfo);
        logger.i(String.format("喜欢数[%s]", favoritingCount));
        return favoritingCount;
    }

    private Integer totalFavorited(Object selfUserInfo) throws Exception {
        Integer totalFavorited = User.getTotalFavorited(getHook(), selfUserInfo);
        logger.i(String.format("获赞数[%s]", totalFavorited));
        return totalFavorited;
    }

    private Integer followingCount(Object selfUserInfo) throws Exception {
        Integer followingCount = User.getFollowingCount(getHook(), selfUserInfo);
        logger.i(String.format("关注数[%s]", followingCount));
        return followingCount;
    }

    public static class User {

        final static String MY_USER_CLASS = "com.ss.android.ugc.aweme.z.a.b";
        final static String MY_USER_CLASS_METHOD = "a";
        final static String MY_USER_METHOD = "b";
        final static String USER_CLASS = "com.ss.android.ugc.aweme.z.a";
        final static String USER_CLASS_METHOD = "a";
        final static String USER_METHOD = "c";
        final static String USER_LOGIN_FIELD = "d";

        public static Object getUserInfo(Hook hooks) throws Exception {
            return hooks.callMethod(hooks.callStaticMethod(MY_USER_CLASS, MY_USER_CLASS_METHOD), MY_USER_METHOD);
        }

        public static Object getUser(Hook hooks) throws Exception {
            Object user = hooks.callStaticMethod(USER_CLASS, USER_CLASS_METHOD);
            user = hooks.callMethod(user, USER_METHOD);
            return user;
        }

        public static Boolean isLogin(Hook hooks) throws Exception {
            Object user = hooks.callStaticMethod(USER_CLASS, USER_CLASS_METHOD);
            return hooks.getBooleanField(user, USER_LOGIN_FIELD);
        }

        public static Boolean isAcceptPrivatePolicy(Hook hooks, Object user) throws Exception {//接受隐私政策
            return hooks.getBooleanField(user, "acceptPrivatePolicy");
        }

        public static String getAccountRegion(Hook hooks, Object user) throws Exception {//帐户区域
            return (String) hooks.getField(user, "accountRegion");
        }

        public static Integer getAllowStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "allowStatus");
        }

        public static Long getAuthorityStatus(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "authorityStatus");
        }

        public static Object getAvatarLarger(Hook hooks, Object user) throws Exception {//UrlModel.class
            return hooks.getField(user, "avatarLarger");
        }

        public static Object getAvatarMedium(Hook hooks, Object user) throws Exception {//UrlModel.class
            return hooks.getField(user, "avatarMedium");
        }

        public static Object getAvatarThumb(Hook hooks, Object user) throws Exception {//UrlModel.class
            return hooks.getField(user, "avatarThumb");
        }

        public static Object getAvatarVideoUri(Hook hooks, Object user) throws Exception {//UrlModel.class
            return hooks.getField(user, "avatarVideoUri");
        }

        public static Integer getAwemeCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "awemeCount");
        }

        public static String getBindPhone(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "bindPhone");
        }

        public static String getBirthday(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "birthday");
        }

        public static String getLocation(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "city");
        }

        public static String getCity(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "cityName");
        }

        public static String getProvince(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "province");
        }

        public static String getCountry(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "country");
        }

        public static String getRegion(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "region");
        }

        public static String getCollegeName(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "collegeName");
        }

        public static Integer getCommentSetting(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "commentSetting");
        }

        public static Object getCommerceInfo(Hook hooks, Object user) throws Exception {//CommerceInfo.class
            return hooks.getField(user, "commerceInfo");
        }

        public static Integer getCommerceUserLevel(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "commerceUserLevel");
        }

        public static Integer getConstellation(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "constellation");
        }

        public static List<Object> getCoverUrls(Hook hooks, Object user) throws Exception {//List<UrlModel>
            return (List<Object>) hooks.getField(user, "coverUrls");
        }

        public static Long getCreateTime(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "createTime");
        }

        public static String getCustomVerify(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "customVerify");
        }

        public static String getDistrict(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "district");
        }

        public static Integer getDongtaiCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "dongtai_count");
        }

        public static Integer getDouPlusShareLocation(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "douPlusShareLocation");
        }

        public static Integer getDownloadSetting(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "downloadSetting");
        }

        public static Integer getDuetSetting(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "duetSetting");
        }

        public static String getEnrollYear(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "enrollYear");
        }

        public static String getEnterpriseVerifyReason(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "enterpriseVerifyReason");
        }

        public static Integer getFansCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "fansCount");
        }

        public static Integer getFavoritingCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "favoritingCount");
        }

        public static Long getFbExpireTime(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "fbExpireTime");
        }

        public static Integer getFollowStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "followStatus");
        }

        public static Integer getFollowerCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "followerCount");
        }

        public static List<Object> getFollowerDetailList(Hook hooks, Object user) throws Exception {//List<FollowerDetail>
            return (List<Object>) hooks.getField(user, "followerDetailList");
        }

        public static Integer getFollowerStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "followerStatus");
        }

        public static Integer getFollowingCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "followingCount");
        }

        public static String getGender(Hook hooks, Object user) throws Exception {
            Integer sex = hooks.getIntegerField(user, "gender");
            if (sex == 1) {
                return "M";
            } else if (sex == 2) {
                return "F";
            } else {
                return null;
            }
        }

        public static String getGoogleAccount(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "googleAccount");
        }

        public static Boolean isHasEmail(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasEmail");
        }

        public static Boolean isHasFacebookToken(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasFacebookToken");
        }

        public static Boolean isHasMedal(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasMedal");
        }

        public static Boolean isHasOrders(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasOrders");
        }

        public static Boolean isHasTwitterToken(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasTwitterToken");
        }

        public static Boolean isHasYoutubeToken(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hasYoutubeToken");
        }

        public static Boolean isHideCity(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hideCity");
        }

        public static Boolean isHideSearch(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "hideSearch");
        }

        public static String getInsId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "insId");
        }

        public static Boolean isAdFake(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isAdFake");
        }

        public static Boolean isBindedWeibo(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isBindedWeibo");
        }

        public static Boolean isBlock(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isBlock");
        }

        public static Boolean isCreater(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isCreater");
        }

        public static Boolean isDisciplineMember(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isDisciplineMember");
        }

        public static Boolean isFlowcardMember(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isFlowcardMember");
        }

        public static Boolean isNewRecommend(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isNewRecommend");
        }

        public static Boolean isPhoneBinded(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isPhoneBinded");
        }

        public static Integer getIsSyncToutiao(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "isSyncToutiao");
        }

        public static Boolean isVerified(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "isVerified");
        }

        public static String getIsoCountryCode(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "isoCountryCode");
        }

        public static String getLanguage(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "language");
        }

        public static Integer getLiveAgreement(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "liveAgreement");
        }

        public static Boolean isLiveCommerce(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "liveCommerce");
        }

        public static Integer getLoginPlatform(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "loginPlatform");
        }

        public static Integer getMAtType(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "mAtType");
        }

        public static Boolean isMIsGovMediaVip(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "mIsGovMediaVip");
        }

        public static Boolean isNeedRecommend(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "needRecommend");
        }

        public static Integer getNeiguangShield(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "neiguangShield");
        }

        public static String getNickname(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "nickname");
        }

        public static Object getOriginalMusician(Hook hooks, Object user) throws Exception {//c.class
            return hooks.getField(user, "originalMusician");
        }

        public static Object getPlatformInfos(Hook hooks, Object user) throws Exception {//PlatformInfo[].class
            return hooks.getField(user, "platformInfos");
        }

        public static Boolean isPreventDownload(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "preventDownload");
        }

        public static String getRecommendReason(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "recommendReason");
        }

        public static String getRecommendReasonRelation(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "recommendReasonRelation");
        }

        public static Integer getRegisterStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "registerStatus");
        }

        public static Long getRegisterTime(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "registerTime");
        }

        public static String getRequestId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "requestId");
        }

        public static Object getRoomCover(Hook hooks, Object user) throws Exception {//UrlModel.class
            return hooks.getField(user, "roomCover");
        }

        public static Long getRoomId(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "roomId");
        }

        public static String getRoomTypeTag(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "roomTypeTag");
        }

        public static String getSchoolName(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "schoolName");
        }

        public static String getSchoolPoiId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "schoolPoiId");
        }

        public static Integer getSchoolType(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "schoolType");
        }

        public static Boolean isSecret(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "secret");
        }

        public static Object getShareInfo(Hook hooks, Object user) throws Exception {//ShareInfo.class
            return hooks.getField(user, "shareInfo");
        }

        public static Integer getShieldCommentNotice(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "shieldCommentNotice");
        }

        public static Integer getShieldDiggNotice(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "shieldDiggNotice");
        }

        public static Integer getShieldFollowNotice(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "shieldFollowNotice");
        }

        public static String getShortId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "shortId");
        }

        public static Integer getShowGenderStrategy(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "showGenderStrategy");
        }

        public static Boolean isShowImageBubble(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "showImageBubble");
        }

        public static String getSignature(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "signature");
        }

        public static Boolean isStarUseNewDownload(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "starUseNewDownload");
        }

        public static Integer getStoryCount(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "storyCount");
        }

        public static Boolean isStoryOpen(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "storyOpen");
        }

        public static String getThirdName(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "thirdName");
        }

        public static Integer getTotalFavorited(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "totalFavorited");
        }

        public static Long getTwExpireTime(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "twExpireTime");
        }

        public static String getTwitterId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "twitterId");
        }

        public static String getTwitterName(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "twitterName");
        }

        public static String getUid(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "uid");
        }

        public static String getUniqueId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "uniqueId");
        }

        public static Long getUniqueIdModifyTime(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "unique_id_modify_time");
        }

        public static Boolean isUserCancelled(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "userCancelled");
        }

        public static Object getUserHonor(Hook hooks, Object user) throws Exception {//UserHonor.class
            return hooks.getField(user, "userHonor");
        }

        public static Integer getVerificationType(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "verificationType");
        }

        public static String getVerifyInfo(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "verifyInfo");
        }

        public static Integer getVerifyStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "verifyStatus");
        }

        public static Integer getWatchStatus(Hook hooks, Object user) throws Exception {
            return hooks.getIntegerField(user, "watchStatus");
        }

        public static String getWeiboName(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "weibo_name");
        }

        public static String getWeiboSchema(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "weibo_schema");
        }

        public static String getWeiboUrl(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "weibo_url");
        }

        public static String getWeiboVerify(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "weibo_verify");
        }

        public static Boolean isWithCommerceEntry(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "withCommerceEntry");
        }

        public static Boolean isWithDouEntry(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "withDouEntry");
        }

        public static Boolean isWithDouplusEntry(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "withDouplusEntry");
        }

        public static Boolean isWithFusionShopEntry(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "withFusionShopEntry");
        }

        public static Boolean isWithNewGoods(Hook hooks, Object user) throws Exception {
            return hooks.getBooleanField(user, "withNewGoods");
        }

        public static String getYoutubeChannelId(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "youtubeChannelId");
        }

        public static String getYoutubeChannelTitle(Hook hooks, Object user) throws Exception {
            return (String) hooks.getField(user, "youtubeChannelTitle");
        }

        public static Long getYoutubeExpireTimey(Hook hooks, Object user) throws Exception {
            return hooks.getLongField(user, "youtubeExpireTime");
        }

        //UrlModel
        public static Integer getHeight(Hook hooks, Object urlModel) throws Exception {
            return hooks.getIntegerField(urlModel, "height");
        }

        public static Integer getWidth(Hook hooks, Object urlModel) throws Exception {
            return hooks.getIntegerField(urlModel, "width");
        }

        public static String getUri(Hook hooks, Object urlModel) throws Exception {
            return (String) hooks.getField(urlModel, "uri");
        }

        public static List<String> getUrlList(Hook hooks, Object urlModel) throws Exception {
            return (List<String>) hooks.getField(urlModel, "urlList");
        }
    }
}