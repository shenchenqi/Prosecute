package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.util.Pair;

import com.micro.hook.AutoControlLayout;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.InspectApply;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.controlLogger;

public class AutoUiControl extends AutoControlLayout implements BaseInterface {

    private final Hook hook;
    private final Context context;
    private final int[] screenData;

    public AutoUiControl(Hook hook, Context context) {
        this.hook = hook;
        this.context = context;
        screenData = phoneScreen(context);
        init();
    }

    private final static int openDouYin = 999;
    private final static int moveUser = 1000;
    private final static int loadMoreVideo = 1001;
    private final static int moveVideo = 1002;
    private final static int changeVideo = 1003;

    private Handler handler;
    private MainActivityControl mainActivityControl;
    private MainFragmentControl mainFragmentControl;
    private UserProfileFragmentControl userProfileFragmentControl;

    private int moreCount;
    private int count;
    private int mainControlStep;

    private void init() {
        moreCount = 0;
        mainControlStep = 0;
        mainActivityControl = new MainActivityControl();
        mainFragmentControl = new MainFragmentControl();
        userProfileFragmentControl = new UserProfileFragmentControl();
        handler = new Handler(getIContext().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case openDouYin:
                        openApply(getIContext());
                        break;
                    case moveUser:
                        if (isAppOnForeground(getIContext())) {
                            if (mainControlStep != 0 && mainControlStep != 4) {
                                return;
                            }
                            mainFragmentControl.moveToUser(screenData[0], screenData[1], new AutoControlLayout.Callback() {
                                @Override
                                public void success(String value) {
                                    controlLogger.d(value);
                                    mainControlStep = 1;
                                }

                                @Override
                                public void fail(String msg) {
                                    controlLogger.e(msg);
                                }

                                @Override
                                public long sleep() {
                                    return 0;
                                }
                            });
                        } else {
                            handler.sendEmptyMessage(openDouYin);
                        }
                        break;
                    case loadMoreVideo:
                        if (isAppOnForeground(getIContext())) {
                            if (mainControlStep != 1) {
                                return;
                            }
                            userProfileFragmentControl.moveToUserMoreVideo(screenData[0], screenData[1], new AutoControlLayout.Callback() {
                                @Override
                                public void success(String value) {
                                    controlLogger.d(value + String.format("滑动[%s][%s]", (count / 10), moreCount));
                                    if (moreCount < (count / 10)) {
                                        moreCount++;
                                        handler.sendEmptyMessageDelayed(loadMoreVideo, sleep());
                                    } else {
                                        mainControlStep = 2;
                                        moreCount = 0;
                                        handler.sendEmptyMessageDelayed(moveVideo, sleep());
                                    }
                                }

                                @Override
                                public void fail(String msg) {
                                    controlLogger.e(msg);
                                }

                                @Override
                                public long sleep() {
                                    return 2 * second;
                                }
                            });
                        } else {
                            moreCount = 0;
                            handler.sendEmptyMessage(openDouYin);
                        }
                        break;
                    case moveVideo:
                        if (isAppOnForeground(getIContext())) {
                            if (mainControlStep != 2) {
                                return;
                            }
                            userProfileFragmentControl.moveToVideo(screenData[0], screenData[1], new AutoControlLayout.Callback() {
                                @Override
                                public void success(String value) {
                                    controlLogger.d(value);
                                    mainControlStep = 3;
                                    handler.sendEmptyMessageDelayed(changeVideo, sleep());
                                }

                                @Override
                                public void fail(String msg) {
                                    controlLogger.e(msg);
                                }

                                @Override
                                public long sleep() {
                                    return 5 * second;
                                }
                            });
                        } else {
                            handler.sendEmptyMessage(openDouYin);
                        }
                        break;
                    case changeVideo:
                        if (isAppOnForeground(getIContext())) {
                            if (mainControlStep != 3) {
                                return;
                            }
                            mainFragmentControl.moveChangeVideo(screenData[0], screenData[1], new AutoControlLayout.Callback() {
                                @Override
                                public void success(String value) {
                                    controlLogger.d(value);
                                    mainControlStep = 4;
                                }

                                @Override
                                public void fail(String msg) {
                                    controlLogger.e(msg);
                                }

                                @Override
                                public long sleep() {
                                    return 0;
                                }
                            });
                        } else {
                            handler.sendEmptyMessage(openDouYin);
                        }
                        break;
                }
            }
        };
    }

    public void autoMoveUser() {
        mainControlStep = 0;
        if (Const.isAuto) {
            handler.sendEmptyMessageDelayed(moveUser, 10 * second);
        }
    }

    public void autoLoadMoreVideo(int count) {
        this.count = count;
        if (Const.isAuto) {
            handler.sendEmptyMessageDelayed(loadMoreVideo, 10 * second);
        }
    }

    @Override
    public Context getIContext() {
        return context;
    }

    public static boolean isAppOnForeground(Context context) {
        return InspectApply.isAppOnForeground(context, Const.PACKAGE_NAME) == 100;
    }

    public static void openApply(Context context) {
        InspectApply.openApply(context, Const.PACKAGE_NAME);
    }

    private void setCoordinateList(List<Pair<Float, Float>> coordinates) {
        for (MotionEvent event : getMotionEvents(coordinates)) {
            mainActivityControl.callDispatchTouchEvent(event);
        }
    }

    public void setMainActivity(Object mainActivity) {
        mainActivityControl.setMainActivity(mainActivity);
    }

    public void setMainFragmentView(View view) {
        mainFragmentControl.setMainFragmentView(view);
    }

    public void setProfileFragmentView(View view) {
        userProfileFragmentControl.setProfileFragmentView(view);
    }

    private class MainActivityControl {
        private Object mainActivity;

        private void setMainActivity(Object mainActivity) {
            this.mainActivity = mainActivity;
        }

        private void callDispatchTouchEvent(MotionEvent event) {
            if (mainActivity == null) {
                controlLogger.e("Main Activity is null");
                return;
            }
            hook.callMethod(mainActivity, TremoloParam.AWEME_MAIN_ACTIVITY_TOUCH_EVENT_METHOD, event);
        }
    }

    private class MainFragmentControl {

        private View mainFragmentView;

        private void setMainFragmentView(View mainFragmentView) {
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

        private void clickLiveView(final Callback callback) {
            if (mainFragmentView == null) {
                callback.fail("直播 布局不存在");
                return;
            }
            handlerPost(handler, () -> {
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

        private void clickRefreshView(final Callback callback) {
            if (mainFragmentView == null) {
                callback.fail("刷新 布局不存在");
                return;
            }
            handlerPost(handler, () -> {

            }, callback.sleep());
        }

        private void moveToUser(final float x, final float y, final Callback callback) {
            controlLogger.d(String.format("滑动 用户界面 准备 width [%s], height [%s]", x, y));
            startThread(() -> {
                List<MotionEvent> events = new ArrayList<>();
                events.add(getObtain(0, MotionEvent.ACTION_DOWN, x - 10, y / 2));
                events.add(getObtain(0, MotionEvent.ACTION_MOVE, x - 10, y / 2));
                events.add(getObtain(100, MotionEvent.ACTION_MOVE, (x / 10) * 9, y / 2));
                events.add(getObtain(200, MotionEvent.ACTION_MOVE, (x / 10) * 8, y / 2));
                events.add(getObtain(300, MotionEvent.ACTION_MOVE, (x / 10) * 7, y / 2));
                events.add(getObtain(400, MotionEvent.ACTION_MOVE, (x / 10) * 6, y / 2));
                events.add(getObtain(500, MotionEvent.ACTION_MOVE, (x / 10) * 5, y / 2));
                events.add(getObtain(600, MotionEvent.ACTION_MOVE, (x / 10) * 4, y / 2));
                events.add(getObtain(700, MotionEvent.ACTION_MOVE, (x / 10) * 3, y / 2));
                events.add(getObtain(800, MotionEvent.ACTION_MOVE, (x / 10) * 2, y / 2));
                events.add(getObtain(900, MotionEvent.ACTION_MOVE, (x / 10) * 1, y / 2));
                events.add(getObtain(1000, MotionEvent.ACTION_MOVE, 10, y / 2));
                events.add(getObtain(1000, MotionEvent.ACTION_UP, 10, y / 2));
                sendPointerSync(events);
                callback.success("用户界面 滑动 成功 ");
            });
        }

        private void moveChangeVideo(final float x, final float y, final Callback callback) {
            controlLogger.d(String.format("滑动 视频切换 准备 width [%s], height [%s]", x, y));
            startThread(() -> {
                List<MotionEvent> events = new ArrayList<>();
                events.add(getObtain(0, MotionEvent.ACTION_DOWN, x / 2, y / 2 - 10));
                events.add(getObtain(0, MotionEvent.ACTION_MOVE, x / 2, y / 2 - 10));
                events.add(getObtain(100, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 9));
                events.add(getObtain(200, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 8));
                events.add(getObtain(300, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 7));
                events.add(getObtain(400, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 6));
                events.add(getObtain(500, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 5));
                events.add(getObtain(600, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 4));
                events.add(getObtain(700, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 3));
                events.add(getObtain(800, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 2));
                events.add(getObtain(900, MotionEvent.ACTION_MOVE, x / 2, (y / 20) * 1));
                events.add(getObtain(1000, MotionEvent.ACTION_MOVE, x / 2, 10));
                events.add(getObtain(1000, MotionEvent.ACTION_UP, x / 2, 10));
                sendPointerSync(events);
                callback.success("视频切换 滑动 成功 ");
            });
        }
    }

    private class UserProfileFragmentControl {

        private View profileFragmentView;

        private void setProfileFragmentView(View profileFragmentView) {
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

        private void moveToUserMoreVideo(final float x, final float y, final Callback callback) {
            controlLogger.d(String.format("滑动 加载更多视频 准备 width [%s], height [%s]", x, y));
            startThread(() -> {
                List<MotionEvent> events = new ArrayList<>();
                events.add(getObtain(0, MotionEvent.ACTION_DOWN, x / 2, y - 10));
                events.add(getObtain(0, MotionEvent.ACTION_MOVE, x / 2, y - 10));
                events.add(getObtain(100, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 9));
                events.add(getObtain(200, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 8));
                events.add(getObtain(300, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 7));
                events.add(getObtain(400, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 6));
                events.add(getObtain(500, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 5));
                events.add(getObtain(600, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 4));
                events.add(getObtain(700, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 3));
                events.add(getObtain(800, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 2));
                events.add(getObtain(900, MotionEvent.ACTION_MOVE, x / 2, (y / 10) * 1));
                events.add(getObtain(1000, MotionEvent.ACTION_MOVE, x / 2, 10));
                events.add(getObtain(1000, MotionEvent.ACTION_UP, x / 2, 10));
                sendPointerSync(events);
                callback.success("加载更多视频 滑动 成功 ");
            });
        }

        private void moveToVideo(final float x, final float y, final Callback callback) {
            controlLogger.d(String.format("滑动 视频界面 准备 width [%s], height [%s]", x, y));
            startThread(() -> {
                List<MotionEvent> events = new ArrayList<>();
                events.add(getObtain(0, MotionEvent.ACTION_DOWN, 10, y / 2));
                events.add(getObtain(0, MotionEvent.ACTION_MOVE, 10, y / 2));
                events.add(getObtain(100, MotionEvent.ACTION_MOVE, (x / 10) * 1, y / 2));
                events.add(getObtain(200, MotionEvent.ACTION_MOVE, (x / 10) * 2, y / 2));
                events.add(getObtain(300, MotionEvent.ACTION_MOVE, (x / 10) * 3, y / 2));
                events.add(getObtain(400, MotionEvent.ACTION_MOVE, (x / 10) * 4, y / 2));
                events.add(getObtain(500, MotionEvent.ACTION_MOVE, (x / 10) * 5, y / 2));
                events.add(getObtain(600, MotionEvent.ACTION_MOVE, (x / 10) * 6, y / 2));
                events.add(getObtain(700, MotionEvent.ACTION_MOVE, (x / 10) * 7, y / 2));
                events.add(getObtain(800, MotionEvent.ACTION_MOVE, (x / 10) * 8, y / 2));
                events.add(getObtain(900, MotionEvent.ACTION_MOVE, (x / 10) * 9, y / 2));
                events.add(getObtain(1000, MotionEvent.ACTION_MOVE, x - 10, y / 2));
                events.add(getObtain(1000, MotionEvent.ACTION_UP, x - 10, y / 2));
                sendPointerSync(events);
                callback.success("视频界面 滑动 成功 ");
            });
        }
    }
}
