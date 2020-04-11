package com.micro.tremolo.inflood.inner.execute.monitor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.Oversee;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 13:39
 */
public class ProfileFragmentOversee extends Oversee {

    public ProfileFragmentOversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    public void monitor() {
        String profileFragment = TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS;
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment onViewCreated");
                presenter.setProfileFragmentView((View) param.getArgs()[0]);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_VIDEO_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment a(Aweme)");
                final Aweme aweme = new Aweme(hook, param.getArgs()[0]);
                presenter.setData(aweme.getAuthor().getUid(), aweme.getAuthor().getSecUid());
                presenter.setUIStatus(0, null, null);
            }
        }, TremoloParam.AWEME_FEED_MODEL_AWEME_CLASS);
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment a_(User)");
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_TO_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment g(boolean) 返回的值： " + param.getArgs()[0]);
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_SHOW_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment h(User)");
                User user = new User(hook, param.getArgs()[0]);
                presenter.setRead(user.getFansCount());
                presenter.setVideoCount(user.getAwemeCount());
                presenter.setUIStatus(1, user, null);
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_VIDEO_CALL_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                List<Aweme> awemeList = new ArrayList<>();
                for (Object object : (List<Object>) param.getResult()) {
                    awemeList.add(new Aweme(hook, object));
                }
                presenter.setVideosSize(awemeList.size());
                presenter.setUIStatus(2, null, awemeList);
            }
        });
    }
}