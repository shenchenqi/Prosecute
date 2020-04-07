package com.micro.tremolo.inflood.inner.execute.monitor.profile_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.AutoUiControl;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 13:39
 */
public class ProfileFragmentOversee extends Plugin<ProfileFragmentPresenter, ProfileFragmentInter> implements ProfileFragmentInter {

    private final AutoUiControl autoUiControl;

    public ProfileFragmentOversee(Hook hook, Context context, AutoUiControl autoUiControl) throws Throwable {
        super(hook, context);
        this.autoUiControl = autoUiControl;
    }

    @Override
    protected ProfileFragmentPresenter getPresenter() {
        return new ProfileFragmentPresenter();
    }

    @Override
    public void monitor() {
        String profileFragment = TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS;
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment onViewCreated");
                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                View view = (View) param.getArgs()[0];
                autoUiControl.setProfileFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(profileFragment, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment a_(User)");
                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                autoUiControl.setUserProfile(true);
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

                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                autoUiControl.setRead(user.getFansCount());
                autoUiControl.autoLoadMoreVideo(user.getAwemeCount());

                if (autoUiControl.isRead()) {
                    if (presenter == null) {
                        monitorLogger.e("当前工厂 未实例");
                        return;
                    }
                    presenter.obtainUser(user);
                }
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_VIDEO_CALL_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("作者视频接口");
                List<Aweme> awemeList = new ArrayList<>();
                for (Object object : (List<Object>) param.getResult()) {
                    awemeList.add(new Aweme(hook, object));
                }
                monitorLogger.i("Video List " + awemeList.size());

                if (presenter == null) {
                    monitorLogger.e("当前工厂 未实例");
                    return;
                }
                presenter.obtainVideoList(awemeList);

                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                autoUiControl.setVideoCount(awemeList.size());
            }
        });
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }

    @Override
    public void profileInfo(Author author) {

    }

    @Override
    public void videoListInfo(List<Video> videos) {

    }

    @Override
    public void error() {

    }
}