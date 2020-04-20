package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import android.view.View;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.control.MainActivityControl;
import com.micro.tremolo.inflood.inner.execute.control.MainFragmentControl;
import com.micro.tremolo.inflood.inner.execute.control.ProfileFragmentControl;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.User;

import java.util.List;

import static com.micro.tremolo.Const.controlLogger;
import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:08
 */
public class OverseePresenter extends PluginPresenter<OverseeInter> {
    private static OverseePresenter mOverseePresenter;

    public static OverseePresenter getInstance() {
        if (mOverseePresenter == null) {
            mOverseePresenter = new OverseePresenter();
        }
        return mOverseePresenter;
    }

    @Override
    public void onAttached() {
    }

    private Hook hook;

    public void setHook(Hook hook) {
        this.hook = hook;
    }

    private AutoUiControl autoUiControl;
    private MainActivityControl mainActivityControl;
    private MainFragmentControl mainFragmentControl;
    private ProfileFragmentControl profileFragmentControl;

    public void initCreate() {
        if (Const.isAutoUI) {
            if (autoUiControl == null) {
                autoUiControl = new AutoUiControl(hook, getContext());
            }
        } else if (Const.isWideArea) {
            if (mainActivityControl == null) {
                mainActivityControl = MainActivityControl.getInstance(getContext(), hook);
            }
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext(), hook);
            }
            if (profileFragmentControl == null) {
                profileFragmentControl = ProfileFragmentControl.getInstance(getContext(), hook);
            }
        } else if (Const.isNarrowArea) {
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext(), hook);
            }
        } else {
            monitorLogger.d("不控制任何");
        }
    }

    public void setMainActivity(Object mainActivity) {
        if (autoUiControl != null) {
            autoUiControl.setMainActivity(mainActivity);
        } else if (mainActivityControl != null) {
            mainActivityControl.setMainActivity(mainActivity);
        }
    }

    public void setMainFragmentView(View mainFragmentView) {
        if (autoUiControl != null) {
            autoUiControl.setMainFragmentView(mainFragmentView);
        } else if (mainFragmentControl != null) {
            mainFragmentControl.setView(mainFragmentView);
        }
    }

    public void setProfileFragmentView(View profileFragmentView) {
        if (autoUiControl != null) {
            autoUiControl.setProfileFragmentView(profileFragmentView);
        } else if (profileFragmentControl != null) {
            profileFragmentControl.setView(profileFragmentView);
        }
    }

    public void nextVideo() {
        if (autoUiControl != null) {
            autoUiControl.autoChangeVideo();
        } else if (Const.isWideArea) {
            mainFragmentControl.clickRecommend();
        }
    }

    public void setRead(int fansCount) {
        if (autoUiControl == null) {
            autoUiControl.setRead(fansCount);
        }
    }

    private int videoCount;

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public void setVideosSize(int size) {
        if (autoUiControl != null) {
            autoUiControl.setVideosSize(size);
        }
    }

    public void setUIStatus(int type, User user, List<Aweme> awemeList) {
        if (!Const.isAutoUI) {
            controlLogger.d("当前不是控制UI模式");
            return;
        }
        if (Lang.isEquals(0, type)) {
            autoMoveUser();
            setStatus(1);
        } else if (Lang.isEquals(1, type)) {
            if (Lang.isNotNull(user)) {
                obtainUser(user);
            }
            userMoreVideo();
            setStatus(2);
        } else if (Lang.isEquals(2, type)) {
            if (Lang.isNotNull(awemeList)) {
                obtainVideoList(awemeList);
            }
            setStatus(3);
        }
    }

    private void autoMoveUser() {
        if (autoUiControl != null) {
            autoUiControl.autoMoveUser();
        }
    }

    private void userMoreVideo() {
        if (autoUiControl != null) {
            autoUiControl.autoLoadMoreVideo(videoCount);
        }
    }

    private void obtainUser(User user) {
        if (autoUiControl != null) {
            autoUiControl.obtainUser(user);
        }
    }

    private void obtainVideoList(List<Aweme> awemeList) {
        if (autoUiControl != null) {
            autoUiControl.obtainVideoList(awemeList);
        }
    }

    private void setStatus(int status) {
        if (autoUiControl != null) {
            autoUiControl.setStatus(status);
        }
    }

    public void statusMonitor() {
        if (autoUiControl != null) {
            autoUiControl.statusMonitor();
        }
    }
}