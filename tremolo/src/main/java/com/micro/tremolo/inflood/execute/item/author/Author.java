package com.micro.tremolo.inflood.execute.item.author;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.execute.item.Aweme;
import com.micro.tremolo.inflood.execute.item.User;

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
        getHook().methodMonitor("com.ss.android.ugc.aweme.feed.model.Aweme", "update", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                logger.d("更新:" + getHook().callMethod(param.getArgs()[0], "toString").toString());
            }
        }, getHook().findClass("com.ss.android.ugc.aweme.feed.model.Aweme"));
        getHook().methodMonitor("com.ss.android.ugc.aweme.main.MainActivity", "onVideoPageChangeEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ae = param.getArgs()[0];//"com.ss.android.ugc.aweme.feed.e.ae"
                Aweme aweme = new Aweme(getHook(), getHook().getField(ae, "a"));
                Object author = aweme.getAuthor();
                User user = new User(getHook(), author);
                logger.d(String.format("用户信息：{昵称[%s], 抖音号[%s > %s], 签名[%s], 官方认证[%s]，企业认证[%s]，获赞数[%s]，关注数[%s]，粉丝数[%s]}",
                        user.getNickname(), user.getUniqueId(), user.getShortId(), user.getSignature(), user.getCustomVerify(), user.getEnterpriseVerifyReason(),
                        user.getFavoritingCount(), user.getFollowerCount(), user.getFansCount()));
            }
        }, getHook().findClass("com.ss.android.ugc.aweme.feed.e.ae"));
    }
}