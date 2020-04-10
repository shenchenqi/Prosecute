package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.core.util.Pair;

import com.micro.hook.ControlLayout;
import com.micro.root.Logger;
import com.micro.root.mvp.BaseInterface;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/9 9:41
 */
public class ProfileFragmentControl extends ControlLayout {
    private Logger logger = com.micro.root.Logger.getLogger("tremoloLog", "ControlLog");

    private final static int clickBack = 1000;
    public final static int uiLoadMoreVideo = 1001;
    public final static int uiMoveVideo = 1002;

    public static ProfileFragmentControl getInstance(Context context) {
        return new ProfileFragmentControl(context, null);
    }

    public static ProfileFragmentControl getInstance(Context context, ProfileCallback callback) {
        return new ProfileFragmentControl(context, callback);
    }

    private final Context context;
    private final int[] screenData;
    private Handler handler;

    private ProfileFragmentControl(Context context, ProfileCallback callback) {
        this.context = context;
        this.screenData = phoneScreen(context);
        userControlHandler(callback);
    }

    private void userControlHandler(final ProfileCallback callback) {
        this.handler = new Handler(context.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case clickBack:
                        clickBackView(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                logger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, clickBack);
                            }

                            @Override
                            public void fail(String msg) {
                                logger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, clickBack);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                    case uiLoadMoreVideo:
                        moveToUserMoreVideo(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                logger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, uiLoadMoreVideo);
                            }

                            @Override
                            public void fail(String msg) {
                                logger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, uiLoadMoreVideo);
                            }

                            @Override
                            public long sleep() {
                                return BaseInterface.second * 5;
                            }
                        });
                        break;
                    case uiMoveVideo:
                        moveToVideo(new ControlLayout.Callback() {
                            @Override
                            public void success(String value) {
                                logger.d(value);
                                if (callback == null) {
                                    return;
                                }
                                callback.success(value, uiMoveVideo);
                            }

                            @Override
                            public void fail(String msg) {
                                logger.e(msg);
                                if (callback == null) {
                                    return;
                                }
                                callback.fail(msg, uiMoveVideo);
                            }

                            @Override
                            public long sleep() {
                                return 5 * BaseInterface.second;
                            }
                        });
                        break;
                }
            }
        };
    }

    private View profileFragmentView;

    public void setProfileFragmentView(View profileFragmentView) {
        this.profileFragmentView = profileFragmentView;
    }

    public void clickBack() {
        handler.sendEmptyMessage(clickBack);
    }

    public void loadMoreVideo() {
        handler.sendEmptyMessage(uiLoadMoreVideo);
    }

    public void moveVideo() {
        handler.sendEmptyMessage(uiMoveVideo);
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

    private void moveToUserMoreVideo(final Callback callback) {
        final float x = screenData[0] / 2;
        final float y = screenData[1] / 12;
        logger.d(String.format("滑动 加载更多视频 准备 width [%s], height [%s]", x, y));
        handlerPost(handler, () -> {
            startThread(() -> {
                List<Pair<Float, Float>> pairList = new ArrayList<>();
                pairList.add(new Pair<>(x, y * 11));
                pairList.add(new Pair<>(x, y * 10));
                pairList.add(new Pair<>(x, y * 9));
                pairList.add(new Pair<>(x, y * 8));
                pairList.add(new Pair<>(x, y * 7));
                pairList.add(new Pair<>(x, y * 6));
                pairList.add(new Pair<>(x, y * 5));
                pairList.add(new Pair<>(x, y * 4));
                pairList.add(new Pair<>(x, y * 3));
                pairList.add(new Pair<>(x, y * 2));
                pairList.add(new Pair<>(x, y * 1));
                sendPointerSync(getMotionEvents(pairList));
                callback.success("加载更多视频 滑动 成功 ");
            });
        }, callback.sleep());
    }

    private void moveToVideo(final Callback callback) {
        final float x = screenData[0] / 12;
        final float y = screenData[1] / 2;
        logger.d(String.format("滑动 视频界面 准备 width [%s], height [%s]", x, y));
        handlerPost(handler, () -> {
            startThread(() -> {
                List<Pair<Float, Float>> pairList = new ArrayList<>();
                pairList.add(new Pair<>(x * 1, y));
                pairList.add(new Pair<>(x * 2, y));
                pairList.add(new Pair<>(x * 3, y));
                pairList.add(new Pair<>(x * 4, y));
                pairList.add(new Pair<>(x * 5, y));
                pairList.add(new Pair<>(x * 6, y));
                pairList.add(new Pair<>(x * 7, y));
                pairList.add(new Pair<>(x * 8, y));
                pairList.add(new Pair<>(x * 9, y));
                pairList.add(new Pair<>(x * 10, y));
                pairList.add(new Pair<>(x * 11, y));
                sendPointerSync(getMotionEvents(pairList));
                callback.success("视频界面 滑动 成功 ");
            });
        }, callback.sleep());
    }

    public interface ProfileCallback {
        void success(String value, int type);

        void fail(String value, int type);
    }
}