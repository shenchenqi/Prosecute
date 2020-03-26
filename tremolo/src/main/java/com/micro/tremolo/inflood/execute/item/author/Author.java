package com.micro.tremolo.inflood.execute.item.author;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.execute.replace.Aweme;
import com.micro.tremolo.inflood.execute.replace.AwemeStatistics;
import com.micro.tremolo.inflood.execute.replace.UrlModel;
import com.micro.tremolo.inflood.execute.replace.User;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:25
 */
public class Author extends Plugin<AuthorPresenter, AuthorInter> implements AuthorInter {

    public Author(Hook hook, Context context) throws Throwable {
        super(hook, context);
        logger.i("博主初始化");
    }

    @Override
    protected AuthorPresenter getPresenter() {
        return new AuthorPresenter();
    }

    @Override
    public void monitor(Hook hook) {

    }

    @Override
    protected void execute() {
        monitor(getHook());
    }

    public void test() {
        getHook().methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ae = param.getArgs()[0];//"com.ss.android.ugc.aweme.feed.e.ae"
                Object aweme1 = getHook().getField(ae, TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                Aweme aweme = new Aweme(getHook(), aweme1);
                AwemeStatistics statistics = aweme.getStatistics();
                logger.i(String.format("当前视频信息：{视频Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s]}",
                        aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                        statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount()));
                User author = aweme.getAuthor();
                logger.d(String.format("用户信息：{用户Id[%s], 昵称[%s], 抖音号[%s > %s], 生日[%s], 城市[%s]，国籍[%s]，地区[%s]}",
                        author.getUid(), author.getNickname(), author.getUniqueId(), author.getShortId(), author.getBirthday(), author.getCity(), author.getCountry(), author.getDistrict()));
                logger.d(String.format("签名[%s], 官方认证[%s]，企业认证[%s]，请求ID[%s]}",
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
        }, getHook().findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }
}