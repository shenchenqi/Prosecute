package com.micro.tremolo.inflood;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.setup.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.inflood.inner.execute.account.Account;
import com.micro.tremolo.inflood.inner.execute.author.Author;
import com.micro.tremolo.inflood.inner.logcat.Logcat;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.mvp.EntranceInter;
import com.micro.tremolo.inflood.mvp.EntrancePresenter;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:23
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "DY-Entrance";

    private static Entrance mEntrance;

    public static Entrance getInstance(HookParam hookParam) {
        if (mEntrance == null) {
            try {
                logger.i(TAG, "步骤事件: " + hookParam.getVersion());
                mEntrance = new Entrance(hookParam);
                mEntrance.init();
            } catch (Throwable throwable) {
                logger.e(throwable, TAG, "步骤事件失败");
            }
        }
        return mEntrance;
    }

    private Entrance(HookParam hookParam) throws Throwable {
        super(hookParam);
    }

    @Override
    protected EntrancePresenter getPresenter() {
        return new EntrancePresenter();
    }

    @Override
    protected void log() {
        getHookParam().getHook().methodMonitor("com.ss.android.agilelogger.ALog", "println", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                new Logcat(getHookParam().getHook(), param.getArgs()[0]);
            }
        }, getHookParam().getHook().findClass("com.ss.android.agilelogger.d"));
    }

    @Override
    public void initParam(String version) {
        logger.i(TAG, "initParam", "参数初始化： " + version);
        TremoloParam.init(version);
    }

    @Override
    public void hide() {
        logger.i(TAG, "hide", "隐藏");
    }

    @Override
    public void executeSQL() {
        logger.i(TAG, "executeSQL", "数据库");
    }

    private Account account;
    private Author author;

    @Override
    public void config() {
        logger.i(TAG, "config", "配置");
        try {
            account = new Account(getHookParam().getHook(), getHookParam().getApplication());
            author = new Author(getHookParam().getHook(), getHookParam().getApplication());
        } catch (Throwable throwable) {
            logger.e(throwable, "配置报错");
        }
    }

    @Override
    protected void test() {
        getHookParam().getHook().methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ae = param.getArgs()[0];//"com.ss.android.ugc.aweme.feed.e.ae"
                Object aweme1 = getHookParam().getHook().getField(ae, TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                Aweme aweme = new Aweme(getHookParam().getHook(), aweme1);
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
        }, getHookParam().getHook().findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }

    @Override
    public void execute() {
        if (account != null) {

        }
        if (author != null) {

        }
    }
}