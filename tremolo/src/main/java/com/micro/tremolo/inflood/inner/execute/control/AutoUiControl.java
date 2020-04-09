package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.view.View;

import com.micro.hook.ControlLayout;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.InspectApply;
import com.micro.tremolo.Const;

/**
 * 自动控制布局
 */
public class AutoUiControl extends ControlLayout implements BaseInterface {

    private Logger logger = com.micro.root.Logger.getLogger("tremoloLog", "ControlLog");

    private final Context context;

    private final MainActivityControl mainActivityControl;
    private final MainFragmentControl mainFragmentControl;
    private final ProfileFragmentControl profileFragmentControl;

    public AutoUiControl(Hook hook, Context context) {
        this.context = context;
        this.mainActivityControl = MainActivityControl.getInstance(context, hook);
        this.mainFragmentControl = MainFragmentControl.getInstance(context, new MainFragmentControl.MainCallback() {
            @Override
            public void success(String value, int type) {
                logger.d(String.format("MainFragment[%s][%s]", type, value));
            }

            @Override
            public void fail(String value, int type) {
                logger.e(String.format("MainFragment[%s][%s]", type, value));
            }
        });
        this.profileFragmentControl = ProfileFragmentControl.getInstance(context, new ProfileFragmentControl.ProfileCallback() {
            @Override
            public void success(String value, int type) {
                logger.d(String.format("ProfileFragment[%s][%s]", type, value));
                if (type == ProfileFragmentControl.uiLoadMoreVideo) {
                    setCount++;
                    logger.d(String.format("加载更多抖音号视频： [%s][%s], {[%s][%s][%s][%s]}", type, value, isRead, videoSize, count, setCount));
                    if (isRead && videoSize < count && setCount < (count / 10)) {
                        profileFragmentControl.loadMoreVideo();
                    } else {
                        profileFragmentControl.moveVideo();
                    }
                } else if (type == ProfileFragmentControl.uiMoveVideo){
                    mainFragmentControl.uiChangeVideoTop();
                }
            }

            @Override
            public void fail(String value, int type) {
                logger.e(String.format("ProfileFragment[%s][%s]", type, value));
            }
        });
    }

    private int count;
    private int setCount;

    private int videoSize;

    public void setVideosSize(int videoSize) {
        this.videoSize = videoSize;
    }

    private boolean isRead;

    public void setRead(int fansCount) {
        isRead = fansCount > Const.fansCount;
    }

    public void setMainActivity(Object mainActivity) {
        mainActivityControl.setMainActivity(mainActivity);
    }

    public void setMainFragmentView(View view) {
        mainFragmentControl.setMainFragmentView(view);
    }

    public void setProfileFragmentView(View view) {
        profileFragmentControl.setProfileFragmentView(view);
    }

    public void autoMoveUser() {
        mainFragmentControl.uiMoveUser();
    }

    public void autoChangeVideo() {
        mainFragmentControl.uiChangeVideoTop();
    }

    public void autoLoadMoreVideo(int count) {
        this.count = count;
        this.setCount = 0;
        profileFragmentControl.loadMoreVideo();
    }

    @Override
    public Context getIContext() {
        return context;
    }

    public static int isAppOnForeground(Context context) {
        return InspectApply.isAppOnForeground(context, Const.PACKAGE_NAME);
    }

    public static void openApply(Context context) {
        InspectApply.openApply(context, Const.PACKAGE_NAME);
    }

    public static void setTopApply(Context context) {
        InspectApply.setBackstageToFrontDesk(context, Const.PACKAGE_NAME);
    }
}
