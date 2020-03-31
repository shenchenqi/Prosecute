package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.hook.AutoControlLayout;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.inner.execute.Deploy.controlLogger;

public class ControlPresenter<Interface extends ControlInter> extends AutoControlLayout {

    private Interface clazz;
    private Handler handler;

    public void setClass(Interface clazz) {
        if (clazz == null) {
            throw new NullPointerException("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    public Interface getClazz() {
        return clazz;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void onAttached() {
        handler = getHandler(getClazz().getIContext().getMainLooper());
    }

    public void executeView() {

    }

    private Object viewPager;

    public void setViewPager(Object viewPager) {
        this.viewPager = viewPager;
    }

    private View mainFragmentView;

    public void setMainFragmentView(View mainFragmentView) {
        this.mainFragmentView = mainFragmentView;
    }

    private void clickAttentionView(final Callback callback) {//com.ss.android.ugc.aweme:id/ewo 0x7f071e1d (2131172893)
        if (mainFragmentView == null) {
            callback.fail("关注 布局不存在");
            return;
        }
        handlerPost(handler, () -> {
            TextView attentionTv = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_ATTENTION_INTEGER);
            if (attentionTv != null) {
                attentionTv.performClick();
                callback.success("关注点击成功");
            } else {
                callback.fail("关注按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickLiveView(final Callback callback) {//com.ss.android.ugc.aweme:id/c_u 0x7f071022 (2131169314)
        if (mainFragmentView == null) {
            callback.fail("直播 布局不存在");
            return;
        }
        handlerPost(handler, () -> {
            LinearLayout liveBt = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_LIVE_INTEGER);
            if (liveBt != null) {
                liveBt.performClick();
                callback.success("直播点击成功");
            } else {
                callback.fail("直播按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickSearchView(final Callback callback) {//com.ss.android.ugc.aweme:id/avg 0x7f070890 (2131167376)
        if (mainFragmentView == null) {
            callback.fail("查询 布局不存在");
            return;
        }
        handlerPost(handler, () -> {
            LinearLayout searchBt = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_SEARCH_INTEGER);
            if (searchBt != null) {
                searchBt.performClick();
                callback.success("查询点击成功");
            } else {
                callback.fail("查询按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickRecommendView(final Callback callback) {//com.ss.android.ugc.aweme:id/ey2 0x7f071e50 (2131172944)
        if (mainFragmentView == null) {
            callback.fail("推荐 布局不存在");
            return;
        }
        handlerPost(handler, () -> {
            TextView recommendTv = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_RECOMMEND_INTEGER);
            if (recommendTv != null) {
                recommendTv.performClick();
                callback.success("推荐点击成功");
            } else {
                callback.fail("推荐按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickUserView(final Callback callback) {//com.ss.android.ugc.aweme:id/fbr 0x7f072070 (2131173488)
        if (mainFragmentView == null) {
            callback.fail("用户 布局不存在");
            return;
        }
        //是直播会跳直播
        handlerPost(handler, () -> {
            RelativeLayout userBt = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_USER_INTEGER);
            if (userBt != null) {
                userBt.performClick();
                callback.success("用户点击成功");
            } else {
                callback.fail("用户按钮未绑定");
            }
        }, callback.sleep());
    }

    private View profileFragmentView;

    public void setProfileFragmentView(View profileFragmentView) {
        this.profileFragmentView = profileFragmentView;
    }

    private void clickBackView(final Callback callback) {//com.ss.android.ugc.aweme:id/ko 0x7f0701ae (2131165614)
        if (profileFragmentView == null) {
            callback.fail("返回 布局不存在");
            return;
        }
        handlerPost(handler, () -> {
            ImageView backBt = bindView(profileFragmentView, TremoloParam.USER_BACK_INTEGER);
            if (backBt != null) {
                backBt.performClick();
                callback.success("返回点击成功");
            } else {
                callback.fail("返回按钮未绑定");
            }
        }, callback.sleep());
    }
}