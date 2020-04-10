package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import android.view.View;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.TremoloApi;
import com.micro.tremolo.inflood.inner.execute.control.MainActivityControl;
import com.micro.tremolo.inflood.inner.execute.control.MainFragmentControl;
import com.micro.tremolo.inflood.inner.execute.control.ProfileFragmentControl;
import com.micro.tremolo.inflood.inner.execute.task.WideAreaAuthorApi;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.User;

import java.util.List;

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

    private TremoloApi tremoloApi;
    private AutoUiControl autoUiControl;
    private MainActivityControl mainActivityControl;
    private MainFragmentControl mainFragmentControl;
    private ProfileFragmentControl profileFragmentControl;

    public void initCreate() {
        if (tremoloApi == null) {
            tremoloApi = new TremoloApi(hook, getContext());
        }
        if (Const.isAutoUI) {
            if (autoUiControl == null) {
                autoUiControl = new AutoUiControl(hook, getContext());
            }
        } else if (Const.isWideArea) {
            if (mainActivityControl == null) {
                mainActivityControl = MainActivityControl.getInstance(getContext(), hook);
            }
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext());
            }
            if (profileFragmentControl == null) {
                profileFragmentControl = ProfileFragmentControl.getInstance(getContext());
            }
        } else if (Const.isNarrowArea) {
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext());
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
            mainFragmentControl.setMainFragmentView(mainFragmentView);
        }
    }

    public void setProfileFragmentView(View profileFragmentView) {
        if (autoUiControl != null) {
            autoUiControl.setProfileFragmentView(profileFragmentView);
        } else if (profileFragmentControl != null) {
            profileFragmentControl.setProfileFragmentView(profileFragmentView);
        }
    }

    public void setData(String authorId, String secAuthorId) {
        if (WideAreaAuthorApi.userMap.containsKey(authorId)) {
            monitorLogger.d(String.format("抖音用户已存在 [%s, %s]", authorId, secAuthorId));
        } else {
            WideAreaAuthorApi.userMap.put(authorId, secAuthorId);
        }
    }

    public void nextVideo() {
        if (autoUiControl != null) {
            autoUiControl.autoChangeVideo();
        } else if (Const.isWideArea) {
            mainFragmentControl.clickRecommend();
        }
    }

    private int fansCount;

    public void setRead(int fansCount) {
        this.fansCount = fansCount;
        if (autoUiControl == null) {
            autoUiControl.setRead(fansCount);
        }
    }

    public boolean isRead() {
        return fansCount > Const.fansCount;
    }

    private int videoCount;

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public void autoMoveUser() {
        if (autoUiControl != null) {
            autoUiControl.autoMoveUser();
        }
    }

    public void setVideosSize(int size) {
        if (autoUiControl != null) {
            autoUiControl.setVideosSize(size);
        }
    }

    public void userMoreVideo() {
        if (autoUiControl != null) {
            autoUiControl.autoLoadMoreVideo(videoCount);
        }
    }

    public void obtainUser(User user) {
        if (autoUiControl != null) {
            autoUiControl.obtainUser(user);
        }
    }

    public void obtainVideoList(List<Aweme> awemeList) {
        if (autoUiControl != null) {
            autoUiControl.obtainVideoList(awemeList);
        }
    }
}