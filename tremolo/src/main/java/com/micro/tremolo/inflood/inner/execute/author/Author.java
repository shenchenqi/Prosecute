package com.micro.tremolo.inflood.inner.execute.author;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.replace.FollowerDetail;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.List;

import static com.micro.tremolo.inflood.inner.execute.Deploy.logger;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:25
 */
public class Author extends Plugin<AuthorPresenter, AuthorInter> implements AuthorInter {

    public Author(Hook hook, Context context) throws Throwable {
        super(hook, context);
        logger.i("作者初始化");
    }

    @Override
    protected AuthorPresenter getPresenter() {
        return new AuthorPresenter();
    }

    @Override
    public void monitor(final Hook hook) {
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    logger.e("当前工厂未实例");
                    return;
                }
                presenter.setAuthorInfo(param.getArgs()[0]);
                presenter.obtainAuthor(new User(hook, presenter.getAuthorInfo()));
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
    }

    @Override
    protected String taskName() {
        return Author.class.getSimpleName();
    }

    @Override
    protected void process() {

    }

    @Override
    protected void error(Throwable throwable) {

    }

    @Override
    public void finish(boolean success) {
        logger.i(String.format("作者任务[%s]", success));
    }

    public static void loadStaticUser(User user) {
        logger.d(String.format("用户信息：{用户Id[%s], 昵称[%s], 抖音号[%s > %s], 生日[%s], 城市[%s]，国籍[%s]，地区[%s]}",
                user.getUid(), user.getNickname(), user.getUniqueId(), user.getShortId(), user.getBirthday(), user.getCity(), user.getCountry(), user.getDistrict()));
        logger.d(String.format("用户信息：{签名[%s], 官方认证[%s]，企业认证[%s]，请求ID[%s]}",
                user.getSignature(), user.getCustomVerify(), user.getEnterpriseVerifyReason(), user.getRequestId()));
        logger.d(String.format("用户统计数：{关注[%s], [%s], 喜欢[%s], [%s], [%s], 作品[%s], 粉丝[%s], [%s], [%s], [%s], 动态[%s]}",
                user.getFollowingCount(), user.getFollowerCount(), user.getFavoritingCount(),
                user.getStoryCount(), user.getCollectCount(), user.getAwemeCount(),
                user.getFansCount(), user.getPrivateAwemeCount(), user.getUserStoryCount(),
                user.getXmasUnlockCount(), user.getDongtai_count()));
        UrlModel avatarLarger = user.getAvatarLarger();
        if (avatarLarger != null) {
            logger.d(String.format("用户头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                    avatarLarger.getHeight(), avatarLarger.getWidth(), avatarLarger.getUri(), avatarLarger.getUrlKey(), JSON.toJSONString(avatarLarger.getUrlList())));
        }
        UrlModel avatarMedium = user.getAvatarMedium();
        if (avatarMedium != null) {
            logger.d(String.format("用户中等头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                    avatarMedium.getHeight(), avatarMedium.getWidth(), avatarMedium.getUri(), avatarMedium.getUrlKey(), JSON.toJSONString(avatarMedium.getUrlList())));
        }
        UrlModel avatarThumb = user.getAvatarThumb();
        if (avatarThumb != null) {
            logger.d(String.format("用户缩略头像：{高[%s], 宽[%s], uri[%s], urlKey[%s], 网址列表[%s]}",
                    avatarThumb.getHeight(), avatarThumb.getWidth(), avatarThumb.getUri(), avatarThumb.getUrlKey(), JSON.toJSONString(avatarThumb.getUrlList())));
        }
    }

    @Override
    public void loadUser(User user) {
        presenter.saveUserTableItem(user);
    }
}