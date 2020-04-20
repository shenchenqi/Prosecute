package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.util.Pair;

import com.micro.hook.BaseControl;
import com.micro.hook.ControlLayout;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.controlLogger;

/**
 * @Author KiLin
 * @Time 2020/4/9 9:36
 */
public class MainFragmentControl extends BaseControl {

    private final static int clickAttention = 994;
    private final static int clickLive = 995;
    private final static int clickRefresh = 996;
    private final static int clickSearch = 997;
    private final static int clickRecommend = 998;
    private final static int clickUser = 999;
    private final static int uiMoveUser = 1000;
    private final static int uiChangeVideoTop = 1001;
    private final static int uiChangeVideoBottom = 1002;

    public static MainFragmentControl getInstance(Context context, Hook hook) {
        return new MainFragmentControl(context, hook, null);
    }

    public static MainFragmentControl getInstance(Context context, Hook hook, MainCallback callback) {
        return new MainFragmentControl(context, hook, callback);
    }

    private Handler handler;

    private MainFragmentControl(Context context, Hook hook, MainCallback callback) {
        super(context, hook);
        mainControlHandler(callback);
    }

    private void mainControlHandler(final MainCallback callback) {
        if (this.handler != null) {
            return;
        }
        this.handler = new Handler(getContext().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case clickAttention:
                        clickAttentionView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickAttention);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickAttention);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 10;
                            }
                        });
                        break;
                    case clickLive:
                        clickLiveView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickLive);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickLive);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 10;
                            }
                        });
                        break;
                    case clickRefresh:
                        clickRefreshView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickRefresh);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickRefresh);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 10;
                            }
                        });
                        break;
                    case clickSearch:
                        clickSearchView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickSearch);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickSearch);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                        break;
                    case clickRecommend:
                        clickRecommendView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickRecommend);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickRecommend);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                        break;
                    case clickUser:
                        clickUserView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickUser);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickUser);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 10;
                            }
                        });
                        break;
                    case uiMoveUser:
                        moveToUser(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, uiMoveUser);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, uiMoveUser);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 10;
                            }
                        });
                        break;
                    case uiChangeVideoTop:
                        moveChangeVideoToTop(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, uiChangeVideoTop);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, uiChangeVideoTop);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                        break;
                    case uiChangeVideoBottom:
                        moveChangeVideoToBottom(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                controlLogger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, uiChangeVideoBottom);
                            }

                            @Override
                            public void fail(String msg) {
                                controlLogger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, uiChangeVideoBottom);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                        break;
                }
            }
        };
    }

    private View mainFragmentView;

    public void setMainFragmentView(View mainFragmentView) {
        this.mainFragmentView = mainFragmentView;
    }

    public void clickAttention() {
        handler.sendEmptyMessage(clickAttention);
    }

    public void clickLive() {
        handler.sendEmptyMessage(clickLive);
    }

    public void clickRefresh() {
        handler.sendEmptyMessage(clickRefresh);
    }

    public void clickSearch() {
        handler.sendEmptyMessage(clickSearch);
    }

    public void clickRecommend() {
        handler.sendEmptyMessage(clickRecommend);
    }

    public void clickUser() {
        handler.sendEmptyMessage(clickUser);
    }

    public void uiMoveUser() {
        handler.sendEmptyMessage(uiMoveUser);
    }

    public void uiChangeVideoTop() {
        handler.sendEmptyMessage(uiChangeVideoTop);
    }

    public void uiChangeVideoBottom() {
        handler.sendEmptyMessage(uiChangeVideoBottom);
    }

    private void clickAttentionView(final Callback callback) {//com.ss.android.ugc.aweme:id/ewo 0x7f071e1d (2131172893)
        if (mainFragmentView == null) {
            callback.fail("关注 布局不存在");
            return;
        }
        handlerPost(getHandler(), () -> {
            TextView attentionTv = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_ATTENTION_INTEGER);
            if (attentionTv != null) {
                attentionTv.performClick();
                callback.success("关注点击成功");
            } else {
                callback.fail("关注按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickLiveView(final Callback callback) {
        if (mainFragmentView == null) {
            callback.fail("直播 布局不存在");
            return;
        }
        handlerPost(getHandler(), () -> {
            LinearLayout liveBt = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_LIVE_LAYOUT_INTEGER);
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
        handlerPost(getHandler(), () -> {
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
        handlerPost(getHandler(), () -> {
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
        handlerPost(getHandler(), () -> {
            RelativeLayout userBt = bindView(mainFragmentView, TremoloParam.MAIN_FRAGMENT_USER_INTEGER);
            if (userBt != null) {
                userBt.performClick();
                callback.success("用户点击成功");
            } else {
                callback.fail("用户按钮未绑定");
            }
        }, callback.sleep());
    }

    private void clickRefreshView(final Callback callback) {
        if (mainFragmentView == null) {
            callback.fail("刷新 布局不存在");
            return;
        }
        handlerPost(getHandler(), () -> {

        }, callback.sleep());
    }

    private void moveToUser(final Callback callback) {
        final float x = getWidth() / 12;
        final float y = getHeight() / 2;
        controlLogger.d(String.format("滑动 用户界面 准备 width [%s], height [%s]", x, y));
        handlerPost(getHandler(), () -> startThread(() -> {
            List<Pair<Float, Float>> pairList = new ArrayList<>();
            pairList.add(new Pair<>(x * 11, y));
            pairList.add(new Pair<>(x * 10, y));
            pairList.add(new Pair<>(x * 9, y));
            pairList.add(new Pair<>(x * 8, y));
            pairList.add(new Pair<>(x * 7, y));
            pairList.add(new Pair<>(x * 6, y));
            pairList.add(new Pair<>(x * 5, y));
            pairList.add(new Pair<>(x * 4, y));
            pairList.add(new Pair<>(x * 3, y));
            pairList.add(new Pair<>(x * 2, y));
            pairList.add(new Pair<>(x * 1, y));
            sendPointerSync(getMotionEvents(pairList));
            callback.success("用户界面 滑动 成功 ");
        }), callback.sleep());
    }

    private void moveChangeVideoToTop(final Callback callback) {
        final float x = getWidth() / 2;
        final float y = getHeight() / 24;
        controlLogger.d(String.format("滑动 视频切换 准备 width [%s], height [%s]", x, y));
        handlerPost(getHandler(), () -> startThread(() -> {
            List<Pair<Float, Float>> pairList = new ArrayList<>();
            pairList.add(new Pair<>(x, y * 20));
            pairList.add(new Pair<>(x, y * 19));
            pairList.add(new Pair<>(x, y * 18));
            pairList.add(new Pair<>(x, y * 17));
            pairList.add(new Pair<>(x, y * 16));
            pairList.add(new Pair<>(x, y * 15));
            pairList.add(new Pair<>(x, y * 14));
            pairList.add(new Pair<>(x, y * 13));
            pairList.add(new Pair<>(x, y * 12));
            pairList.add(new Pair<>(x, y * 10));
            pairList.add(new Pair<>(x, y * 4));
            sendPointerSync(getMotionEvents(pairList));
            callback.success("视频切换 向上滑动 成功 ");
        }), callback.sleep());
    }

    private void moveChangeVideoToBottom(final Callback callback) {
        final float x = getWidth() / 2;
        final float y = getHeight() / 24;
        controlLogger.d(String.format("滑动 视频切换 准备 width [%s], height [%s]", x, y));
        handlerPost(getHandler(), () -> startThread(() -> {
            List<Pair<Float, Float>> pairList = new ArrayList<>();
            pairList.add(new Pair<>(x, y * 8));
            pairList.add(new Pair<>(x, y * 9));
            pairList.add(new Pair<>(x, y * 10));
            pairList.add(new Pair<>(x, y * 11));
            pairList.add(new Pair<>(x, y * 12));
            pairList.add(new Pair<>(x, y * 13));
            pairList.add(new Pair<>(x, y * 14));
            pairList.add(new Pair<>(x, y * 15));
            pairList.add(new Pair<>(x, y * 16));
            pairList.add(new Pair<>(x, y * 17));
            pairList.add(new Pair<>(x, y * 18));
            sendPointerSync(getMotionEvents(pairList));
            callback.success("视频切换 向下滑动 成功 ");
        }), callback.sleep());
    }

    public interface MainCallback {
        void success(String value, int type);

        void fail(String value, int type);
    }
}