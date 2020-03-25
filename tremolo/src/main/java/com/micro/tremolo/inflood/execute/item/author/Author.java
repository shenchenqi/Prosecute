package com.micro.tremolo.inflood.execute.item.author;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.execute.replace.Aweme;
import com.micro.tremolo.inflood.execute.replace.AwemeStatistics;
import com.micro.tremolo.inflood.execute.replace.User;

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
        Aweme.update(getHook(), new Aweme.Callback() {
            @Override
            public void loadData(Aweme aweme, String msg) {
                /*logger.i("toString", String.format("视频信息：%s", msg));
                AwemeStatistics statistics = aweme.getStatistics();
                logger.i(String.format("更新 视频信息：{Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s], 前进数[%s]}",
                        aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                        statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount(), statistics.getForwardCount()));
                User author = aweme.getAuthor();
                logger.i("author", String.format("更新 用户信息 All：%s", JSON.toJSONString(author)));
                logger.d(String.format("更新 用户信息：{id[%s], 昵称[%s], 抖音号[%s > %s], 签名[%s], 官方认证[%s]，企业认证[%s]}",
                        author.getUid(), author.getNickname(), author.getUniqueId(), author.getShortId(), author.getSignature(), author.getCustomVerify(), author.getEnterpriseVerifyReason()));
                logger.d(String.format("更新 用户数据：{[%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s]}",
                        author.getFollowingCount(), author.getFollowerCount(), author.getFavoritingCount(),
                        author.getStoryCount(), author.getCollectCount(), author.getAwemeCount(),
                        author.getFansCount(), author.getPrivateAwemeCount(), author.getUserStoryCount(),
                        author.getXmasUnlockCount(), author.getDongtai_count()));*/
            }
        });
        /*getHook().methodMonitor("com.ss.android.ugc.aweme.feed.model.Aweme", "update", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d("更新:" + getHook().callMethod(param.getArgs()[0], "toString").toString());
            }
        }, getHook().findClass("com.ss.android.ugc.aweme.feed.model.Aweme"));*/
        getHook().methodMonitor("com.ss.android.ugc.aweme.main.MainActivity", "onVideoPageChangeEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ae = param.getArgs()[0];//"com.ss.android.ugc.aweme.feed.e.ae"
                Object aweme1 = getHook().getField(ae, "a");
                Aweme aweme = new Aweme(getHook(), aweme1);
                /*AwemeStatistics statistics = aweme.getStatistics();
                logger.i(String.format("视频信息：{Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], " +
                                "评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s], 前进数[%s]}",
                        aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                        statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount(), statistics.getForwardCount()));
                User author = aweme.getAuthor();
                logger.i("author", String.format("用户信息 All：%s", JSON.toJSONString(author)));
                logger.d(String.format("用户信息：{id[%s], 昵称[%s], 抖音号[%s > %s], 签名[%s], 官方认证[%s]，企业认证[%s]}",
                        author.getUid(), author.getNickname(), author.getUniqueId(), author.getShortId(), author.getSignature(), author.getCustomVerify(), author.getEnterpriseVerifyReason()));
                logger.d(String.format("用户数据：{[%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s], [%s]}",
                        author.getFollowingCount(), author.getFollowerCount(), author.getFavoritingCount(),
                        author.getStoryCount(), author.getCollectCount(), author.getAwemeCount(),
                        author.getFansCount(), author.getPrivateAwemeCount(), author.getUserStoryCount(),
                        author.getXmasUnlockCount(), author.getDongtai_count()));*/
            }
        }, getHook().findClass("com.ss.android.ugc.aweme.feed.e.ae"));
    }
}