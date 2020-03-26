package com.micro.tremolo.inflood.inner;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.version.TremoloParam;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:34
 */
public class TestHook {

    private static final Logger logger = Logger.getLogger("Test-Log");

    public static void testMainFragment(final Hook hook) {
        hook.methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ae = param.getArgs()[0];//"com.ss.android.ugc.aweme.feed.e.ae"
                Object aweme1 = hook.getField(ae, TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                Aweme aweme = new Aweme(hook, aweme1);
                AwemeStatistics statistics = aweme.getStatistics();
                logger.i(String.format("当前视频信息：{视频Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s]}",
                        aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                        statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount()));
                User author = aweme.getAuthor();
                logger.d(String.format("用户信息：{用户Id[%s], 昵称[%s], 抖音号[%s > %s], 生日[%s], 城市[%s]，国籍[%s]，地区[%s]}",
                        author.getUid(), author.getNickname(), author.getUniqueId(), author.getShortId(), author.getBirthday(), author.getCity(), author.getCountry(), author.getDistrict()));
                logger.d(String.format("用户信息：{签名[%s], 官方认证[%s]，企业认证[%s]，请求ID[%s]}",
                        author.getSignature(), author.getCustomVerify(), author.getEnterpriseVerifyReason(), author.getRequestId()));
                UrlModel avatarLarger = author.getAvatarLarger();
                logger.d(String.format("用户头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarLarger.getHeight(), avatarLarger.getWidth(), avatarLarger.getUri(), avatarLarger.getUrlKey(), JSON.toJSONString(avatarLarger.getUrlList())));
                UrlModel avatarMedium = author.getAvatarMedium();
                logger.d(String.format("用户中等头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarMedium.getHeight(), avatarMedium.getWidth(), avatarMedium.getUri(), avatarMedium.getUrlKey(), JSON.toJSONString(avatarMedium.getUrlList())));
                UrlModel avatarThumb = author.getAvatarThumb();
                logger.d(String.format("用户缩略头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarThumb.getHeight(), avatarThumb.getWidth(), avatarThumb.getUri(), avatarThumb.getUrlKey(), JSON.toJSONString(avatarThumb.getUrlList())));
                logger.d(String.format("用户统计数：{[%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s]}",
                        author.getFollowingCount(), author.getFollowerCount(), author.getFavoritingCount(),
                        author.getStoryCount(), author.getCollectCount(), author.getAwemeCount(),
                        author.getFansCount(), author.getPrivateAwemeCount(), author.getUserStoryCount(),
                        author.getXmasUnlockCount(), author.getDongtai_count()));
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }

    public static void testProfileApi(final Hook hook) {
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.api.ProfileApi", "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("%s - %s", JSON.toJSONString(param.getArgs()), param.getResult()));
            }
        }, String.class, String.class, String.class, int.class);
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.api.ProfileApi", "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("%s - %s", JSON.toJSONString(param.getArgs()), param.getResult()));
            }
        }, boolean.class);
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.api.ProfileApi", "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d("api: " + JSON.toJSONString(param.getArgs()));
                User author = new User(hook, param.getResult());
                logger.d(String.format("用户信息：{用户Id[%s], 昵称[%s], 抖音号[%s > %s], 生日[%s], 城市[%s]，国籍[%s]，地区[%s]}",
                        author.getUid(), author.getNickname(), author.getUniqueId(), author.getShortId(), author.getBirthday(), author.getCity(), author.getCountry(), author.getDistrict()));
                logger.d(String.format("用户信息：{签名[%s], 官方认证[%s]，企业认证[%s]，请求ID[%s]}",
                        author.getSignature(), author.getCustomVerify(), author.getEnterpriseVerifyReason(), author.getRequestId()));
                UrlModel avatarLarger = author.getAvatarLarger();
                logger.d(String.format("用户头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarLarger.getHeight(), avatarLarger.getWidth(), avatarLarger.getUri(), avatarLarger.getUrlKey(), JSON.toJSONString(avatarLarger.getUrlList())));
                UrlModel avatarMedium = author.getAvatarMedium();
                logger.d(String.format("用户中等头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarMedium.getHeight(), avatarMedium.getWidth(), avatarMedium.getUri(), avatarMedium.getUrlKey(), JSON.toJSONString(avatarMedium.getUrlList())));
                UrlModel avatarThumb = author.getAvatarThumb();
                logger.d(String.format("用户缩略头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                        avatarThumb.getHeight(), avatarThumb.getWidth(), avatarThumb.getUri(), avatarThumb.getUrlKey(), JSON.toJSONString(avatarThumb.getUrlList())));
                logger.d(String.format("用户统计数：{[%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s]}",
                        author.getFollowingCount(), author.getFollowerCount(), author.getFavoritingCount(),
                        author.getStoryCount(), author.getCollectCount(), author.getAwemeCount(),
                        author.getFansCount(), author.getPrivateAwemeCount(), author.getUserStoryCount(),
                        author.getXmasUnlockCount(), author.getDongtai_count()));
            }
        }, String.class, boolean.class, String.class);
    }
}