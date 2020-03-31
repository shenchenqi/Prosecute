package com.micro.tremolo.inflood.inner;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.inner.execute.monitor.author.Author;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;

import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:34
 */
public class TestHook {

    private static final Logger logger = Logger.getLogger("Test-Log");

    public static void testMain(final Hook hook) {
       /* hook.classMonitor("com.ss.android.ugc.aweme.homepage.ui.d", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.i(JSON.toJSONString(param.getArgs()));
            }
        }, hook.findClass("com.bytedance.ies.uikit.base.AbsFragment"), Context.class, hook.findClass("android.support.v4.app.FragmentManager"), hook.findClass("com.ss.android.ugc.aweme.homepage.ui.a.a"));*/
        /*hook.methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD, new ForeignHook() {
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
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));*/
    }

    public static void testApi(final Hook hook) {
        hook.classMonitor("com.ss.android.ugc.aweme.feed.model.FeedItemList", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("FeedItemList[%s]", JSON.toJSONString(param.getThisObject())));
            }
        });
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.viewmodel.MediaMixListViewModel.a", "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("请求[%s]", JSON.toJSONString(param.getArgs())));
                logger.d(String.format("返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        }, String.class, String.class, int.class, long.class);
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.ProfileServiceImpl.a", "call", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object object = param.getThisObject();
                String b = (String) hook.getField(object, "b");
                String c = (String) hook.getField(object, "c");
                String d = (String) hook.getField(object, "d");
                logger.d(String.format("%s,%s,%s", b, c, d));
            }
        });
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.api.ProfileApi", "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("%s - %s", JSON.toJSONString(param.getArgs()), param.getResult()));
            }
        }, String.class, String.class, int.class);
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
        hook.methodMonitor("com.ss.android.ugc.aweme.common.a", "sendRequest", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("sendRequest : %s", JSON.toJSONString(param.getArgs())));
            }
        }, Object[].class);
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.presenter.b", "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d(String.format("presenter.b : %s", JSON.toJSONString(param.getArgs())));
            }
        }, boolean.class, String.class, int.class, long.class, int.class, String.class);
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.presenter.b", "getItems", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                List<Object> list = (List<Object>) param.getResult();
                logger.d(String.format("presenter.b list : %s", list != null));
                if (list != null) {
                    for (Object object : list) {
                        Aweme aweme = new Aweme(hook, object);
                        AwemeStatistics statistics = aweme.getStatistics();
                        logger.i(String.format("当前视频信息：{视频Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s]}",
                                aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                                statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount()));
                    }
                }
            }
        });
    }

    public static void testUser(final Hook hook) {
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.presenter.ai", "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object b = hook.getField(param.getThisObject(), "b");
                Object d = hook.getField(param.getThisObject(), "d");
                Object e = hook.getField(param.getThisObject(), "e");
                int f = hook.getIntegerField(param.getThisObject(), "f");
                logger.d(String.format("准备的数据： %s, %s, %s, %s", b, d, e, f));
                Object h = hook.getField(param.getThisObject(), "h");
                Object data = hook.callMethod(h, "getData");
                Object getUser = JSON.toJSONString(hook.callMethod(data, "getUser"));
                logger.d(JSON.toJSONString(getUser));
            }
        });
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.ui.UserProfileFragment", "onAntiCrawlerEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d("Crawler >>>>" + JSON.toJSONString(param.getArgs()));
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.a.a"));
        hook.methodMonitor("com.ss.android.ugc.aweme.profile.ui.UserProfileFragment", "onMobRequestIdEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d("MobRequest >>>>" + JSON.toJSONString(param.getArgs()));
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.z"));
    }
}